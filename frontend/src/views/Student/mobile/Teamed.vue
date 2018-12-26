<template>
<div class="student" style="height:20px;background:#fff">
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
        <cell :border-intent="false" value-align="left" style="height:20px"><span style="padding-left:100px;font-size:1.2em">{{myteam.name}}</span></cell>
        <cell :border-intent="false"  value-align="left" title="组长：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{myteam.leader.account}} &emsp;{{myteam.leader.name}}</span></cell>
        
        <!-- 组长点击人名可以选择删除组员 -->
        <template v-if="isleader">
            <template v-for="memb in myteam.members">
            <cell :key="memb.id" :border-intent="false" @click.native="Delete=!Delete"  value-align="left" title="组员：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{memb.account}} &emsp;{{memb.name}}</span></cell>
            </template>
        </template>

        <template v-if="!isleader">
            <template v-for="memb in myteam.members">
            <cell :key="memb.id" :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{memb.account}} &emsp;{{memb.name}}</span></cell>
            </template>
        </template>

        <cell :border-intent="false" value-align="left" title="添加成员：" style="height:20px"><span style="color:#000;padding-left:20px"></span></cell>
        <template v-for="stu in noteam">
        <check-icon  :key="stu.id" :value.sync="stu.showNoTeam" style="color:#000;padding-left:15px"> &emsp;&emsp;&emsp;{{stu.account}} &emsp;{{stu.name}}&emsp;&emsp;<span v-if="stu.showNoTeam">我俏丽吗</span></check-icon>
        </template>
            <cell :border-intent="false" value-align="left" title="搜索成员：" style="margin-top:30px;padding-top:20px;height:20px;padding-right:30px"><el-input placeholder="请输入成员学号或姓名"></el-input></cell>
        
    
        <template v-if="isleader">
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
            <template v-if="valid">
                <flexbox style="margin-top:30px">
                    <flexbox-item>
                        <x-button type="primary" @click.native="application=!application">提交申请</x-button>
                    </flexbox-item>
                </flexbox>
            </template>
        </template>

        <template v-if="!isleader">
            <flexbox style="margin-top:30px">
                <flexbox-item>
                    <x-button type="warn"  @click.native="leave=!leave">退出小组</x-button>
                </flexbox-item>
            </flexbox>
        </template>
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
            dissolved:false,
            add:false,
            save:false,
            Delete:false,
            application:false,
            leave:false,
            isleader:false,
            valid:false,
            myteam:{
    "name": "1-32 早早鸟小组",
    "valid": true,
    "leader": {
      "id": "001",
      "account": 121,
      "name": "张三"
    },
    "members": [
      {
        "id": "002",
        "account": 123,
        "name": "李四"
      },
      {
        "id": "003",
        "account": 124,
        "name": "王五"
      }
    ]
  },
            noteam:[
  {
    "id": "001",
    "account": "111111",
    "name": "张三",
    "showNoTeam":false,
  },
  {
    "id": "002",
    "account": "222222",
    "name": "张四",
    "showNoTeam":false,
  }
]
        }
    },
    mounted:function(){
        // this.$axios.get('/course/'+this.$store.state.student.currentCourse.id+'/noteam')
        // .then((response)=>{
        //     this.noteam=response.data;
        //     for(var i=0;i<this.noteam.length;i++){
        //         this.noteam[i].showNoTeam=false;
        //     }
        // })
        if(this.$store.state.student.myteam.leader.account===this.$store.state.student.myaccount){
            this.isleader=true
        }else if(this.$store.state.student.myteam.leader.account!=this.$store.state.student.myaccount){
            this.isleader=false
        }
        this.valid=this.$store.state.student.myteam.valid;
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