<template>
  <div class="course" style="background:#eee">
    <x-header title="学生组队" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group :title="组队">
      <cell
      title="1-1 hhhhhh"
      is-link
      :border-intent="false"
      :arrow-direction="showContent001 ? 'up' : 'down'"
      @click.native="showContent001 = !showContent001"></cell>

        <template v-if="showContent001">
            <cell-box :border-intent="false" class="sub-item">组长： 111111111 王二</cell-box>
            <cell-box class="sub-item">组员：111111111 ---</cell-box>
            <cell-box class="sub-item" >111111111  ---</cell-box>
            <cell-box class="sub-item" >111111111  ---</cell-box>
            <cell-box class="sub-item" >111111111  ---</cell-box>
            <cell-box class="sub-item" >111111111  ---</cell-box>
        </template>

      <cell
      title="1-2 aaaaa"
      is-link
      :border-intent="false"
      :arrow-direction="showContent002 ? 'up' : 'down'"
      @click.native="showContent002 = !showContent002"></cell>

        <template v-if="showContent002">
            <cell-box :border-intent="false" class="sub-item">111111111 王二</cell-box>
            <cell-box class="sub-item">111111111 ---</cell-box>
            <cell-box class="sub-item" >111111111  ---</cell-box>
            <cell-box class="sub-item" >111111111  ---</cell-box>
            <cell-box class="sub-item" >111111111  ---</cell-box>
            <cell-box class="sub-item" > 111111111  ---</cell-box>
        </template>

    </group>
    <div v-transfer-dom>
      <popup v-model="show" height="22%">
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
import {XHeader,Cell,CellBox,Group,TransferDom,Popup} from 'vux'
export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,Popup
    },
    data(){
        return{
            show:false,
            showContent001: false,
            showContent002: false
        }   
    },
    methods: {
        Undo(){
            this.$router.push('/mobile/teacher')
        },
        TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
        GoSeminar(){
            this.$router.push('/mobile/teacher/seminarP')
        },
    }
}
</script>

<style scoped>
.sub-item {
  color: #888;
}
.slide {
  padding: 0 20px;
  overflow: hidden;
  max-height: 0;
  transition: max-height .5s cubic-bezier(0, 1, 0, 1) -.1s;
}
.animate {
  max-height: 9999px;
  transition-timing-function: cubic-bezier(0.5, 0, 1, 0);
  transition-delay: 0s;
}
</style>