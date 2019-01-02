<template>
<div class="student" style="height:800px;background:#fff;">
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px;font-size:20px" :left-options="{showBack:false}" :right-options="{showMore: true}"  @on-click-more="show=!show">
    
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
        <div style="padding-left:15px;color:#000;margin-top:15px">组队开始时间：&emsp;{{this.courseInfo.teamStartTime.substring(0, 10)}}&emsp;{{this.courseInfo.teamStartTime.substring(11, 22)}}</div>
        <div style="padding-left:15px;color:#000;margin-top:15px">组队截止时间：&emsp;{{this.courseInfo.teamEndTime.substring(0, 10)}}&emsp;{{this.courseInfo.teamEndTime.substring(11, 22)}}</div>
    </div>
    <cell primary="content" title="组队要求：" value-align="left" style="padding-left:8em"></cell>
    <group title="组员基本要求">
    <template v-if="this.strategy">
        <div style="padding-left:15px;color:#000;">小组总人数（含组长）：&emsp;&emsp;&emsp;{{this.strategy[1].minMember}}~{{this.strategy[1].maxMember}} 人</div>
        <div style="padding-left:15px;margin-top:1em">组内选修课程人数:</div>
        <template v-for="stra in strategy">
            <div style="padding-left:6em" v-if="stra.type==='CourseMemberLimitStrategy'">{{stra.course.courseName}}&emsp;&emsp;{{stra.minMember}}~{{stra.maxMember}}人</div>
        </template>
        <div style="padding-left:15px;margin-top:1em">选修课人数要求:&emsp;&emsp;<span v-if="this.strategy[0].type=='TeamAndStrategy'">均满足</span><span v-if="this.strategy[0].type=='TeamOrStrategy'">满足其一</span></div>
    </template>
    <div style="padding-left:15px;margin-top:1em;font-size:0.8em;color:#aaa">均满足只选修课人数均达到要求</div>
    <div style="padding-left:15px;font-size:0.8em;color:#aaa">满足其一指任意选修课人数满足即可（多指冲突课程）</div>    
    </group>
    
    <group title="冲突课程">
        <template v-for="stra in strategy">
            <div style="padding-left:6em" v-if="stra.type==='CourseMemberLimitStrategy'">{{stra.course.courseName}}&emsp;&emsp;({{stra.course.teacher.name}}老师)</div>
        </template>
        
    <div style="padding-left:15px;margin-top:1em;font-size:0.8em;color:#aaa">选修不同冲突课程学生不可同组</div>
    <div style="padding-left:15px;font-size:0.8em;color:#aaa">注意同课程名不同教师名为不同课程</div>
    </group>
    <div v-transfer-dom>
      <popup v-model="show" height="15%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div></cell>
          </div>
      </popup>
    </div>
</div>
</template>

<style>
.vux-header-back{
    padding:9px;
    font-size:1.1em;
  }
.left-arrow{
    margin-top:10px;
}
.vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import axios from 'axios'
import {TransferDom,XHeader,Cell,Actionsheet,Popup,Group
        } from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        Cell,
        Actionsheet,
        Popup,Group
    },
    data() {
       return{ 
           show:false,
           courseInfo:'',
           strategy:'',
    }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId)
        .then((response)=>{
            this.courseInfo=response.data;
        })
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/strategy')
        .then((response)=>{
            this.strategy=response.data;
        })
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            console.log("sss");
        },
        edit:function(){
            this.$router.push('/seminarSeqFinished')
        },
        sign:function(){
            this.$router.push('/seminarSeqUnsigned')
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        }
    }
        
  };
</script>