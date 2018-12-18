<template>
<div class="student" style="background:#eee;">
    <x-header title="账户与设置" style="height:60px;padding-top:12px;font-size:20px">
        <button @click="onClick" style="background:0;height:30px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <x-header style="background:#fff;margin:20px 0px 0px;height:60px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:6px;">姓名：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;www</div>
    </x-header>
    <x-header style="background:#eee;height:60px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:6px;">学号：&emsp;&emsp;&emsp;25120152202348</div>
    </x-header>
    <x-header style="background:#fff;height:85px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:18px;">联系方式(邮箱)：&emsp;&emsp;135768543@qq.com</div>
         <p slot="right" @click="edit" style="text-decoration:underline;color:#DC143C;font-size:1.1em;padding-top:30px">修改</p>
    </x-header>
    <cell is-link title="账户密码" link="/StudentInfo" style="font-size:1.1em;padding-left:18px"></cell>
    <x-header style="background:#fff;height:85px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:18px;">管理员邮箱：&emsp;&emsp;86738468@qq.com</div>
    </x-header>
    <group-title style="margin-top:20px">通知发送时间&emsp;{{hour1}}</group-title>
    <picker :data='hours' v-model='hour1' @on-change='change' style="margin-top:15px"></picker>
    <x-header style="background:#FF3333;height:60px;padding-top:11px;margin-top:50px" :left-options="{showBack:false}">
         <div  style="text-align:center;font-size:1.3em;color:#000;padding-top:0px;color:#fff" @click="edit">退出登录</div>
    </x-header>
</div>
</template>

<style>
.vux-header-back{
    padding:9px;
    font-size:1.1em;
  }
.left-arrow{
    margin-top:10px;
}
</style>


<script>
import axios from 'axios'
import {XHeader,
        XButton,GroupTitle,Cell,Picker} from 'vux'
let hours=[]
for (var i = 0; i <= 24; i++) {
  hours.push({
    name: i + 'h',
    value: i + ''
  })
}
  export default {
    components:{
        XHeader,
        XButton,
        GroupTitle,
        Cell,
        Picker
    },
    data() {
       return{ 
        hours:[hours],
        hour1:['8']
    }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            console.log("sss");
        },
        edit:function(){
            this.$router.push('/StudentInfo')
        }
    }
        
  };
</script>