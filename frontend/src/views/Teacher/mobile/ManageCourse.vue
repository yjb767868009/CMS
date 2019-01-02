<template>
  <div class="course" style="background:#fff">
    <x-header title="课程管理" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}"
      @on-click-more="show=!show">
    </x-header>
    <group title="我的课程">
      <template v-for="course in this.courses">
        <cell :key="course.courseId" :title="course.courseName" is-link :border-intent="false" :arrow-direction="course.showCourseContent?'up':'down'"
          @click.native="select(course)" style="height:30px">
        </cell>
        <template v-if="course.showCourseContent">
          <div style="margin-left:20px" :key="course.courseId">
          <cell-box :border-intent="false" class="sub-item" is-link @click.native="gradelist">学生成绩</cell-box>
          <cell-box class="sub-item" is-link @click.native="teamlist">学生组队</cell-box>
          <cell-box class="sub-item" is-link @click.native="courseinfo">课程信息</cell-box>
          <cell-box class="sub-item" is-link @click.native="classInfo">班级信息</cell-box>
          <cell-box class="sub-item" is-link @click.native="setSeminar">讨论课设置</cell-box>
          <cell-box class="sub-item" is-link @click.native="setShare">共享设置</cell-box>
          </div>
        </template>
      </template>
    </group>
    <x-button style="margin-top:40px"  type="primary" @click.native="newCourse">新建课程</x-button>



    <div v-transfer-dom>
      <popup v-model="show" height="23%">
        <div>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div>
          </cell>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div>
          </cell>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div>
          </cell>
        </div>
      </popup>
    </div>
  </div>
</template>

<script>
  import {
    XHeader,
    Cell,
    CellBox,
    Group,
    TransferDom,
    Popup,
    XButton
  } from 'vux'
  export default {
    directives: {
      TransferDom
    },
    components: {
      XHeader,
      Cell,
      CellBox,
      Group,
      Popup,XButton
    },
    data() {
      return {
        show: false,
        courses:'',
        coursePlus: {
          "1": "主",
          "2": "从"
        },
      }
    },
    mounted: function () {
      this.$axios.get('/course')
        .then((response) => {
          this.courses = response.data.courses
          this.coursePlus = response.data.coursePlus
          //为返回数据绑定show属性用于折叠显示
      for (var i = 0; i < this.courses.length; i++) {
        this.$set(this.courses[i],'showCourseContent', false)
      }
        })
      
    },
    methods: {
      back: function () {
        this.$router.push('/mobile/teacher')
      },
      Undo() {
        this.$router.push('/mobile/teacher/notify')
      },
      TeacherInfo() {
        this.$router.push('/mobile/teacher')
      },
      GoSeminar() {
        this.$router.push('/mobile/teacher/seminars')
      },
      newCourse(){
          this.$router.push('/mobile/teacher/createcourse')
      },
      setShare:function(){
        this.$router.push('/mobile/teacher/share')
      },
      gradelist:function(){
        this.$router.push('/mobile/teacher/gradelist')
      },
      select:function(course){
        course.showCourseContent=!course.showCourseContent;
        this.$store.state.teacher.currentCourse=course
      },
      courseinfo:function(){
        this.$router.push('/mobile/teacher/courseinfo')
      },
      classInfo:function(){
        this.$router.push('/mobile/teacher/classinfo')
      },
      teamlist:function(){
        this.$router.push('/mobile/teacher/teamlist')
      },
      setSeminar(s){
        this.$router.push('/mobile/teacher/seminarP')
      }
    }
  }

</script>

<style scoped>
  .sub-item {
    color: #888;
  }

  .slide {
    padding: 0 20px;
    overflow: hidden;
    max-height: 0;
    transition: max-height .5s cubic-bezier(0, 1, 0, 1) -.1s;
  }

  .animate {
    max-height: 9999px;
    transition-timing-function: cubic-bezier(0.5, 0, 1, 0);
    transition-delay: 0s;
  }

</style>
