<template>
  <div class="course" style="background:#eee">
    <x-header title="班级信息" style="background-color:#35495e;height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="onClick" style="background:0;height:0px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
        <button @click="close" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>


    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">班级名：
        <input  style="margin-left:20%;width:50px;height:30px" placeholder="">年级</input>
        <input  style="margin-left:5%;width:50px;height:30px" placeholder="">班级</input>
    </div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">讨论课时间：
        <input  style="margin-left:20%;width:50px;height:30px" placeholder="星期"></input>
        <input  style="margin-left:5%;width:50px;height:30px" placeholder="节次"></input>
    </div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">讨论课地点：
        <input  style="margin-left:20%;width:100px;height:30px" placeholder="地点"></input>
    </div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">班级学生名单：
        <x-button @click="post1" style="font-size:5px;height:20px;width:90px" mini plain>选择文件</x-button>
    </div>






    <x-button @click="newclass" type="primary" style="margin-top:100px;color:#fff">保存</x-button>
  </div>
</template>

<script>
import {XHeader,XButton,Divider,Group,DatetimeRange} from 'vux'
export default {
    components:{
        XHeader,
        XButton,
        Divider,
        Group,
        DatetimeRange
    },
    methods: {
    onChange (val) {
      console.log('change', val)
        }
    },
    data () {
        return {
            value: ['周一-第一节-第十节']
        }
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
