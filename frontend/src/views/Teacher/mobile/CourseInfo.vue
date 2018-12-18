<template>
  <div class="course" style="background:#eee">
    <x-header title="课程信息" style="background-color:#35495e;height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="onClick" style="background:0;height:0px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
        <button @click="close" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <div style="height:30px;padding-top:12px;text-align:left" class="box">课程要求</div>
    <divider></divider>
    <div style="height:30px;padding-top:12px;text-align:left" class="box">
        成绩计算规则：
        <div style='text-align:right'>课堂展示</div>
        <div style='text-align:right'>课堂提问</div>
        <div style='text-align:right'>书面报告</div>
    </div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:50px" class="box">小组人数：</div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">组队开始时间：</div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">组队截止时间：</div>

    <x-button @click="post1" style="margin-top:100px;margin-left:50%" mini plain type="warn">删除课程</x-button>
  </div>
</template>

<script>
import {XHeader,XButton} from 'vux'
export default {
    components:{
        XHeader,
        XButton
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
