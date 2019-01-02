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
