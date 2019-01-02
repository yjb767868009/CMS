<template>
<div class="student" style="background:#fff">
    <x-header title="代办及通知" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group style="text-align:left" title="特殊小组:">
        <template v-for="valid in valids">
      <cell :key="valid.teamApplicationId" v-if="valid.showValidContent" style="height:30px" is-link :title="valid.team.teamName+'分组邀请'" @click.native="valid.detail=!valid.detail" :arrow-direction="valid.detail?'up':'down'">
        <img slot="icon" src="@/assets/exclamation.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
      <template v-if="valid.detail&&valid.showValidContent">
        <cell value-align="left" style="padding-left:30px">{{valid.team.teamName}}特殊组队申请:<p>{{valid.reason}}</p></cell>
         <img @click="choice(valid,'yes')" src="@/assets/correct.png" style="margin-left:80px" width="20px" height="20px"/>
         <img @click="choice(valid,'no')" src="@/assets/wrong.png" style="margin-left:135px" width="20px" height="20px"/>
      </template>
        </template>
    </group>

    <!-- <group style="text-align:left" title="共享组队：">
      <template v-for="valid in valids">
      <cell :key="valid.teamApplicationId" v-if="valid.showValidContent" style="height:30px" is-link :title="valid.team.teamName+'分组邀请'" @click.native="valid.detail=!valid.detail" :arrow-direction="detail?'up':'down'">
        <img slot="icon" src="@/assets/exclamation.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
      <template v-if="valid.detail&&valid.showValidContent">
        <cell value-align="left" style="padding-left:30px">{{valid.team.teamName}}共享组队申请:{{valid.reason}}</cell>
         <img @click="choice('yes')" src="@/assets/correct.png" style="margin-left:80px" width="20px" height="20px"/>
         <img @click="choice('no')" src="@/assets/wrong.png" style="margin-left:135px" width="20px" height="20px"/>
      </template>
        </template>
    </group> -->
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
            shares:'',
            taskId:'',
        }
    },
    mounted:function(){
        this.$axios.get('/request/teamshare')
        .then((response)=>{
            this.shares=response.data
            for(var i=0;i<this.valids.length;i++){
                this.$set(this.valids[i],'showShareContent',true)
                this.$set(this.valids[i],'detail',false)
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
            }
        },
        sure:function(){
            console.log('sure')
            if(this.YN==='agree'){
            this.$axios.get('/request/teamvalid/'+this.taskId)
            .then((response)=>{
            }) 
                for(var i=0;i<this.valids.length;i++){
                if(this.valids[i].teamApplicationId==this.taskId){
                    this.valids[i].showValidContent=false;
                }
            }
                this.YN=''}  
        }
    }
        
  };
</script>