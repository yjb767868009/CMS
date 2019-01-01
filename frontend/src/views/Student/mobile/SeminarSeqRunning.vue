<template>
  <div>
    <x-header
      :title="this.$store.state.student.currentCourse.courseName"
      style="height:60px;padding-top:12px;font-size:20px"
      :left-options="{showBack:false}"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>
    
    <group>
    
    <x-header style="background-color:#fff;">
      <span style="color:#000;">{{this.$store.state.student.currentSeminar.topic}}</span>
      <span style="color:#000;" slot="overwrite-left">{{'第'+currentTeamOrder+'组 展示'}}</span>
      <span style="color:#000;" slot="right">{{'已有'+questions.length+'位同学提问'}}</span>
    </x-header>
    
    <template v-for="attendance in attendances">
      <template v-if="attendance.present">
        <cell style="color:#E64340" :key="attendance.attendanceId" :title="'第'+attendance.teamOrder+'组'">{{attendance.team.teamName}}</cell>
      </template>
      <template v-else>
        <cell :key="attendance.attendanceId" :title="'第'+attendance.teamOrder+'组'">{{attendance.team.teamName}}</cell>
      </template>
    </template>

    </group>

    <x-button style="margin-top:30px" type="primary" @click.native="ask">发起提问</x-button>

    <div v-transfer-dom>
      <confirm v-model="questionConfrimShow"
      title="提问"
      @on-confirm="onConfirm">
        <p style="text-align:center;">确认提问?</p>
      </confirm>
    </div>

    <div v-transfer-dom>
      <popup v-model="show" height="15%">
        <div>
          <cell value-align="left" title>
            <img
              slot="icon"
              src="@/assets/man.png"
              style="display:block;margin-right:10px;"
              width="30px"
              height="30px"
            >
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div>
          </cell>
          <cell value-align="left" title>
            <img
              slot="icon"
              src="@/assets/book.png"
              style="display:block;margin-right:10px;"
              width="30px"
              height="30px"
            >
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div>
          </cell>
        </div>
      </popup>
    </div>
  </div>
</template>

<style>
.vux-header-back {
  padding: 9px;
  font-size: 1.1em;
}
.left-arrow {
  margin-top: 10px;
}
.vux-header-more {
  padding-top: 9px;
}
</style>


<script>
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'
import {
  XHeader,XButton,
  Cell,
  Group,
  AlertPlugin,
  TransferDom,
  Popup,Confirm,Flexbox
} from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XButton,
    Group,
    XHeader,
    Cell,
    AlertPlugin,
    Popup,Confirm,Flexbox
  },
  data() {
    return {
      show: false,
      questionConfrimShow:false,
      questions:'',
      attendances:'',
      KlassSeminarRun:'',
    };
  },
  mounted: function () {
      this.initWebSocket()
      this.$axios.get('/klassseminar/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId+'/run')
        .then((response) => {
          this.questions = response.data.questions
          this.attendances = response.data.attendances
        })
    },
    beforeDestroy: function () {
      // 页面离开时断开连接
      this.disconnect();
    },
  computed:{
    currentAttendanceId:function(){
      for(var i=0;i<this.attendances.length;i++){
        if(this.attendances[i].present){
          return this.attendances[i].attendanceId
        }
      }
      return -1
    },
    currentTeamOrder:function(){
      for(var i=0;i<this.attendances.length;i++){
        if(this.attendances[i].present){
          return this.attendances[i].teamOrder
        }
      }
      return -1
    },
  },
  methods: {
    ask:function(){
      this.questionConfrimShow=true
    },
    onConfirm: function() {
        console.log(JSON.stringify({
          student:{
            studentId:this.$store.state.student.studentId
          },
          attendance:this.currentAttendanceId
        }))
        this.stompClient
        .send('/app/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId+'/question', 
        {
          'Authorization':"Bearer "+this.$store.state.token
        }, 
        JSON.stringify({
          student:{
            studentId:this.$store.state.student.studentId
          },
          attendance:this.currentAttendanceId
        }))
    },
    running: function() {
      this.$router.push("/mobile/Student/studentSeminarList");
    },
    StudentInfo: function() {
      this.$router.push("/mobile/student/studentInfo");
    },


    //websocket
      initWebSocket: function () {
        this.connection()
      },

      connection: function () {
        //建立链接对象
        this.socket = new SockJS('http://localhost:8000/gs-guide-websocket')
        //获取STOMP子协议的客户端对象
        this.stompClient = Stomp.over(this.socket)

        this.stompClient.connect({}, (frame) => {
          this.stompClient.subscribe('/topic/klassSeminar/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId, (KlassSeminarRun) => {
            this.KlassSeminarRun=JSON.parse(KlassSeminarRun.body)
            console.log(KlassSeminarRun.body)
          })
        })

      },

      disconnect() {
        if (this.stompClient != null) {
          this.stompClient.disconnect()
          console.log("Disconnected")
        }
      },
      postQuestion: function () {
        
      },
  }
};
</script>