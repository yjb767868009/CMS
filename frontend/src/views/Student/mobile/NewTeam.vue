<template>
<div class="student" style="height:20px;background:#fff">
    <x-header title="OOAD 2016-1" style="height:60px;padding-top:12px" :right-options="{showMore: true}" @on-click-more="onClick">
    </x-header>
    
    
        <cell :border-intent="false" title="小组名：" value-align="left" style="padding-top:18px;height:30px;padding-right:50px"><el-input style="padding-left:20px;font-size:1.2em;"></el-input></cell>
        <group>
        <cell is-link :border-intent="false" :arrow-direction="showContent002 ? 'up' : 'down'"
         @click.native="showContent002 = !showContent002" value-align="left">
        <span style="color:#000">选择班级：&emsp;&emsp;&emsp;{{klass}}</span>
        </cell>
        </group>

            <template v-if="showContent002">
                <cell-box :border-intent="false" class="sub-item" style="padding-left:130px" @click.native="klass='2016-1'">2016-1</cell-box>
                <cell-box class="sub-item"  style="padding-left:130px" @click.native="klass='2016-2'">2016-2</cell-box>
                <cell-box class="sub-item"  style="padding-left:130px" @click.native="klass='2016-3'">2016-3</cell-box>
            </template>
    

        <cell :border-intent="false" value-align="left" title="添加成员：" style="margin-top:15px;height:20px"><span style="color:#000;padding-left:20px"></span></cell>
        <check-icon :value.sync="student1" @on-change="onClick" style="color:#000;padding-left:15px"> &emsp;&emsp;&emsp;11111111 &emsp;aaa&emsp;&emsp;<span v-if="student1">我俏丽吗</span></check-icon>
        <check-icon :value.sync="student2" style="color:#000;padding-left:15px">  &emsp;&emsp;&emsp;22222222 &emsp;bbb&emsp;&emsp;<span v-if="student2">我俏丽吗</span></check-icon>
        <check-icon :value.sync="student3" style="color:#000;padding-left:15px">  &emsp;&emsp;&emsp;33333333 &emsp;ccc&emsp;&emsp;<span v-if="student3">我俏丽吗</span></check-icon>
    
        <cell :border-intent="false" value-align="left" title="搜索成员：" style="margin-top:30px;padding-top:20px;height:20px;padding-right:30px">
            <el-input placeholder="请输入成员学号或姓名" v-model="stu"></el-input>
            </cell>
        <check-icon v-if="stu==name" :value.sync="student1" @on-change="onClick" style="color:#000;padding-left:15px">  &emsp;&emsp;&emsp;11111111 &emsp;aaa&emsp;&emsp;<span v-if="student1">我俏丽吗</span></check-icon>

        <flexbox style="margin-top:30px">
            <flexbox-item>
                <x-button type="primary" @click.native="submit=!submit">确认提交</x-button>
            </flexbox-item>
        </flexbox>
    <div v-transfer-dom>
      <confirm v-model="submit"
        :show-cancel-button="false"
        title="提示"
        @on-confirm="onConfirm">
        <p style="text-align:center;">新建队伍成功</p>
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
            name:'aaa',
            newItem:'newItem',
            show:false,
            student1:false,
            student2:false,
            student3:false,
            submit:false,
            showContent002:false,
            klass:"2016-1",
            stu:"",
            stunum:1111111111,
        }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            console.log('ss');
            this.show=true;
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