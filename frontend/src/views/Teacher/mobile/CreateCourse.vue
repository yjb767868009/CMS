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
        <popup-picker title="小组总人数(含组长)：" :data="percentageOptions" v-model="presentation"></popup-picker>
        <cell><x-input placeholder="上限"></x-input><x-input placeholder="下限"></x-input></cell>
        </template>
        <x-button mini style="margin-left:21em;margin-bottom:8px" @click.native="newTeamRule" type="primary">新增</x-button>
    </group>
    
    <group title="选修课人数要求：">
      <button-tab v-model="strategy">
        <button-tab-item @on-item-click="consoleIndex()">仅满足</button-tab-item>
        <button-tab-item @on-item-click="consoleIndex()">均满足</button-tab-item>
      </button-tab>
    </group>

    <!-- <group title="冲突课程：" style="text-align:left" >
        <template v-for="conflictCourse in conflictCourses"> 
            <cell>
                <x-input></x-input>
                <x-input></x-input>
            </cell>
        </template>
        <x-button mini style="margin-left:21em;margin-bottom:8px" @click.native="" type="primary">新增</x-button>
    </group>

    <x-button @click.native="" type="primary" style="margin-top:40px;color:#fff">确认设置</x-button> -->

    <group label-width="6em" style="text-align:left" >
        <datetime style="height:1.5em" v-model="teamStartTime" :start-date="startDateS" :end-date="endDateS" format="YYYY-MM-DD HH:mm"  title="组队开始时间"></datetime>
        <datetime style="height:1.5em" v-model="teamEndTime" :start-date="startDateE" :end-date="endDateE" format="YYYY-MM-DD HH:mm"  title="组队截止时间"></datetime>
    </group>

    <x-button @click.native="newCourse" type="primary" style="margin-top:40px;color:#fff">发布</x-button>
    
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
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,TransferDom,Popup,Cell,XTextarea} from 'vux'
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
        PopupPicker,Popup,Cell,XTextarea
    },
        data () {
        return {
            percentageOptions: [['10','15', '20','25', '30','35', '40','45','50','55','60','65','70','75','80','85','90','95','100']],
            startDateS:'2018-01-01',
            endDateS: '2018-12-31',
            startDateE:'2018-01-01',
            endDateE: '2018-12-31',
            show:false,
            courseName:'',
            courseRequirement:'',
            presentation:['10'],
            question:['10'],
            report:['10'],
            teamStartTime:'',
            teamEndTime:'',
        }
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
        this.courseRequirement,//introduction
        this.presentation[0],//presentationWeight
        this.question[0],//questionWeight
        this.report[0],
        this.teamStartTime,//~~~
        this.teamEndTime,)//~~~
        
        this.$axios.post('/course',{
            courseName:this.courseName,
            introduction:this.courseRequirement,
            presentationWeight:this.presentation[0],
            reportWeight:this.report[0],
            questionWeight:this.question[0],
            teamStartTime:this.teamStartTime,
            teamEndTime:this.teamEndTime,
        })
    },
    newTeamRule(){
        console.log('newtr')
    }
  },

}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
