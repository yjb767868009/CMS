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
    
    <cell>{{this.$store.state.student.currentSeminar.topic}}</cell>
    <cell>{{'第'+currentTeamOrder+'组 展示'}}</cell>
    <cell>{{'已有'+questionLength+'位同学提问'}}</cell>

    <template v-for="attendance in attendances">
      <template v-if="attendance.teamOrder===parseInt(currentTeamOrder)">
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
      <alert v-model="user_selected">提问已被抽取</alert>
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
  Popup,Confirm,Flexbox,Alert
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
    Popup,Confirm,Flexbox,Alert
  },
  data() {
    return {
      show: false,
      questionConfrimShow:false,
      questions:'',
      attendances:[{
		"attendanceId": 40,
		"team": {
			"teamId": 3,
			"leader": {
				"studentId": 160,
				"name": "周必群"
			},
			"teamName": "WEAK"
		},
		"teamOrder": 2,
		"present": true
  },],
      KlassSeminarRun:'',
      user_selected:false,
      currentTeamOrder:1,
      questionLength:0,
    };
  },
  mounted: function () {
      this.initWebSocket()
      this.$axios.get('/klassseminar/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId+'/run')
        .then((response) => {
          // this.questions = response.data.questions
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
  },
  methods: {
    ask:function(){
      this.questionConfrimShow=true
    },
    onConfirm: function() {
        this.stompClient
        .send('/app/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId+'/question', 
        {
          'Authorization':"Bearer "+this.$store.state.token
        }, 
        JSON.stringify({
          student:{
            studentId:this.$store.state.student.studentId
          },
          attendance:{attendanceId:this.currentAttendanceId}
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


            if(this.KlassSeminarRun.nowAttendance){
              console.log('new attendance')
              this.currentTeamOrder=this.KlassSeminarRun.nowAttendance.teamOrder
              for(var i=0;i<this.attendances;i++){
                if(this.attendances[i].teamOrder===this.KlassSeminarRun.nowAttendance.teamOrder){
                  this.$set(this.attendances[i],'present',true)
                }
                else{
                  this.$set(this.attendances[i],'present',false)
                }
              }
            }else if(this.KlassSeminarRun.newQuestion){
              this.questionLength=this.questionLength+1
            }else if(this.KlassSeminarRun.selectQuestion){
              this.questionLength=this.questionLength-1
              if(parseInt(this.KlassSeminarRun.selectQuestion.student.studentId)===parseInt(this.$store.state.student.studentId)){
                //被抽到了
                this.user_selected=true
              }
            }else if(this.KlassSeminarRun.message){
              if(this.KlassSeminarRun.message==='end'){
                if(this.$store.state.student.currentAttendance.message!=='other'){
                  //已完已报
                  this.$router.push('/mobile/student/course/seminar/signed')
                }else{
                  this.$router.push('/mobile/student/course/seminar/finished')
                }
              }
            }

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