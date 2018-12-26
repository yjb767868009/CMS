<template>
  <div class="course" style="background:#eee">
    <x-header title="讨论课" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <group>
      <cell
        v-for="course in courses"
        :key="course.courseId"
        :title="course.courseName"
        is-link
        @click.native="clickCourse(course)"
      ></cell>
    </group>

    <template v-if="seminar_on_going">
      <x-button @click="doing" style="margin-top:100%;font-size:10px;" mini type="warn">正在进行讨论课</x-button>
    </template>
    
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
import { XHeader, Cell, Group, XButton,TransferDom,Popup } from "vux";
export default {
  directives:{
    TransferDom
  },
  components: {
    XHeader,
    Cell,
    Group,
    XButton,Popup
  },
  data() {
    return {
      show:false,
      courses: [
        {
          courseId: 1,
          teacher: {
            teacherId: 1,
            email: "992964285@qq.com",
            name: "wu wei"
          },
          courseName: "j2ee",
          introduction: "j2ee",
          presentationWeight: 50,
          reportWeight: 20,
          questionWeight: 30,
          teamStartTime: "2018-11-27T14:00:00.000+0000",
          teamEndTime: "2018-12-01T14:00:00.000+0000"
        },
      ], 
      coursePlus: {
        "1": "主",
        "2": "从"
      },
      seminar_on_going: false
    };
  },
  //   mounted:function(){
  //       this.$axios.get('/course')
  //       .then((response)=>{
  //           this.courses=response.data.courses
  //           this.coursePlus=response.data.coursePlus
  //       })
  // },
  methods: {
    clickCourse: function(course) {
      this.$store.state.teacher.currentCourse = course;
      if (this.coursePlus[course.courseId] === "主") {
        //老师主
        this.$router.push("/mobile/teacher/seminarP");
      } else if (this.coursePlus[course.courseId] === "从") {
        //老师从
        this.$router.push("/mobile/teacher/seminarS");
      }
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
  }
};
</script>
