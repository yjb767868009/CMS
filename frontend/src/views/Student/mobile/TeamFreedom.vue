<template>
<div class="student" style="height:20px;background:#fff">
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <flexbox style="margin-top:30px">
            <flexbox-item>
                <template v-if="teamInfo.myTeam==null">
                <x-button type="primary"  @click.native="newteam">创建小组</x-button>
                </template>
                <template v-if="teamInfo.myTeam!=null">
                <x-button type="primary"  @click.native="myteam">我的小组</x-button>
                </template>
            </flexbox-item>
    </flexbox>
    <cell is-link :border-intent="false" :arrow-direction="showNoTeam ? 'up' : 'down'"
         @click.native="getNoTeam" value-align="left" style="margin-top:20px">
        
        <img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/>
        <span style="color:#000">未组队学生</span>
        </cell>

            <template v-if="showNoTeam">
                <template v-for="stu in noteam">
                <cell :key="stu.id" :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{stu.account}} &emsp;{{stu.name}}</span></cell>
                </template>
            </template>
    <group>
        <template v-if="teamInfo.myTeam!=null">
        <cell is-link :border-intent="false" :arrow-direction="showMyContent ? 'up' : 'down'"
         @click.native="getmyteam(teamInfo.myTeam)" value-align="left">
        <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><span style="color:#000">{{this.teamInfo.myTeam.teamName}}</span> (我的小组)
        </cell>
            <template v-if="showMyContent">
                <cell :border-intent="false"  value-align="left" title="组长：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{this.teamInfo.myTeam.members.leader.account}} &emsp;{{this.teamInfo.myTeam.members.leader.name}}</span></cell>
                <template v-for="memb in teamInfo.myTeam.members.members">
                <cell :key="memb.id" :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{memb.account}} &emsp;{{memb.name}}</span></cell>
                </template>    
            </template>
        </template>
        <!-- 连接好后端后我的小组信息显示代码，小组信息登录后保存在内存中，组队更新时内存也刷新 -->

        <!-- <cell is-link :border-intent="false" :arrow-direction="showMyContent ? 'up' : 'down'"
         @click.native="showMyContent = !showMyContent" value-align="left">
        <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><span style="color:#000">{{this.$store.state.student.myteam.name}}</span> (我的小组)
        </cell>

            <template v-if="showMyContent">
                <cell :border-intent="false" value-align="left" title="组长：" style="height:20px"><span style="color:#000">&emsp;{{this.$store.state.student.myteam.leader.account}} &emsp;{{this.$store.state.student.myteam.leader.account}}</span></cell>
                <template v-for="member in this.$store.state.student.myteam.members">
                <cell :key="member.id" :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000">&emsp;{{member.account}}&emsp;{{member.name}}</span></cell>
                </template>
            </template> -->
      
        <template v-for="team in teamInfo.teams">
        <cell :key="team.teamId" is-link :border-intent="false" :arrow-direction="team.showMemberContent ? 'up' : 'down'"
         @click.native="getmembers(team)" value-align="left">
        
        <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/>
        <span style="color:#000">{{team.teamName}}</span> <span v-if="!team.valid" style="color:#E80000">invalid</span>
        </cell>

            <template v-if="team.showMemberContent">
                <cell :border-intent="false" value-align="left" title="组长：" style="height:20px"><span style="color:#000">&emsp;{{team.members.leader.account}}&emsp;{{team.members.leader.name}}</span></cell>
                <template v-for="member in team.members.members">
                <cell :key="member.id" :border-intent="false" value-align="left" title="组员：" style="height:20px;"><span style="color:#000">&emsp;{{member.account}}&emsp;{{member.name}}</span></cell>
                </template>
            </template>
        </template>
         
        
        
    </group>
    
    <div v-transfer-dom>
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
        ButtonTabItem, CellBox, CellFormPreview,Popup,Flexbox,FlexboxItem} from 'vux'
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
        ButtonTabItem, CellBox, CellFormPreview,Popup,Flexbox,FlexboxItem
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            showMyContent:false,
            showNoTeam:false,
            show:false,
            noteam:'',
            teamInfo:'',
            gotten:false,
        }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/team')
        .then((response)=>{
            this.teamInfo=response.data;
            this.$store.state.student.Myteam=this.teamInfo.myTeam;
            for(var i=0;i<this.teamInfo.teams.length;i++){
                this.$set(this.teamInfo.teams[i],'showMemberContent',false);
            }
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
        newteam:function(){
            this.$router.push('/mobile/student/newTeam')
        },
        getNoTeam:function(){
            this.showNoTeam=!this.showNoTeam
            if(!this.gotten){
                this.gotten=true
            this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/noteam')
        .then((response)=>{
            this.noteam=response.data;
        });}
        },
        myteam:function(){
            this.$router.push('/mobile/student/teamed')
        },
        getmembers(currentteam){
            currentteam.showMemberContent = !currentteam.showMemberContent
            this.$axios.get('/team/'+currentteam.teamId)
            .then((response)=>{
                this.$set(currentteam,'members','')
                currentteam.members=response.data.team
            })
        },
        getmyteam:function(currentteam){
            this.showMyContent = !this.showMyContent
            this.$axios.get('/team/'+currentteam.teamId)
            .then((response)=>{
                this.$set(currentteam,'members','')
                currentteam.members=response.data.team
            })
        }
    }
        
  };
</script>