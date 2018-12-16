<template>
<div class="student" style="height:800px;background:#eee;">
    <x-header title="OOAD-讨论课" style="height:60px;padding-top:12px;font-size:20px"  :right-options="{showMore: true}"  @on-click-more="showMenus = true">
    </x-header>
    <div style="font-size:18px;background:#fff;margin-top:7px;margin-bottom:5px"><cell primary="content" value-align="left">
        <div style="text-align:center;color:#000;height:35px;"><span style="font-size:1.2em">业务流程分析</span>
        </div>
        </cell></div>
    
    <div style="font-size:18px;background:#fff"><cell primary="content" title="第一组：" value-align="left"><div style="padding-left:30px;color:#000;">1-1 业务流程.PPT</div></cell></div>
    <div style="font-size:18px;background:#eee"><cell primary="content" title="第二组：" value-align="left"><div style="padding-left:30px;color:#00DB00;">可报名</div></cell></div>
    <div style="font-size:18px;background:#fff"><cell primary="content" title="第三组：" value-align="left"><div style="padding-left:30px;color:#000;">
        <span style="font-weight:bold;color:#FF3333">1-3 未提交</span></div></cell></div>
    <div style="font-size:18px;background:#eee"><cell primary="content" title="第四组：" value-align="left"><div style="padding-left:30px;color:#00DB00;">可报名</div></cell></div>
    
    <div style="font-size:22px;background:#FF3333;margin-top:70px;height:60px" v-if="ok==ok">
        <cell primary="content" title="" value-align="left">
            <div style="padding-left:130px;padding-top:5px;color:#fff;font-weight:520;" @click="sign">取消报名</div>
        </cell>
    </div>
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
import Vue from 'vue'
import {XHeader,Cell,Actionsheet,
        Alert,Group,AlertPlugin,
        XSwitch,TransferDomDirective as TransferDom 
        } from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        Alert,
        XSwitch,
        Group,
        XHeader,
        Cell,
        Actionsheet,
        AlertPlugin
    },
    data() {
       return{ 
        show2:false,
        menus: {
        menu1: '待办',
        menu2: '个人页',
        menu3: '讨论课'
      },
        showMenus:false,
    }
    },
   methods:{
        showPlugin:function(){
            alert("caonima");        
        },
        toast:function(){
            Toast(this.name);
        },
        sign:function(){
            console.log("aaa");
        },
   }
  }
</script>