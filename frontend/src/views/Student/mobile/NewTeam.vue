<template>
<div class="student" style="height:20px;background:#fff">
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
        <group>
        <cell :border-intent="false" title="小组名：" value-align="left" style="height:30px;padding-right:50px"> 
            <x-input  placeholder="请输入小组名" style="padding-left:60px" v-model="teamName"></x-input>
        </cell>
        </group>

        <group>
        <cell is-link :border-intent="false" :arrow-direction="showContent002 ? 'up' : 'down'"
         @click.native="showContent002 = !showContent002" value-align="left">
        <span style="color:#000">选择班级：&emsp;&emsp;&emsp;{{this.currentklass.name}}</span>
        </cell>
        </group>

            <template v-if="showContent002">
                <template v-for="klass in klasses">
                <cell-box :key="klass.id" :border-intent="false" class="sub-item" style="padding-left:130px" @click.native="chooseKlass(klass)">{{klass.name}}</cell-box>
                </template>
            </template>
    
        <group>
        <cell :border-intent="false" value-align="left" title="搜索成员：" style="height:20px">
        <x-input style="height:20px" placeholder="请输入成员学号或姓名" v-model="searchstu"></x-input>
        </cell>
        </group>
        <template v-for="stu in this.noteam">
        <check-icon :key="stu.id" v-if="(searchstu===stu.account||searchstu===stu.name)" :value.sync="stu.showNoTeam" style="color:#000;padding-left:15px">&emsp;{{stu.account}} &emsp;{{stu.name}}&emsp;&emsp;</check-icon>
        </template>
        <cell :border-intent="false" value-align="left" title="添加成员：" style="margin-top:20px;height:20px"><span style="color:#000;padding-left:20px"></span></cell>
        <template v-for="stu in this.noteam">
            <check-icon :key="stu.id" :value.sync="stu.showNoTeam" style="color:#000;padding-left:15px">&emsp;{{stu.account}} &emsp;{{stu.name}}<span v-if="stu.showNoTeam">&emsp; (｡･∀･)ﾉﾞ</span></check-icon>
        </template>

        <flexbox style="margin-top:30px">
            <flexbox-item>
                <x-button type="primary" @click.native="newTeam">确认提交</x-button>
            </flexbox-item>
        </flexbox>
    <div v-transfer-dom>
      <confirm v-model="submit"
        title="请确认信息"
        @on-confirm="sureNewTeam"
        @on-cancel="onCancel">
        <p style="text-align:center;">队伍名:{{this.teamName}}</p>
        <p>班级：{{this.currentklass.name}}</p>
        <p>组长：<span style="padding-left:5px">{{this.$store.state.student.name}}</span></p>
        <p>小组成员：</p>
        <template v-for="mem in this.newMembersname"><span :key="mem.id" style="padding-left:10px">{{mem.studentId}}</span></template>
      </confirm>
      <confirm v-model="error"
        title="错误"
        :show-cancel-button="false"
        @on-confirm="onConfirm">
        <p style="text-align:center;">请输入小组名</p>
      </confirm>
      <confirm v-model="error1"
        title="错误"
        :show-cancel-button="false"
        @on-confirm="onConfirm">
        <p style="text-align:center;">请选择班级</p>
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
        ButtonTabItem, CellBox, CellFormPreview,Popup,CheckIcon,Flexbox,FlexboxItem,Box,Confirm,XInput} from 'vux'
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
        ButtonTabItem, CellBox, CellFormPreview,Popup,CheckIcon,Flexbox,FlexboxItem,Box,Confirm,XInput
    },
    data() {
        return{
            name:'',
            newItem:'newItem',
            show:false,
            submit:false,
            student1:false,
            showContent002:false,
            currentklass:'',
            searchstu:"",
            klasses:'',
            noteam:'',
            teamName:'',
            error:false,
            newMembers:[],
            error1:false,
            newMembersname:[],
        }
    },
    mounted:function(){
        this.name=this.$store.state.student.name;
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/noteam')
        .then((response)=>{
            this.noteam=response.data;
            for(var i=0;i<this.noteam.length;i++){
                this.$set(this.noteam[i],'showNoTeam',false);
            }
        });
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/class')
        .then((response)=>{
            this.klasses=response.data;
        })
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
            this.newMembers=[]
            this.newMembersname=[]
        },
        onConfirm:function(){
            console.log('确认')
            // this.$router.push('/mobile/Student/teamFreedom')
        },
        newTeam:function(){
            if(this.teamName===''){
                this.error=!this.error;
            }else{
                if(this.currentklass===''){
                    this.error1=!this.error1
                }else{
                for(var i=0;i<this.noteam.length;i++){
                    if(this.noteam[i].showNoTeam===true){
                        this.newMembers.push({'studentId':this.noteam[i].studentId})
                        this.newMembersname.push({'studentId':this.noteam[i].name})
                    }
                }
                this.submit=!this.submit;
                }
            }
        },
        chooseKlass:function(klass){
            this.currentklass=klass
            this.showContent002=!this.showContent002
        },
        sureNewTeam:function(){
            this.$axios.post('/course/'+this.$store.state.student.currentCourse.courseId+'/class/'+this.currentklass.klassId+'/team',this.newMembers.push({teamName:this.teamName}))
            .then((response)=>{
                console.log(response.data)
                this.newMembers=[]
                this.newMembersname=[]
            })
        }
    }
        
  };
</script>