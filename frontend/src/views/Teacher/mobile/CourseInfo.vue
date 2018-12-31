<template>
  <div class="course">
    <x-header :title="this.$store.state.teacher.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    
    <cell primary="content" title="课程简介：" value-align="left"><div style="padding-left:5px;font-size:15px">{{this.courseInfo.introduction}}</div></cell>
    <cell primary="content" :border-intent="false" value-align="left"><span style="color:#000">成绩计算规则：</span>
        <div style="padding-left:98px;color:#000;">
            <p>课堂展示：{{this.courseInfo.presentationWeight}}%</p>
            <p>课堂提问：{{this.courseInfo.questionWeight}}%</p>
            <p>书面报告：{{this.courseInfo.reportWeight}}%</p>
        </div>
    </cell>
    <div style="font-size:15px;background:#fff;margin-top:15px">
        <template v-if="this.strategy"><div style="padding-left:15px;color:#000;">小组人数：&emsp;&emsp;&emsp;&emsp;&emsp;{{this.strategy[0].minMember}}~{{this.strategy[0].maxMember}} 人</div></template>
        <div style="padding-left:15px;color:#000;margin-top:15px">组队开始时间：&emsp;{{this.courseInfo.teamStartTime.substring(0, 10)}}&emsp;{{this.courseInfo.teamStartTime.substring(11, 22)}}</div>
        <div style="padding-left:15px;color:#000;margin-top:15px">组队截止时间：&emsp;{{this.courseInfo.teamEndTime.substring(0, 10)}}&emsp;{{this.courseInfo.teamEndTime.substring(11, 22)}}</div>
    </div>

    <x-button @click.native="deletecourse=!deletecourse" style="margin-top:40px;margin-left:63%" mini plain type="warn">删除课程</x-button>
    
    <div v-transfer-dom>
        <confirm v-model="deletecourse"
        title="提示"
        theme="android"
        @on-cancel="Return"
        @on-confirm="sure">
        <p style="text-align:center;">确定删除该课程吗？</p>
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
import {XHeader,XButton,TransferDom,Popup,Cell,Flexbox,FlexboxItem,Confirm} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,Popup,Cell,Flexbox,FlexboxItem,Confirm
    },
    data(){
        return{
            show:false,
            strategy:'',
            courseInfo:'',
            deletecourse:false,
        }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId)
        .then((response)=>{
            this.courseInfo=response.data;
        })
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/strategy')
        .then((response)=>{
            this.strategy=response.data;
        })
    },
    methods:{
    Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
    TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
    GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
    Return(){

    },
    sure(){
        this.$axios.delete('/course/'+this.$store.state.teacher.currentCourse.courseId)
        .then((response)=>{
            console.log(response)
        })
    }
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
