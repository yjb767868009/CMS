<template>
  <div class="course" style="background:#eee">
    <x-header title="班级信息" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <div>
    <div style="height:30px;padding-top:12px" class="box">2016-(1)</div>

    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">讨论课时间：</div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">讨论课地点：</div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">班级学生名单：
        <x-button @click="post1" style="font-size:5px;height:20px;width:90px" mini plain>选择文件</x-button>
    </div>

    <x-button @click="post1" style="margin-top:50px" mini plain type="warn">删除班级</x-button>
    </div>

    <divider>-</divider>

    <div>
    <div style="height:30px;padding-top:12px" class="box">2016-(2)</div>

    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">讨论课时间：</div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">讨论课地点：</div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">班级学生名单：
        <x-button @click="post1" style="font-size:5px;height:20px;width:90px" mini plain>选择文件</x-button>
    </div>

    <x-button @click="post1" style="margin-top:50px" mini plain type="warn">删除班级</x-button>
    </div>

    <divider>-</divider>

    <x-button @click="newclass" type="primary" style="margin-top:20px;color:#fff">新建班级</x-button>
  </div>
</template>

<script>
import {XHeader,XButton,Divider} from 'vux'
export default {
    components:{
        XHeader,
        XButton,
        Divider
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
