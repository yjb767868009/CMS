<template>
  <div class="course">
    <x-header title="新建课程" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>



    <group>
        <x-input v-model="courseName" placeholder="课程名称"  style="padding-left:8em"></x-input>
        <x-textarea v-model="courseRequirement" placeholder="课程要求"></x-textarea>
    </group>

       
    <group title="成绩计算规则（百分比）：">
        <popup-picker title="展示" :data="percentageOptions" v-model="presentation"></popup-picker>
        <popup-picker title="提问" :data="percentageOptions" v-model="question"></popup-picker>
        <popup-picker title="报告" :data="percentageOptions" v-model="report"></popup-picker>
    </group>
    
    <group title="组员基本要求：">
        <template>
        <span style="padding-left:15px">小组总人数(含组长)：</span>
        <x-input placeholder="上限" v-model="maxmember"></x-input><x-input placeholder="下限" v-model="minmember"></x-input>
        </template>
    </group>
    <template>
        <template v-for="teamrule in teamruleCourses"> 
            <group>
                <cell style="margin-top:1em" value-align="left"><span style="padding-left:6em;color:#000">{{teamrule.course.courseName+' '+teamrule.course.teacher.name}}</span></cell>
                <x-input placeholder="上限" v-model="teamrule.max"></x-input>
                <x-input placeholder="下限" v-model="teamrule.min"></x-input>
            </group>
        </template>
        <x-button mini style="margin-left:21em;margin-bottom:8px" @click.native="newTeamRule()" type="primary">新增</x-button>
    </template>

    <group title="选修课人数要求：" v-if="this.teamruleCourses!=''">
      <button-tab v-model="strategy">
        <button-tab-item @on-item-click="consoleIndex('0')">仅满足</button-tab-item>
        <button-tab-item @on-item-click="consoleIndex('1')">均满足</button-tab-item>
      </button-tab>
    </group>

    <group title="冲突课程：" style="text-align:left" >
        <template v-for="conflictCourse in conflictCourses"> 
                <cell value-align="left"><span style="padding-left:6em;color:#000">{{conflictCourse.courseName+' '+conflictCourse.teacher.name}}</span></cell>
                <!-- <x-input></x-input>
                <x-input></x-input> -->
        </template>
        <x-button mini style="margin-left:21em;margin-bottom:8px" @click.native="newConflict()" type="primary">新增</x-button>
    </group>

    <group label-width="6em" style="text-align:left" >
        <datetime style="height:1.5em" v-model="teamStartTime" :start-date="startDateS" :end-date="endDateS" format="YYYY-MM-DD HH:mm"  title="组队开始时间"></datetime>
        <datetime style="height:1.5em" v-model="teamEndTime" :start-date="startDateE" :end-date="endDateE" format="YYYY-MM-DD HH:mm"  title="组队截止时间"></datetime>
    </group>

    <x-button @click.native="newCourse" type="primary" style="margin-top:40px;color:#fff">发布</x-button>
    
    <div v-transfer-dom>
        <popup v-model="showteam">
        <template v-for="course in courses">
            <cell :title="course.courseName+' '+course.teacher.name" @click.native="addteamrule(course)"></cell>
        </template>
        </popup>
    </div>

    <div v-transfer-dom>
        <popup v-model="showConflict">
        <template v-for="course in courses">
            <cell :title="course.courseName+' '+course.teacher.name" @click.native="addmenu(course)"></cell>
        </template>
        </popup>
    </div>

    <div v-transfer-dom>
      <popup v-model="show" height="23%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div></cell>
          </div>
      </popup>
    </div>
  </div>
</template>

<script>
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,TransferDom,Popup,Cell,XTextarea,ButtonTab, ButtonTabItem} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Divider,
        Group,
        Datetime,
        XInput,
        PopupPicker,Popup,Cell,XTextarea,ButtonTab, ButtonTabItem
    },
        data () {
        return {
            percentageOptions: [['10','15', '20','25', '30','35', '40','45','50','55','60','65','70','75','80','85','90','95','100']],
            startDateS:'2018-01-01',
            endDateS: '2020-12-31',
            startDateE:'2018-01-01',
            endDateE: '2020-12-31',
            show:false,
            courseName:'',
            courseRequirement:'',
            presentation:['10'],
            question:['10'],
            report:['10'],
            teamStartTime:'',
            teamEndTime:'',
            strategy:'',
            courses:'',
            conflictCourses:[],
            teamruleCourses:[],
            showteam:false,
            showConflict:false,
            ruleCourseList:[],
            conflictCourseList:[],
            maxMemberList:[],
            minMemberList:[],
            maxmember:'',
            minmember:'',
            max:'',
            min:'',
            strategies:[],
            type:'TeamOrStrategy',
            CourseMemberLimitStrategies:[],
            ConflictCourseStrategies:[]

        }
    },
    mounted(){
        this.$axios.get('/allcourse')
        .then((res)=>{
            this.courses=res.data
        })
    },
    methods: {
    Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
    TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
    GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
    newCourse(){
        console.log(
        this.courseName,//courseName
        this.introduction,//introduction
        this.presentation[0],//presentationWeight
        this.question[0],//questionWeight
        this.report[0],
        this.type,
        this.teamStartTime,//~~~
        this.teamEndTime,)//~~~
        for(var i=0;i<this.teamruleCourses.length;i++){
            this.minMemberList.push(this.teamruleCourses[i].min)
            this.maxMemberList.push(this.teamruleCourses[i].max)
        }
        this.strategies.push({type:this.type})
        for(var i=0;i<this.teamruleCourses.length;i++){
            this.CourseMemberLimitStrategies.push({course:{courseId:this.ruleCourseList[i]},maxMember:this.maxMemberList[i],minMember:this.minMemberList[i]})
        }
        for(var j=0;j<this.conflictCourseList.length;j++){
            this.ConflictCourseStrategies.push({courseId:this.conflictCourseList[j]})
        }
        console.log({
            courseName:this.courseName,
            introduction:this.courseRequirement,
            presentationWeight:this.presentation[0],
            reportWeight:this.report[0],
            questionWeight:this.question[0],
            teamStartTime:this.teamStartTime,
            teamEndTime:this.teamEndTime,
            MemberLimitStrategy:{minMember:this.minmember,maxMember:this.maxmember},
            })
            if(this.type=='TeamAndStrategy'){
            this.$axios.post('/course',{
            courseName:this.courseName,
            introduction:this.courseRequirement,
            presentationWeight:this.presentation[0],
            reportWeight:this.report[0],
            questionWeight:this.question[0],
            teamStartTime:this.teamStartTime.slice(0,4)+'-'+this.teamStartTime.slice(5,7)+'-'+this.teamStartTime.slice(8,10)+'T18:07:14.384+0000',
            teamEndTime:this.teamEndTime.slice(0,4)+'-'+this.teamEndTime.slice(5,7)+'-'+this.teamEndTime.slice(8,10)+'T18:10:14.384+0000',
            memberLimitStrategy:{minMember:this.minmember,maxMember:this.maxmember},
            teamAndStrategy:{},
            courseMemberLimitStrategies: this.CourseMemberLimitStrategies,
            conflictCourseStrategy:{courses:this.ConflictCourseStrategies},
            }
        ).then((res)=>{
            console.log(res)
            this.$router.go(-1)
        })}else if(this.type=='TeamOrStrategy'){
        this.$axios.post('/course',{
                    courseName:this.courseName,
                    introduction:this.courseRequirement,
                    presentationWeight:this.presentation[0],
                    reportWeight:this.report[0],
                    questionWeight:this.question[0],
                    teamStartTime:this.teamStartTime.slice(0,4)+'-'+this.teamStartTime.slice(5,7)+'-'+this.teamStartTime.slice(8,10)+'T18:07:14.384+0000',
                    teamEndTime:this.teamEndTime.slice(0,4)+'-'+this.teamEndTime.slice(5,7)+'-'+this.teamEndTime.slice(8,10)+'T18:10:14.384+0000',
                    memberLimitStrategy:{minMember:this.minmember,maxMember:this.maxmember},
                    teamOrStrategy:{},
                    courseMemberLimitStrategies: this.CourseMemberLimitStrategies,
                    conflictCourseStrategy:{courses:this.ConflictCourseStrategies},
                    }
                ).then((res)=>{
                    console.log(res)
                    this.$router.go(-1)
                })
        }
        this.strategies=[]
        },
    newTeamRule(){
        this.showteam=!this.showteam
    },
    consoleIndex(t){
        if(t=='0'){
            this.type='TeamOrStrategy'
        }else if(t=='1'){
            this.type='TeamAndStrategy'
        }
    },
    newConflict(){
        this.showConflict=true
    },
    addmenu(cour){
        this.conflictCourses.push(cour)
        this.conflictCourseList.push(cour.courseId)
        console.log(this.conflictCourseList)
        this.showConflict=false
    },
    addteamrule(cour){
        this.teamruleCourses.push({
            course:cour,
            max:'',
            min:'',})
        this.ruleCourseList.push(cour.courseId)
        console.log(this.ruleCourseList)
        this.showteam=false
    }
  },

}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
