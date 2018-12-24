<template>
  <div class="student" style="height:800px;background:#eee;">
    <x-header
      title="OOAD-讨论课"
      style="height:60px;padding-top:12px;font-size:20px"
      :left-options="{showBack:false}"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>
    <div style="font-size:18px;background:#fff">
      <cell primary="content" title="轮次：" value-align="left">
        <div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;第二轮</div>
      </cell>
    </div>
    <div style="font-size:18px;background:#eee">
      <cell primary="content" title="主题：" value-align="left">
        <div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;领域模型</div>
      </cell>
    </div>
    <div style="font-size:18px;background:#fff">
      <cell primary="content" title="课次序号：" value-align="left">
        <div style="padding-left:30px;color:#000;">&emsp;&emsp;第二次</div>
      </cell>
    </div>

    <cell primary="content" title="要求：" value-align="left">
      <div style="padding-left:30px">界面导航图和所有界面原型设计课堂讨论每个小组15分钟</div>
    </cell>
    <div style="font-size:18px;background:#fff">
      <cell style="height:30px" primary="content" title="报名情况：" value-align="left">
        <div style="padding-left:65px;color:#000;">正在进行
          <span
            @click="registrationStatus"
            style="text-decoration:underline;padding-left:15px;font-size:0.8em;color:#FF3333"
          >报名情况</span>
        </div>
      </cell>
    </div>

    <div style="font-size:15px;background:#fff;margin-top:10px">
      <cell style="height:20px" primary="content" title="PPT：" value-align="left">
        <div style="padding-left:100px;color:#00DB00;">已提交</div>
      </cell>
    </div>

    <flexbox style="margin-top:15px">
      <flexbox-item>
        <x-button type="primary" @click.native="presentation">进入讨论课</x-button>
      </flexbox-item>
    </flexbox>
    <flexbox style="margin-top:15px">
      <flexbox-item>
        <x-button type="primary" @click.native="submit=!submit">提交PPT</x-button>
      </flexbox-item>
    </flexbox>
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
  FlexboxItem
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
    FlexboxItem
  },
  data() {
    return {
      show: false,
      submit: false
    };
  },
  methods: {
    modify: function() {
      this.$router.push(
        "/mobile/student/course/seminar/seminarRegistrationModification"
      );
    },
    registrationStatus:function(){
        this.$router.push(
            "/mobile/student/course/seminar/running/presentation"
        )
    },
    presentation:function(){
        this.$router.push({
            name:'/mobile/student/course/seminar/running'
        })
    },
    running: function() {
      this.$router.push("/mobile/Student/studentSeminarList");
    },
    StudentInfo: function() {
      this.$router.push("/mobile/student/studentInfo");
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