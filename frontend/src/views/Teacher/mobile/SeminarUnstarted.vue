<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD讨论课" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <group>
      <cell :title="'主题'" :value="this.$store.state.teacher.currentSeminar.topic"></cell>
      <cell :title="'课次序号'" :value="this.$store.state.teacher.currentKlassSeminar.klass.klassId"></cell>
      <x-textarea :title="'要求'" :show-counter="false" :placeholder="this.$store.state.teacher.currentSeminar.introduction" disabled></x-textarea>
      <cell :title="'课程情况'" value="未开始"></cell>
      <x-button @click="edit" type="primary" style="margin-top:100px;color:#fff">查看信息</x-button>
      <x-button @click="presentation" type="primary" style="margin-top:18px;color:#fff">开始讨论课</x-button>
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
import { XHeader, Cell, Group, CellBox, XButton ,TransferDom,Popup,XTextarea} from "vux";
export default {
  directives:{
    TransferDom
  },
  components: {
    XHeader,
    Cell,
    CellBox,
    Group,
    XButton,Popup,XTextarea
  },
  data() {
    return {
      show:false,
    };
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
