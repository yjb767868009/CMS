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
      <div slot="left" style="font-size:1.3em;color:#000;padding-top:6px;">课次序号：&emsp;&emsp;&emsp;1</div>
    </x-header>
    <x-header style="background:#fff;height:85px;padding-top:12px" :left-options="{showBack:false}">
      <div
        slot="left"
        style="font-size:1.3em;color:#000;padding-top:18px;"
      >要求：{{this.$store.state.teacher.currentSeminar.introduction}}</div>
    </x-header>
    <x-header style="background:#eee;height:85px;padding-top:12px" :left-options="{showBack:false}">
      <div slot="left" style="font-size:1.3em;color:#000;padding-top:18px;">课程情况：&emsp;&emsp;未开始</div>
      <p
        slot="right"
        @click="edit"
        style="text-decoration:underline;color:#1AAD19;font-size:1.1em"
      >查看信息</p>
    </x-header>

    <x-button @click="presentation" type="primary" style="margin-top:100px;color:#fff">开始讨论课</x-button>
  </div>
</template>

<script>
import { XHeader, Cell, Group, CellBox, XButton } from "vux";
export default {
  components: {
    XHeader,
    Cell,
    CellBox,
    Group,
    XButton
  },
  data() {
    return {};
  },
  methods: {
    onClick() {
      console.log("on click");
    },
    back: function() {
      this.$router.push("/mobile/teacher/seminars");
    },
    more: function() {},
    presentation:function(){
        this.$router.push('')
    }
  }
};
</script>
