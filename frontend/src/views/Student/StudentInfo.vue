<template>
<div class="student" style="height:370px;background:#eee">
    <x-header title="我" style="height:60px;padding-top:12px" :left-options="{showBack:false}"  :right-options="{showMore: true}" @on-click-more="showMenus = true">
    </x-header>
    <x-header align="right" style="background:#fff;margin:20px 0px 0px;height:90px;padding-top:12px border:1" :left-options="{showBack:false}">
         <div slot="right" style="font-size:1.3em;color:#000;">www&emsp;</div>
         <p slot="right" style="font-size:1.3em;color:#000">25120152202348&emsp;</p>
    </x-header>
    <group>
      <cell is-link title="我的课程" link="/setAccount"><img slot="icon" src="../../assets/lock.png" style="display:block;margin-right:12px;margin-left:3px" width="15px" height="20px"/></cell>
      <cell is-link title="账户与设置" link="/studentSeminar"><img slot="icon" src="../../assets/package.png" style="display:block;margin-right:10px;" width="20px" height="20px"/></cell>
    </group>
    <div v-transfer-dom>
      <actionsheet :menus="menus" v-model="showMenus"></actionsheet>
    </div>
</div>
</template>

<style>
    .vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import axios from 'axios'
import {XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem} from 'vux'
  export default {
    components:{
        XHeader,
        XButton,
        Group,
        Cell,
        Actionsheet,
        ButtonTab,
        ButtonTabItem
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
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
        }
    }
        
  };
</script>