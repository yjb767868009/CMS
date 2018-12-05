<template>
<div class="student" style="height:20px;background:#fff">
    <x-header title="OOAD 2016-1" style="height:60px;padding-top:12px" :right-options="{showMore: true}" @on-click-more="showMenus = true">
    </x-header>
    <group title="我的讨论课">
      <cell is-link :border-intent="false" :arrow-direction="showContent001 ? 'up' : 'down'"
      @click.native="showContent001 = !showContent001" value-align="left">
      <span>讨论课信息</span>
      </cell>

        <template v-if="showContent001">
      
        <cell is-link :border-intent="false" :arrow-direction="showContent002 ? 'up' : 'down'"
         @click.native="showContent002 = !showContent002" value-align="left" style="padding-left:150px">
        <span>第一轮</span>
        </cell>

            <template v-if="showContent002">
                <cell-box :border-intent="false" class="sub-item" is-link link="/seminarDetail" style="padding-left:130px">业务流程分析</cell-box>
                <cell-box class="sub-item" is-link style="padding-left:130px">领域模型</cell-box>
            </template>
        
      
        <cell is-link :border-intent="false" :arrow-direction="showContent003 ? 'up' : 'down'"
         @click.native="showContent003 = !showContent003" value-align="left" style="padding-left:150px">
        <span>第二轮</span>
        </cell>

            <template v-if="showContent003">
                <cell-box :border-intent="false" class="sub-item" is-link style="padding-left:130px">代码检查</cell-box>
                <cell-box class="sub-item" is-link style="padding-left:130px">对象模型设计</cell-box>
            </template>
        </template>
        
        <cell is-link :border-intent="false" :arrow-direction="showContent004 ? 'up' : 'down'"
      @click.native="showContent004 = !showContent004" value-align="left" style="margin-top:10px">
      <span>已报名的讨论课</span>
      </cell>

      <template v-if="showContent004">
      
        <cell is-link :border-intent="false" :arrow-direction="showContent005 ? 'up' : 'down'"
         @click.native="showContent005 = !showContent005" value-align="left" style="padding-left:150px">
        <span>第一轮</span>
        </cell>

            <template v-if="showContent005">
                <cell-box :border-intent="false" class="sub-item" is-link style="padding-left:130px">业务流程分析</cell-box>
                <cell-box class="sub-item" is-link style="padding-left:130px">领域模型</cell-box>
            </template>
      
        <cell is-link :border-intent="false" :arrow-direction="showContent006 ? 'up' : 'down'"
         @click.native="showContent006 = !showContent006" value-align="left" style="padding-left:150px;">
        <span>第二轮</span>
        </cell>

            <template v-if="showContent006">
                <cell-box :border-intent="false" class="sub-item" is-link style="padding-left:130px">代码检查</cell-box>
                <cell-box class="sub-item" is-link style="padding-left:130px">对象模型设计</cell-box>
            </template>
      </template>
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
    .weui-cells{
        color:#000;
    }
</style>

<script>
import axios from 'axios'
import {XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview} from 'vux'
  export default {
    components:{
        XHeader,
        XButton,
        Group,
        Cell,
        Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview
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
        showContent001: false,
        showContent002:false,
        showContent003:false,
        showContent004: false,
        showContent005:false,
        showContent006:false,
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