<template>
  <div class="student" style="height:800px;background:#eee;">
    <x-header
      title="讨论课正在进行"
      style="height:60px;padding-top:12px;font-size:20px"
      :left-options="{showBack:false}"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>
    <div style="font-size:18px;background:#fff;margin-top:7px;margin-bottom:5px">
      <cell primary="content" value-align="left">
        <div style="text-align:center;color:#000;height:45px;">
          <span style="font-size:1.2em">业务流程分析</span>
          <p style="margin-top:5px;text-align:right;font-size:0.8em;color:#000">第三组&emsp;展示</p>
        </div>
      </cell>
    </div>

    <div style="font-size:18px;background:#fff">
      <cell primary="content" title="第一组：" value-align="left">
        <div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;1-1</div>
      </cell>
    </div>
    <div style="font-size:18px;background:#eee">
      <cell primary="content" title="第二组：" value-align="left">
        <div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;1-2</div>
      </cell>
    </div>
    <div style="font-size:18px;background:#fff">
      <cell primary="content" title="第三组：" value-align="left">
        <div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;
          <span style="font-weight:bold;color:#FF3333">1-3</span>
        </div>
      </cell>
    </div>
    <div style="font-size:18px;background:#eee">
      <cell primary="content" title="第四组：" value-align="left">
        <div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;1-4</div>
      </cell>
    </div>

    <flexbox style="margin-top:30px">
        <x-button type="primary" @click.native="question=!question">发起提问</x-button>
    </flexbox>

    <div v-transfer-dom>

      <confirm v-model="question"
        :show-cancel-button="false"
        title="提问成功"
        @on-confirm="onConfirm">
        <p style="text-align:center;">等待老师抽取</p>
      </confirm>
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
import Vue from "vue";
import {
  XHeader,XButton,
  Cell,
  Group,
  AlertPlugin,
  TransferDom,
  Popup,Confirm,Flexbox
} from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XButton,
    Group,
    XHeader,
    Cell,
    AlertPlugin,
    Popup,Confirm,Flexbox
  },
  data() {
    return {
      show: false,
      question:false,
    };
  },
  methods: {
    onCancel: function() {
      console.log("取消");
    },
    onConfirm: function() {
      console.log("确认");
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