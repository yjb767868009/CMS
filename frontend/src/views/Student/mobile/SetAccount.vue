<template>
<div class="student" style="height:800px;background:#eee;">
    <x-header title="账户与设置" style="height:60px;padding-top:12px;font-size:20px"  :right-options="{showMore: true}"  @on-click-more="showMenus = true">
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
    <x-header style="background:#FF3333;height:60px;padding-top:11px;margin-top:20px" :left-options="{showBack:false}">
         <div  style="text-align:center;font-size:1.3em;color:#000;padding-top:0px;color:#fff" @click="edit">退出登录</div>
    </x-header>
    
    <div v-transfer-dom>
      <actionsheet :menus="menus" v-model="showMenus"></actionsheet>
    </div>
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
.vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import axios from 'axios'
import {XHeader,
        XButton,GroupTitle,Cell,Picker,Actionsheet,
        ButtonTab,
        ButtonTabItem} from 'vux'
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
        Picker,
        Actionsheet,
        ButtonTab,
        ButtonTabItem
    },
    data() {
       return{ 
        hours:[hours],
        hour1:['8'],
        menus: {
        menu1: '待办',
        menu2: '个人页',
        menu3: '讨论课'
      },
        showMenus:false,
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