<template>
  <div class="student" style="height:200px;background:#eee">
    <x-header
      title="讨论课"
      style="height:60px;padding-top:12px"
      :right-options="{showMore: true}"
      @on-click-more="showMenus = true"
    ></x-header>
    <group>
      <cell v-for="course in courses" :key="course.id" @click.native="click(course)" style="margin:20px">
        {{course.name}}
        <img
          slot="icon"
          src="@/assets/book.png"
          style="display:block;margin-right:12px;"
          width="23px"
          height="23px"
        >
      </cell>
    </group>
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
  ButtonTabItem
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
    ButtonTabItem
  },
  mounted: function() {
    // this.$axios.get("/course").then(response => {
    //   this.courses = repsonse.data;
    //   this.$store.state.currentClassId=repsonse.data.classId
    // }).catch((error)=>{
    //     console.log(error)
    // });
  },
  data() {
    return {
      name: "name",
      newItem: "newItem",
      courses: [
        {
          id: 1,
          isShareTeam: true,
          isShareSeminar: false,
          name: "OOAD",
          className: "2016-2",
          classId: 1,
          teacherName: "邱明"
        },
        {
          id: 2,
          isShareTeam: true,
          isShareSeminar: false,
          name: "J2EE",
          className: "2016-1",
          classId: 2,
          teacherName: "邱明"
        },
      ]
    };
  },
  methods: {
    // /mobile/Student/studentSeminarList
    click: function(course) {
      this.$store.state.currentCourse=course
      this.$router.push({ name: 'StudentCourseSeminar'})
    }
  }
};
</script>