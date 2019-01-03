<template>
<div class="student" style="background:#fff">
    <x-header title="代办及通知" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group style="text-align:left" title="特殊小组:">
        <template v-for="valid in valids">
      <cell :key="valid.teamApplicationId" v-if="valid.showValidContent" style="height:30px" is-link :title="valid.team.teamName+'特殊小组申请'" @click.native="valid.detail=!valid.detail" :arrow-direction="valid.detail?'up':'down'">
        <img slot="icon" src="@/assets/exclamation.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
      <template v-if="valid.detail&&valid.showValidContent">
        <cell value-align="left" style="padding-left:30px">{{valid.team.teamName}}特殊组队申请:<p>{{valid.reason}}</p></cell>
         <img @click="choice(valid,'yes')" src="@/assets/correct.png" style="margin-left:80px" width="20px" height="20px"/>
         <img @click="choice(valid,'no')" src="@/assets/wrong.png" style="margin-left:135px" width="20px" height="20px"/>
      </template>
        </template>
    </group>
    <group style="text-align:left" title="共享组队：">
      <template v-for="share in shares">
      <cell :key="share.shareTeamId" v-if="share.showShareContent&&share.status!=true   " style="height:30px" is-link :title="'课程 '+share.masterCourse.courseName+' 分组邀请'" @click.native="share.detail=!share.detail" :arrow-direction="share.detail?'up':'down'">
        <img slot="icon" src="@/assets/exclamation.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
      <template v-if="share.detail&&share.showShareContent">
        <cell value-align="left" style="padding-left:30px">来自--{{share.masterCourse.teacher.name}}老师 的共享组队申请</cell>
         <img @click="choice(share,'y')" src="@/assets/correct.png" style="margin-left:80px" width="20px" height="20px"/>
         <img @click="choice(share,'n')" src="@/assets/wrong.png" style="margin-left:135px" width="20px" height="20px"/>
      </template>
        </template>
    </group>
    <div v-transfer-dom>

       <confirm v-model="todo"
        :title="'提示'"
        @on-confirm="sure">
        <p style="text-align:center;font-size:1.3em">请确认操作</p>
      </confirm>

      <popup v-model="show" height="23%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div></cell>
          </div>
      </popup>
    </div>

</div>
</template>

<script>
import axios from 'axios'
import {XHeader,
        XButton,Group,Cell,TransferDom,Popup,Confirm} from 'vux'

  export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,Popup,Confirm
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            show:false,
            todo:false,
            YN:'',
            done:true,
            valids:[{
                showValidContent:false,
                team:{
                    teamId:'',
                    teamName:'',
                },
                reason:''
            }],
            shares:[{
                shareTeamId:'',
                masterCourse:{
                    courseId:'',
                    courseName:'',
                },
                receiveCourse:{
                    courseId:'',
                    courseName:'',
                    teacher:{
                        teacherId:'',
                        email:'',
                        name:'',
                    }
                },status:'',
                showContent:true,
                detail:false
            }],
            taskId:'',
        }
    },
    mounted:function(){
        this.$axios.get('/request/teamshare')
        .then((response)=>{
            this.shares=response.data
            for(var i=0;i<this.shares.length;i++){
                this.$set(this.shares[i],'showShareContent',true)
                this.$set(this.shares[i],'detail',false)
            }
        })
        this.$axios.get('/request/teamvalid')
        .then((response)=>{
            this.valids=response.data
            for(var i=0;i<this.valids.length;i++){
                this.$set(this.valids[i],'showValidContent',true)
                this.$set(this.valids[i],'detail',false)
            }
        })
    },
    methods:{
        
        Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
        TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
        GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
        agree(){
            console.log('agree')
        },
        refuse(){
        },
        choice(va,which){
            this.todo=!this.todo
            if(which==='no'){
                this.taskId=va.teamApplicationId
                this.YN='refuse'
            }else if(which==='yes'){
                this.taskId=va.teamApplicationId
                this.YN='agree'
            }else if(which==='n'){
                this.taskId=va.shareTeamId
                this.YN='re'
            }else if(which==='y'){
                this.taskId=va.shareTeamId
                this.YN='ag'
            }
        },
        sure:function(){
            console.log('sure')
            if(this.YN==='agree'){
            this.$axios.put('/request/teamvalid/'+this.taskId,{status:true})
            .then((res)=>{})
                for(var i=0;i<this.valids.length;i++){
                if(this.valids[i].teamApplicationId==this.taskId){
                    this.valids[i].showValidContent=false;
                }
            }

            }else if(this.YN==='refuse'){
                this.$axios.put('/request/teamvalid/'+this.taskId,{status:false}).then((res)=>{})
                for(var i=0;i<this.valids.length;i++){
                if(this.valids[i].teamApplicationId==this.taskId){
                    this.valids[i].showValidContent=false;
                }
            }

            }else if(this.YN==='ag'){
                this.$axios.put('/request/teamshare/'+this.taskId,{status:true}).then((res)=>{})
                for(var i=0;i<this.shares.length;i++){
                if(this.share[i].shareTeamId==this.taskId){
                    this.shares[i].showShareContent=false;
                }
            }

            }else if(this.YN==='re'){
                this.$axios.put('/request/teamshare/'+this.taskId,{status:false}).then((res)=>{})
                for(var i=0;i<this.shares.length;i++){
                if(this.share[i].shareTeamId==this.taskId){
                    this.shares[i].showShareContent=false;
                }
            }
            }
                this.$router.go(0)
                this.YN=''  
                this.taskId=''
        }
    }
        
  };
</script>