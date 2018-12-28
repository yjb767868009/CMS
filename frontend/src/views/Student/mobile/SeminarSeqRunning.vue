<template>
  <div class="student" style="height:800px;background:#eee;">
    <x-header
      title="讨论课正在进行"
      style="height:60px;padding-top:12px;font-size:20px"
      :left-options="{showBack:false}"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>
    
    <group>
    <cell>{{'第'+attendances.teamOrder+'组展示中    '+this.$store.state.student.currentSeminar.topic+'   已有'+Questions.length+'位同学提问'}}</cell>
    <template v-for="attendance in attendances">
      <cell :key="attendance.attendanceId" :title="'第'+attendance.teamOrder+'组'">{{attendance.team.teamName}}</cell>
    </template>

    </group>

    <flexbox style="margin-top:30px">
        <x-button type="primary" @click.native="question=!question">发起提问</x-button>
    </flexbox>

    <div v-transfer-dom>

      <confirm v-model="question"
        :show-cancel-button="false"
        title="提问成功"
        @on-confirm="onConfirm">
        <p style="text-align:center;">等待老师抽取</p>
      </confirm>
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
      question:false,
      questions:'',
      Questions:'',
      attendances:'',
    };
  },
  mounted: function () {
      this.initWebSocket()
      //this.$store.state.teacher.currentKlassSeminar.klassSeminarId
      this.$axios.get('/klassseminar/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId+'/run')
        .then((response) => {
          console.log(response)
          this.questions = response.data.questions
          this.attendances = response.data.attendances
          this.Questions = []
          for (var i = 0; i < this.questions.length; i++) {
            this.Questions.push(this.questions[i].name)
          }
          this.Teams = []
          for (var i = 0; i < this.attendances.length; i++) {
            this.Teams.push(this.attendances[i].team.teamName)
          }
        })
    },
    beforeDestroy: function () {
      // 页面离开时断开连接,清除定时器
      this.disconnect();
    },
  methods: {
    onCancel: function() {
      console.log("取消");
    },
    onConfirm: function() {
      console.log("确认");
      let len = this.attendances.length
      console.log(len)
      console.log(this.attendances[len-1].attendanceId+1)
        this.stompClient.send('/app/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId+'/question', {}, {
          attendanceId:this.attendances[len-1].attendanceId+1
        })
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
        //空header
        //this.$store.state.teacher.currentKlassSeminar.klassSeminarId
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
        console.log(this.attendances[this.attendances.length].attendanceId+1)
        this.stompClient.send('/app/'+this.$store.state.student.currentSeminar.klassSeminars[0].klassSeminarId+'/question', {}, {
          attendanceId:this.attendances[this.attendances.length].attendanceId+1
        })
      },
  }
};
</script>