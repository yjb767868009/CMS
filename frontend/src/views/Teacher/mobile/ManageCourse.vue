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
  </div>
</template>

<script>
import {XHeader,Cell,CellBox,Group} from 'vux'
export default {
    components:{
        XHeader,
        Cell,
        CellBox,
        Group
    },
    data(){
        return{
            showContent001: false,
            showContent002: false
        }   
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        back:function(){
            this.$router.push('/mobile/teacher')
        }
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