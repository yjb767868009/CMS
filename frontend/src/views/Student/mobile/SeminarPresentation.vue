<template>
  <div class="student" style="height:800px;background:#eee;">
    <x-header
      title="讨论课PPT"
      style="height:60px;padding-top:12px;font-size:20px"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>
    <div style="font-size:18px;background:#fff">
      <cell primary="content" title="第一组：" value-align="left">
        <div style="text-decoration:underline;padding-left:40px;color:#005AB5;">1-1&emsp;业务流程.ppt</div>
      </cell>
    </div>
    <div style="font-size:18px;background:#eee">
      <cell primary="content" title="第二组：" value-align="left">
        <div style="text-decoration:underline;padding-left:40px;color:#005AB5;">1-2&emsp;业务流程.ppt</div>
      </cell>
    </div>
    <div style="font-size:18px;background:#fff">
      <cell primary="content" title="第三组：" value-align="left">
        <div style="text-decoration:underline;padding-left:40px;color:#005AB5;">1-3&emsp;业务流程.ppt</div>
      </cell>
    </div>
    <div style="font-size:18px;background:#eee">
      <cell primary="content" title="第四组：" value-align="left">
        <div style="text-decoration:underline;padding-left:40px;color:#005AB5;">1-4&emsp;业务流程.ppt</div>
      </cell>
    </div>

    <div v-transfer-dom>
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
import { XHeader, XButton, Group, Cell, TransferDom, Popup } from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XHeader,
    XButton,
    Group,
    Cell,
    Popup
  },
  data() {
    return {
      show: false
    };
  },
  methods: {
    toast: function() {
      Toast(this.name);
    },
    onClick: function() {
      console.log("sss");
    },
    edit: function() {
      this.$router.push("/StudentInfo");
    },
    running: function() {
      this.$router.push("/mobile/Student/studentSeminarList");
    },
    StudentInfo: function() {
      this.$router.push("/mobile/student/studentInfo");
    }
  }
};
</script>