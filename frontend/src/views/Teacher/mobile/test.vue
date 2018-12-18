<template>
<div class="student" style="height:800px;background:#eee;">
    <x-header title="讨论课正在进行" style="height:60px;padding-top:12px;font-size:20px"  :right-options="{showMore: true}"  @on-click-more="showMenus = true">
    </x-header>
    <div style="font-size:18px;background:#fff;margin-top:7px;margin-bottom:5px"><cell primary="content" value-align="left">
        <div style="text-align:center;color:#000;height:45px;"><span style="font-size:1.2em">业务流程分析</span>
        <p style="margin-top:5px;text-align:right;font-size:0.8em;color:#000">第三组&emsp;展示</p>
        </div>
        </cell></div>
    
    <div style="font-size:18px;background:#fff"><cell primary="content" title="第一组：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;1-1</div></cell></div>
    <div style="font-size:18px;background:#eee"><cell primary="content" title="第二组：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;1-2</div></cell></div>
    <div style="font-size:18px;background:#fff"><cell primary="content" title="第三组：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;<span style="font-weight:bold;color:#FF3333">1-3</span></div></cell></div>
    <div style="font-size:18px;background:#eee"><cell primary="content" title="第四组：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;1-4</div></cell></div>
    
    <group style="font-size:18px;margin-top:50px;background:#fff;">
      <x-switch title="提问：" v-model="show2" style="background:#fff;"></x-switch>
    </group>
    <div v-transfer-dom>
      <alert v-model="show2" title="提问成功"><p>等待老师抽选</p></alert>
    </div>

    <group title="ss">
      <cell title="ss" @click.native="ss" is-link></cell>
    </group>
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
        showPlugin:function() {
            alert("caonima");        
        },

     
        toast:function(){
            Toast(this.name);
        },
        ss:function(){
            this.$vux.alert.show({
  title: 'Vux is Cool',
  content: 'Do you agree?',
})
        } 
    },
  }
</script>