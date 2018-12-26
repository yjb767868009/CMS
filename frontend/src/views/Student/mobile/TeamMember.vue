<template>
<div class="student" style="height:20px;background:#fff">
    <x-header title="OOAD 2016-1" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    
    
        <cell :border-intent="false" value-align="left" style="height:20px"><span style="padding-left:100px;font-size:1.2em">1-1 中华第一皮</span></cell>
        <cell :border-intent="false" value-align="left" title="组长：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;22222222 &emsp;zzz</span></cell>
        <cell :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;33333333 &emsp;kkk</span></cell>
        <cell :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:68px">&emsp;37777777 &emsp;mmm</span></cell>
        <cell :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:68px">&emsp;37788888 &emsp;www</span></cell>
    
    

        <flexbox style="margin-top:30px">
            <flexbox-item>
                <x-button type="warn"  @click.native="leave=!leave">退出小组</x-button>
            </flexbox-item>
        </flexbox>
    <div v-transfer-dom>
      <confirm v-model="leave"
        title="提示"
        theme="android"
        @on-cancel="onCancel"
        @on-confirm="onConfirm">
        <p style="text-align:center;">确定退出该组吗</p>
      </confirm>
      
      <popup v-model="show" height="15%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div></cell>
          </div>
      </popup>
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
import {TransferDom,XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup,CheckIcon,Flexbox,FlexboxItem,Box,Confirm} from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,
        Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup,CheckIcon,Flexbox,FlexboxItem,Box,Confirm
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            show:false,
            student1:false,
            student2:false,
            student3:false,
            leave:false,
        }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        onCancel:function(){
            console.log('取消')
        },
        onConfirm:function(){
            console.log('确认')
        },
    }
        
  };
</script>