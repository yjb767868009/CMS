<template>
  <div class="student" style="height:800px;background:#eee;">
    <x-header
      title="OOAD-讨论课"
      style="height:60px;padding-top:12px;font-size:20px"
      :left-options="{showBack:false}"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>
    
    <group>
      <cell :title="'轮次'" :value="'第'+this.$store.state.student.currentRound.order+'轮'"></cell>
      <cell :title="'主题'" :value="this.$store.state.student.currentSeminar.topic"></cell>
      <cell :title="'课次序号'" :value="this.$store.state.student.currentSeminar.klassSeminars[0].klass.klassSerial"></cell>
      <x-textarea :title="'要求'" :show-counter="false" :placeholder="this.$store.state.student.currentSeminar.introduction" disabled></x-textarea>
      <cell :title="'报名:'">已报名</cell>
      <cell :title="'课程情况'">已完成&emsp;&emsp;&emsp;<a @click="checkInfo">报名情况</a></cell>
      <cell :title="'PPT'">{{'未提交'}}</cell>
      <cell :title="'书面报告'">{{'未提交'}}</cell>
      <x-button @click.native="register" type="primary" style="margin-top:18px;color:#fff">报名</x-button>
    </group>

    <div v-transfer-dom>
      <confirm
        v-model="submit"
        ref="confirm"
        :show-cancel-button="false"
        title="上传文件"
        show-input
        @on-confirm="onConfirm"
        @on-show="onshow"
      ></confirm>
      <popup v-model="show" height="15%">
        <div>
          <cell value-align="left" title>
            <img
              slot="icon"
              src="@/assets/man.png"
              style="display:block;margin-right:10px;"
              width="30px"
              height="30px"
            >
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div>
          </cell>
          <cell value-align="left" title>
            <img
              slot="icon"
              src="@/assets/book.png"
              style="display:block;margin-right:10px;"
              width="30px"
              height="30px"
            >
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div>
          </cell>
        </div>
      </popup>
    </div>
  </div>
</template>

<style>
.vux-header-back {
  padding: 9px;
  font-size: 1.1em;
}
.left-arrow {
  margin-top: 10px;
}
.vux-header-more {
  padding-top: 9px;
}
</style>


<script>
import axios from "axios";
import {
  XHeader,
  XButton,
  GroupTitle,
  Cell,
  Picker,
  TransferDom,
  Popup,
  Confirm,
  Flexbox,
  FlexboxItem,XTextarea
} from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XHeader,
    XButton,
    GroupTitle,
    Cell,
    Popup,
    Confirm,
    Flexbox,
    FlexboxItem,XTextarea
  },
  data() {
    return {
      show: false,
      submit: false
    };
  },
  methods: {
    state: function() {
      this.$router.push("/mobile/student/course/seminar/finished");
    },
    alter: function() {
      this.$router.push(
        "/mobile/student/course/seminar/seminarRegistrationModification"
      );
    },
    running: function() {
      this.$router.push("/mobile/Student/studentSeminarList");
    },
    StudentInfo: function() {
      this.$router.push("/mobile/student/studentInfo");
    },
    onCancel: function() {
      console.log("取消");
    },
    onConfirm: function() {
      console.log("确认");
    },
    onshow: function() {
      this.$refs.confirm.setInputValue("业务流程分析1-1.doc");
    },
    score: function() {
      this.$router.push("/mobile/student/course/seminar/seminarScore");
    }
  }
};
</script>