<template>
  <div class="course">
    <x-header :title="this.$store.state.teacher.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <div style="margin-left:10px">
    <div style="text-align:center;margin-top:20px">修改讨论课</div>

    <group>
        <x-input v-model="topic" :title="'主题'" placeholder="主题 讨论课大纲" label-width="6em"></x-input>
        <x-textarea  v-model="intro" :title="'内容'" placeholder="讨论课主要内容 要求 具体讨论部分" ></x-textarea>
        <popup-picker title="讨论课次序号" :data="seminarOrders" v-model="seminarOrder"></popup-picker>
        <x-switch v-model="visible" title="讨论课可见" :value-map="[false,true]"></x-switch>
        
        <datetime style="height:20px;" v-model="signupStartTime" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm"  title="报名开始时间"></datetime>
        <datetime style="height:20px" v-model="signupEndTime" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm" title="报名截止时间"></datetime>
        <popup-picker title="报名小组数" :data="teamNums" v-model="teamNum" ></popup-picker>
        <popup-picker title="所属round" :data="roundOrders" v-model="roundOrder"></popup-picker>
    </group>
    </div>
    <x-button @click.native="deleteseminar=!deleteseminar" style="margin-top:40px;margin-left:63%" mini plain type="warn">删除讨论课</x-button>
    <x-button @click.native="submit" type="primary" style="margin-top:55px;color:#fff">发布</x-button>
    <div v-transfer-dom>
         <confirm v-model="deleteseminar"
        title="提示"
        @on-confirm="sure">
        <p style="text-align:center;">确定删除该讨论课吗？</p>
      </confirm>
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
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,XSwitch,XTextarea,TransferDom,Popup,Cell,Confirm} from 'vux'
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
        PopupPicker,
        XSwitch,
        XTextarea,Popup,
        Cell,Confirm
    },
    data () {
        return {
            seminarOrder: ['1'],
            seminarOrders: [['1', '2', '3','4','5']],
            startDate: '2018-01-01',
            endDate: '2018-12-31',
            teamNum: ['6'],
            teamNums: [['1', '2', '3','4','5','6']],
            roundOrder: ['1'],
            roundOrders: [['1' ,'2', '3','4','5','6','7','8','9','10']],
            topic:this.$store.state.teacher.currentSeminar.topic,
            intro:this.$store.state.teacher.currentSeminar.introduction,
            visible:false,
            signupStartTime:'',
            signupEndTime:'',
            show:false,
            roundList:[],
            deleteseminar:false,
            ismaster:'',
            currentCourseId:''
        }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/roundlist')
        .then((res)=>{
            this.roundList=res.data
            this.roundOrders=[this.roundOrders[0].slice(0,this.roundList.length)]

        })
        // this.$axios.get('/course')
        // .then((res)=>{
        //     for(var i=0;i<res.data.coursePlus.length;i++){
        //         if(this.$store.state.teacher.currentCourse.courseId==res.data.coursePlus[i]){

        //         }
        //     }
        //     this.ismaster=res.data.coursePlus
        //     this.currentCourseId=this.$store.state.teacher.currentCourse.courseId
        // })
    },
    methods:{
        submit:function(){
            this.$axios.post('/course/'+this.$store.state.teacher.currentCourse.courseId+'/seminar',{
                topic:this.topic,
                intro:this.intro,
                signOrder:this.seminarOrder[0],
                round:{roundId:this.roundList[parseInt(this.roundOrder[0])].roundId,},
                visible:this.visible,
                maxTeamNum:this.teamNum[0],
                signupStartTime:this.signupStartTime.slice(0,4)+'/'+this.signupStartTime.slice(5,7)+'/'+this.signupStartTime.slice(8,10),
                signupEndTime:this.signupEndTime.slice(0,4)+'/'+this.signupEndTime.slice(5,7)+'/'+this.signupEndTime.slice(8,10),
            }).then((res)=>{
                this.$router.push('/mobile/teacher/seminars')
            })
        },
    Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
    TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
    GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
        sure(){
            this.$message('删除成功')
        }
    // submit:function(){
    //     this.$message('修改成功')
    // }
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
