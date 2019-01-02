<template>
<div>
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px;font-size:20px" :left-options="{showBack:false}" :right-options="{showMore: true}"  @on-click-more="show=!show">
    </x-header>
    
    
    <cell title="第一组">
        <template v-if="attendances[0]">{{attendances[0].team.teamName}}</template>
        <template v-else><a @click="register(0)" style="text-decoration:underline;color:#1AAD19">可报名</a></template>
    </cell>
    <cell title="第二组">
        <template v-if="attendances[1]">{{attendances[1].team.teamName}}</template>
        <template v-else><a @click="register(1)" style="text-decoration:underline;color:#1AAD19">可报名</a></template>
    </cell>
    <cell title="第三组">
        <template v-if="attendances[2]">{{attendances[2].team.teamName}}</template>
        <template v-else><a @click="register(2)" style="text-decoration:underline;color:#1AAD19">可报名</a></template>
    </cell>
    <cell title="第四组">
        <template v-if="attendances[3]">{{attendances[3].team.teamName}}</template>
        <template v-else><a @click="register(3)" style="text-decoration:underline;color:#1AAD19">可报名</a></template>
    </cell>
    <cell title="第五组">
        <template v-if="attendances[4]">{{attendances[4].team.teamName}}</template>
        <template v-else><a @click="register(4)" style="text-decoration:underline;color:#1AAD19">可报名</a></template>
    </cell>
    <cell title="第六组">
        <template v-if="attendances[5]">{{attendances[5].team.teamName}}</template>
        <template v-else><a @click="register(5)" style="text-decoration:underline;color:#1AAD19">可报名</a></template>
    </cell>
    
    <template v-if="this.$store.state.student.currentAttendance.message!=='other'">
        <x-button type="warn" @click="cancelRegistration">取消报名</x-button>
    </template>
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
.vux-header-back{
    padding:9px;
    font-size:1.1em;
  }
.left-arrow{
    margin-top:10px;
}
.vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import Vue from 'vue'
import {XHeader,Cell,XButton,
        Alert,Group,
        TransferDom,Popup,Flexbox,Confirm
        } from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        Alert,XButton,
        Group,
        XHeader,
        Cell,Popup,Flexbox,Confirm
    },
    data() {
       return{ 
        show:false,
        attendances:[],
    }
    },
    mounted:function(){
        this.attendances=[]
        for(var i=0;i<this.$store.state.student.currentAttendance.attendance.length;i++){
            if(parseInt(this.$store.state.student.currentAttendance.attendance[i].teamOrder)===i+1){
                this.$set(this.attendances,i,this.$store.state.student.currentAttendance.attendance[i])
            }else{
                this.$set(this.attendances,i,null)
            }
        }
    },
   methods:{
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        cancelRegistration:function(){
            this.$axios.delete('/attendance/'+this.$store.state.student.currentAttendance.attendance[parseInt(this.$store.state.student.currentAttendance.message)-1].attendanceId)
            .then((response)=>{
                this.$router.go(0)
            })
        },
        register:function(index){
            this.$axios.post('/klassseminar/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId+'/attendance',{
                teamOrder:index+1
            }).then((response)=>{
                this.$router.go(0)
            })
        }
   }
  }
</script>