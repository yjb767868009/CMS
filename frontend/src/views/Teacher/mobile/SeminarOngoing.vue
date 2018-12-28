<template>
  <div class="login" title="2016-(1)">
    <x-header :title="this.$store.state.teacher.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <group>
      <cell :title="'主题'" :value="this.$store.state.teacher.currentSeminar.topic"></cell>
      <cell :title="'课次序号'" :value="this.$store.state.teacher.currentKlassSeminar.klass.klassId"></cell>
      <x-textarea :title="'要求'" :show-counter="false" :placeholder="this.$store.state.teacher.currentSeminar.introduction" disabled></x-textarea>
      <cell :title="'课程情况'">正在进行&emsp;&emsp;&emsp;<a style="color:#1AAD19;text-decoration:underline" @click="checkInfo">查看信息</a></cell>
      <x-button @click.native="presentation" type="primary" style="margin-top:18px;color:#fff">进入讨论课</x-button>
    </group>

    
    
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
    checkInfo:function(){
      this.$router.push('/mobile/teacher/checkPPT')
    },
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
