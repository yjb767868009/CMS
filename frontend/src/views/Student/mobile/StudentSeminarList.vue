<template>
  <div class="student" style="height:200px;background:#eee">
    <x-header
      title="讨论课"
      style="height:60px;padding-top:12px"
      :left-options="{showBack:false}"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>
    <group>
      <cell v-for="course in this.courses" :key="course.couresId" @click.native="clickCourse(course)" style="margin:20px">
        {{course.courseName}}
        <img
          slot="icon"
          src="@/assets/book.png"
          style="display:block;margin-right:12px;"
          width="23px"
          height="23px"
        >
      </cell>
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
.vux-header-more {
  padding-top: 9px;
}
</style>


<script>
import {
  TransferDom,
  XHeader,
  XButton,
  Group,
  Cell,
  Actionsheet,
  ButtonTab,
  ButtonTabItem,Popup
} from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XHeader,
    XButton,
    Group,
    Cell,
    Actionsheet,
    ButtonTab,
    ButtonTabItem,Popup
  },
  mounted:function() {
    this.$axios.get("/course").then(response => {
      this.courses = response.data.courses;
    }).catch((error)=>{
        console.log(error)
    });
  },
  data() {
    return {
      name: "name",
      newItem: "newItem",
      show:false,
      courses:'',
    };
  },
  methods: {
    // /mobile/Student/studentSeminarList
    clickCourse: function(course) {
      this.$store.state.student.currentCourse=course
      this.$router.push({ name: 'StudentCourseSeminar'})
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