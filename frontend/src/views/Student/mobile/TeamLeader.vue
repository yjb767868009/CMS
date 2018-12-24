<template>
<div class="student" style="height:20px;background:#fff">
    <x-header title="OOAD 2016-1" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    
    
        <cell :border-intent="false" value-align="left" style="height:20px"><span style="padding-left:100px;font-size:1.2em">1-1 中华第一皮</span></cell>
        <cell :border-intent="false" @click.native="Delete=!Delete"  value-align="left" title="组长：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;22222222 &emsp;zzz</span></cell>
        <cell :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;33333333 &emsp;kkk</span></cell>
        <cell :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:68px">&emsp;37777777 &emsp;mmm</span></cell>
        <cell :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:68px">&emsp;37788888 &emsp;www</span></cell>
    

        <cell :border-intent="false" value-align="left" title="添加成员：" style="height:20px"><span style="color:#000;padding-left:20px"></span></cell>
        <check-icon :value.sync="student1" @on-change="onClick" style="color:#000;padding-left:15px"> &emsp;&emsp;&emsp;11111111 &emsp;aaa&emsp;&emsp;<span v-if="student1">我俏丽吗</span></check-icon>
        <check-icon :value.sync="student2" style="color:#000;padding-left:15px">  &emsp;&emsp;&emsp;22222222 &emsp;bbb&emsp;&emsp;<span v-if="student2">我俏丽吗</span></check-icon>
        <check-icon :value.sync="student3" style="color:#000;padding-left:15px">  &emsp;&emsp;&emsp;33333333 &emsp;ccc&emsp;&emsp;<span v-if="student3">我俏丽吗</span></check-icon>
    
        <cell :border-intent="false" value-align="left" title="搜索成员：" style="margin-top:30px;padding-top:20px;height:20px;padding-right:30px"><el-input placeholder="请输入成员学号或姓名"></el-input></cell>
        
    

        <flexbox style="margin-top:30px">
            <flexbox-item>
                <x-button type="warn"  @click.native="dissolved=!dissolved">解散小组</x-button>
            </flexbox-item>
            <flexbox-item>
                <x-button type="default"  @click.native="add=!add">添加</x-button>
            </flexbox-item>
            <flexbox-item>
                <x-button type="primary" @click.native="save=!save">保存</x-button>
            </flexbox-item>
        </flexbox>

        <!-- invalid组申请 -->

        <!-- <flexbox style="margin-top:30px">
            <flexbox-item>
                <x-button type="primary" @click.native="application=!application">提交申请</x-button>
            </flexbox-item>
        </flexbox> -->
            
    <div v-transfer-dom>
        <confirm v-model="application"
      show-input
      title="申请理由"
      @on-cancel="onCancel"
      @on-confirm="onConfirm">
      </confirm>
      <confirm v-model="dissolved"
        title="提示"
        theme="android"
        @on-cancel="onCancel"
        @on-confirm="onConfirm">
        <p style="text-align:center;">确定解散该组吗</p>
      </confirm>
        
        <confirm v-model="add"
        :show-cancel-button="false"
        title="提示"
        @on-confirm="onConfirm">
        <p style="text-align:center;">添加成功</p>
      </confirm>

      <confirm v-model="save"
        :show-cancel-button="false"
        title="提示"
        @on-confirm="onConfirm">
        <p style="text-align:center;">保存成功</p>
      </confirm>

      <confirm v-model="Delete"
        title="提示"
        theme="android"
        @on-cancel="onCancel"
        @on-confirm="onConfirm">
        <p style="text-align:center;">确定删除该组员吗</p>
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
            dissolved:false,
            add:false,
            save:false,
            Delete:false,
            application:false,
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