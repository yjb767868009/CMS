<template>
  <div class="course" style="background:#eee">
    <x-header title="课程管理" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group :title="课程">
      <cell
      title="OOAD（主）"
      is-link
      :border-intent="false"
      :arrow-direction="showContent001 ? 'up' : 'down'"
      @click.native="showContent001 = !showContent001"></cell>

        <template v-if="showContent001">
            <cell-box :border-intent="false" class="sub-item" is-link link="">学生成绩</cell-box>
            <cell-box class="sub-item" is-link link="">学生组队</cell-box>
            <cell-box class="sub-item" is-link link="">课程信息</cell-box>
            <cell-box class="sub-item" is-link link="">班级信息</cell-box>
            <cell-box class="sub-item" is-link link="">讨论课设置</cell-box>
            <cell-box class="sub-item" is-link link="">共享设置</cell-box>
        </template>

      <cell
      title="J2EE"
      is-link
      :border-intent="false"
      :arrow-direction="showContent002 ? 'up' : 'down'"
      @click.native="showContent002 = !showContent002"></cell>

        <template v-if="showContent002">
            <cell-box :border-intent="false" class="sub-item" is-link link="">学生成绩</cell-box>
            <cell-box class="sub-item" is-link link="">学生组队</cell-box>
            <cell-box class="sub-item" is-link link="">课程信息</cell-box>
            <cell-box class="sub-item" is-link link="">班级信息</cell-box>
            <cell-box class="sub-item" is-link link="/mobile/teacher/setseminar">讨论课设置</cell-box>
            <cell-box class="sub-item" is-link link=""> 共享设置</cell-box>
        </template>

      <cell title="新建课程" link="/mobile/teacher/createcourse"></cell>
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
            showContent001: false,
            showContent002: false,
            show:false,
        }   
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        back:function(){
            this.$router.push('/mobile/teacher')
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