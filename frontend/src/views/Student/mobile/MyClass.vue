<template>
<div class="student" style="height:20px;background:#fff">
    <x-header title="我的课程" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group title="我的讨论课">
        <template v-for="course in courses">
        <cell :key="course.courseId" is-link :border-intent="false" :arrow-direction="course.showContent ? 'up' : 'down'"
         @click.native="course.showContent=!course.showContent" value-align="left">
        <span>{{course.courseName}}</span>
        </cell>
            <template v-if="course.showContent">
                <cell-box :key="course.id" :border-intent="false" class="sub-item" is-link @click.native="classInfo(course)" style="padding-left:130px">课程信息</cell-box>
                <cell-box :key="course.id+1" class="sub-item" is-link style="padding-left:130px" @click.native="checkMyScore(course)">我的成绩</cell-box>
                <cell-box :key="course.id+2" class="sub-item" is-link style="padding-left:130px" @click.native="checkMyTeam(course)">我的组队</cell-box>
            </template>
        </template>
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
    .vux-header-more{
        padding-top:9px;
    }
    .weui-cells{
        color:#000;
    }
</style>

<script>
import axios from 'axios'
import vue from 'Vue'
import {TransferDom,XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup} from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,
        Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            showContent001:false,
            show:false,
            courses:'',
            myteam:'',
        }
    },
    mounted:function(){
        this.$axios.get('/course')
        .then((response)=>{
            this.courses=response.data.courses;
            this.courses.forEach(item=>{
                this.$set(item,'showContent',false)
            })
        })
         this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/team')
        .then((response)=>{
            this.myteam=response.data.myTeam;
        })
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        classInfo:function(Currcourse){
            this.$store.state.student.currentCourse=Currcourse
            this.$router.push('/mobile/Student/studentCourseInfo')
        },
        checkMyScore:function(Currcourse){
            if(this.myteam!=null){
            this.$store.state.student.currentCourse=Currcourse
            this.$router.push("/mobile/Student/myScore")}
            else{
            this.$message.error('请先加入或创建一个小组')
            }
        },
        checkMyTeam:function(Currcourse){
            this.$store.state.student.currentCourse=Currcourse
            this.$router.push("/mobile/Student/teamFreedom")
        },
    }
        
  };
</script>