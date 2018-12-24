<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD讨论课" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <x-header
      style="background:#fff;margin:20px 0px 0px;height:60px;padding-top:12px"
      :left-options="{showBack:false}"
    >
      <div
        slot="left"
        style="font-size:1.3em;color:#000;padding-top:6px;"
      >主题：{{this.$store.state.teacher.currentSeminar.topic}}</div>
    </x-header>
    <x-header style="background:#eee;height:60px;padding-top:12px" :left-options="{showBack:false}">
      <div slot="left" style="font-size:1.3em;color:#000;padding-top:6px;">课次序号：{{s}}</div>
    </x-header>
    <x-header style="background:#fff;height:85px;padding-top:12px" :left-options="{showBack:false}">
      <div
        slot="left"
        style="font-size:1.3em;color:#000;padding-top:18px;"
      >要求：{{this.$store.state.teacher.currentSeminar.introduction}}</div>
    </x-header>
    <x-header style="background:#eee;height:85px;padding-top:12px" :left-options="{showBack:false}">
      <div slot="left" style="font-size:1.3em;color:#000;padding-top:18px;">课程情况：&emsp;&emsp;正在进行</div>
      <p
        slot="right"
        @click="edit"
        style="text-decoration:underline;color:#1AAD19;font-size:1.1em"
      >查看信息</p>
    </x-header>

    <x-button @click="presentation" type="primary" style="margin-top:100px;color:#fff">进入讨论课</x-button>
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
import { XHeader, Cell, Group, CellBox, XButton,TransferDom,Popup } from "vux";
export default {
  directives:{
    TransferDom
  },
  components: {
    XHeader,
    Cell,
    CellBox,
    Group,
    XButton,Popup
  },
  data() {
    return {
      show:false,
    };
  },
  methods: {
    back: function() {
      this.$router.push("/mobile/teacher/seminars");
    },
    more: function() {},
    presentation: function() {
      this.$router.push("/mobile/teacher/manageSeminar");
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
