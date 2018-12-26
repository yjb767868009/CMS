<template>
<div class="student" style="height:20px;background:#fff">
    <x-header :title="this.$store.state.student.currentCourse.name" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group>
        
        
        <cell is-link :border-intent="false" :arrow-direction="showMyContent ? 'up' : 'down'"
         @click.native="showMyContent = !showMyContent" value-align="left">
        <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><span style="color:#000">{{myteam.name}}</span> (我的小组)
        </cell>
            <template v-if="showMyContent">
                <cell :border-intent="false"  value-align="left" title="组长：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{myteam.leader.account}} &emsp;{{myteam.leader.name}}</span></cell>
                <template v-for="memb in myteam.members">
                <cell :key="memb.id" :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{memb.account}} &emsp;{{memb.name}}</span></cell>
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
      
        <template v-for="team in teams">
        <cell :key="team.leader.id" is-link :border-intent="false" :arrow-direction="team.showMemberContent ? 'up' : 'down'"
         @click.native="team.showMemberContent = !team.showMemberContent" value-align="left">
        
        <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/>
        <span style="color:#000">{{team.name}}</span> {{team.invalid}}
        </cell>

            <template v-if="team.showMemberContent">
                <cell :border-intent="false" value-align="left" title="组长：" style="height:20px"><span style="color:#000">&emsp;{{team.leader.account}} &emsp;{{team.leader.name}}</span></cell>
                <template v-for="member in team.members">
                <cell :key="member.id" :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000">&emsp;{{member.account}}&emsp;{{member.name}}</span></cell>
                </template>
            </template>
        </template>
         <cell is-link :border-intent="false" :arrow-direction="showNoTeam ? 'up' : 'down'"
         @click.native="getNoTeam" value-align="left">
        
        <img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/>
        <span style="color:#000">未组队学生</span>
        </cell>

            <template v-if="showNoTeam">
                <template v-for="stu in noteam">
                <cell :key="stu.id" :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;{{stu.account}} &emsp;{{stu.name}}</span></cell>
                </template>
            </template>
        
        <flexbox style="margin-top:30px">
            <flexbox-item>
                <template v-if="this.$store.state.student.currentCourse.myteam==null">
                <x-button type="primary"  @click.native="newteam">创建小组</x-button>
                </template>
                <template v-if="this.$store.state.student.currentCourse.myteam!=null">
                <x-button type="primary"  @click.native="myteam">我的小组</x-button>
                </template>
            </flexbox-item>
        </flexbox>
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
    "name": "张三"
  },
  {
    "id": "002",
    "account": "222222",
    "name": "张四"
  }
],
            teams:[
  {
    "showMemberContent":false,
    "name": "1-33 早早早鸟小组",
    "valid": true,
    "leader": {
      "id": "007",
      "account": 1218,
      "name": "张七"
    },
    "members": [
      {
        "id": "008",
        "account": 1234,
        "name": "李八"
      },
      {
        "id": "009",
        "account": 1246,
        "name": "王九"
      }
    ]
  },
  {
    "showMemberContent":false,
    "name": "1-34 早鸟小组",
    "valid": true,
    "leader": {
      "id": "004",
      "account": 112,
      "name": "三张"
    },
    "members": [
      {
        "id": "005",
        "account": 132,
        "name": "四李"
      },
      {
        "id": "006",
        "account": 142,
        "name": "五王"
      }
    ]
  }
]
        }
    },
    // mounted:function(){
    //     this.$axios.get('/course/'+this.$store.state.student.currentCourse.id+'/team')
    //     .then((response)=>{
    //         this.teams=response.data;
    //         for(var i=0;i<this.teams.length;i++){
    //             this.teams[i].showMemberContent=false;
    //         }
    //     })
    //         this.$axios.get('/course/'+this.$store.state.student.currentCourse.id+'/noteam')
    //     .then((response)=>{
    //         this.noteam=response.data;
    //         for(var i=0;i<this.noteam.length;i++){
    //             this.noteam[i].showNoTeam=false;
    //         }
    //     });
    // },
        
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
            this.showNoTeam = !this.showNoTeam;
            // this.$axios.get('/course/'+this.$store.state.student.currentCourse.id+'/noTeam')
            // .then((response)=>{
            //     this.noteam=response.data;
            // })
        },
        myteam:function(){
            this.$router.push('/mobile/student/teamed')
        }
    }
        
  };
</script>