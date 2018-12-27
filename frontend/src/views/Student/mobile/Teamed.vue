<template>
<div class="student" style="height:20px;background:#fff">
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
        <cell :border-intent="false" value-align="left" style="height:20px"><span style="padding-left:130px;font-size:1.2em">{{this.$store.state.student.Myteam.teamName}}</span></cell>
        <cell :border-intent="false"  value-align="left" title="组长：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{this.teaminfo.team.leader.account}} &emsp;{{this.teaminfo.team.leader.name}}</span></cell>
        {{this.teaminfo.team.valid}}
        <!-- 组长点击人名可以选择删除组员 -->
        <template v-if="teaminfo.role==='leader'">
            <template v-for="memb in this.teaminfo.team.members">
            <cell :key="memb.id" :border-intent="false" @click.native="deleting(memb.account)"  value-align="left" title="组员：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{memb.account}} &emsp;{{memb.name}}</span></cell>
            </template>
        </template>

        <template v-if="teaminfo.role==='member'">
            <template v-for="memb in teaminfo.team.members">
            <cell :key="memb.id" :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{memb.account}} &emsp;{{memb.name}}</span></cell>
            </template>
        </template>
        <template v-if="teaminfo.role==='leader'">
        <cell :border-intent="false" value-align="left" title="添加成员：" style="height:20px"><span style="color:#000;padding-left:20px"></span></cell>
        <template v-for="stu in noteam">
        <check-icon  :key="stu.id" :value.sync="stu.showNoTeam" style="color:#000;padding-left:15px"> &emsp;{{stu.account}} &emsp;{{stu.name}} &emsp;<span v-if="stu.showNoTeam">(｡･∀･)ﾉﾞ</span></check-icon>
        </template>
            <cell :border-intent="false" value-align="left" title="搜索成员：" style="margin-top:30px;padding-top:20px;height:20px;padding-right:30px"><el-input placeholder="请输入成员学号或姓名"></el-input></cell>
        </template>
        <template v-if="teaminfo.role==='leader'">
        <flexbox style="margin-top:30px">
            <flexbox-item>
                <x-button type="warn"  @click.native="dissolved=!dissolved">解散小组</x-button>
            </flexbox-item>
            <flexbox-item>
                <x-button type="default"  @click.native="addmember">添加</x-button>
            </flexbox-item>
            <flexbox-item>
                <x-button type="primary" @click.native="save=!save">保存</x-button>
            </flexbox-item>
        </flexbox>
        <!-- invalid组申请 -->
            <template v-if="teaminfo.team.valid===false">
                <flexbox style="margin-top:30px">
                    <flexbox-item>
                        <x-button type="primary" @click.native="application=!application">提交申请</x-button>
                    </flexbox-item>
                </flexbox>
            </template>
        </template>

        <template v-if="teaminfo.role==='member'">
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
      @on-cancel="Return"
      @on-confirm="applic">
      </confirm>
      <confirm v-model="dissolved"
        title="提示"
        theme="android"
        @on-cancel="Return"
        @on-confirm="Broken">
        <p style="text-align:center;">确定解散该组吗</p>
      </confirm>
        
        <confirm v-model="add"
        title="请确认添加信息"
        @on-cancel="onCancelnew"
        @on-confirm="onConfirm">
        <p style="text-align:center;">添加组员：</p>
        <template v-for="mem in this.newMembers"><span style="padding-left:10px">{{mem}}</span></template>
      </confirm>
        <confirm v-model="nomember"
        :show-cancel-button="false"
        title="错误"
        @on-confirm="add">
        <p style="text-align:center;">请选择组员</p>
     </confirm>
      <confirm v-model="save"
        :show-cancel-button="false"
        title="提示"
        @on-confirm="saving">
        <p style="text-align:center;">保存成功</p>
      </confirm>

      <confirm v-model="Delete"
        title="提示"
        theme="android"
        @on-cancel="Return"
        @on-confirm="deleteMem">
        <p style="text-align:center;">确定删除该组员吗</p>
      </confirm>
      <confirm v-model="leave"
        title="提示"
        theme="android"
        @on-cancel="Return"
        @on-confirm="leaveGroup">
        <p style="text-align:center;">确定退出该组吗</p>
      </confirm>

      <popup v-model="show" height="15%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div></cell>
          </div>
      </popup>
    </div>
    {{this.newMembers}}
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
import { notEqual } from 'assert';
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
            teaminfo:'',
            myteam:'',
            noteam:'',
            newMembers:{},
            nomember:false,
            memberWillBeDe:'',
        }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/noteam')
        .then((response)=>{
            this.noteam=response.data;
            for(var i=0;i<this.noteam.length;i++){
                this.$set(noteam[i],'showNoTeam',false);
            }
        })
        this.$axios.get('/team/'+this.$store.state.student.Myteam.teamId)
        .then((response)=>{
            this.teaminfo=response.data
        })
    },
    methods:{
        //把当前确认的人保存在session中，待维护
        saving:function(){
            console.log('保存')
            this.$store.state.student.newMembers=this.newMembers
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        //将newMember中保存的人清空
        onCancelnew:function(){
            console.log('取消')
            this.newMembers={}
        },
        Return:function(){
            console.log('返回')
        },
        onConfirm:function(){
            console.log('添加')
            this.axios.post('/team/'+this.teaminfo.team.teamId,{})  //学生List待完成
            .then((response)=>{
                console.log(response.data)
            })
            // this.$router.push('/mobile/Student/teamFreedom')
        },
        //addmember函数用于将已勾选的学生加入到newmember列表当中用于post
        addmember:function(){
            for(var i=0;i<this.noteam.length;i++){
                if(this.noteam[i].showNoTeam){
                    this.newMembers[this.noteam[i].studentId]=this.noteam[i].name
                }
            }
            if(JSON.stringify(this.newMembers)=='{}'){
                this.nomember=!this.nomember
            }else{
                this.add=!this.add
            }
        },
        Broken:function(){
            console.log('解散')
            this.$axios.delete('/team/'+this.teaminfo.team.teamId)
            .then((response)=>{
                console.log(response.data)
            })
        },
        deleteMem:function(){
            console.log('确认删除')
            this.$axios.put('/team/'+this.teaminfo.team.teamId,{})
            .then((response)=>{
                console.log(response.data)
            })
        },
        applic:function(){
            console.log('申请')
            // this.$axios.post('/team/'+this.teaminfo.team.teamId,{})
            // .then((response)=>{
            //     console.log(response.data)
            // })
        },
        leaveGroup:function(){
            console.log('离开小组')
            //不太清楚到底调用那个接口
            // this.$router.push('/mobile/Student/teamFreedom')
        },
        //这里将要删除的组员信息存在memberAC中
        deleting:function(memberAC){
            this.Delete=!this.Delete
            this.memberWillBeDe=memberAC
            console.log(memberAC)
        }
    }
        
  };
</script>