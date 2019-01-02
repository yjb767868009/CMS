<template>
  <div>
    <x-header :title="this.$store.state.teacher.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}"
      @on-click-more="show=!show">
    </x-header>

    <cell :title="this.$store.state.teacher.currentKlassSeminar.seminar.topic" style="text-align:center">
    </cell>

    <flexbox :gutter="10">
      <flexbox-item>
        <group title="展示队伍">
          <radio v-model="currentTeam" :options="Teams" disabled></radio>
        </group>
      </flexbox-item>

      <flexbox-item>
        <template v-if="!question_scoring">
        <group>
          展示分数:
          <x-input v-model="presentationScore" :is-type="typePresentationScore"></x-input>
        </group>
        </template>
        <template v-if="question_scoring">
          提问分数:
          <x-input v-model="questionScore" :is-type="typeQuestionScore"></x-input>
        </template>
      </flexbox-item>

      <flexbox-item>
        <group :title="'已有'+questionForShow.length+'位同学提问'">
          <radio v-model="currentQuestion" :options="questionForShow" @on-change="questionChange"></radio>
        </group>
      </flexbox-item>
    </flexbox>



    <template v-if="!is_modifying">
      <x-button @click.native="nextQuestion" type="primary" style="margin-top:100px;color:#fff;width:50%">抽取提问</x-button>
      <x-button @click.native="nextTeam" type="primary" plain style="margin-top:10px;width:50%">下组展示</x-button>
    </template>
    <template v-if="is_modifying">
      <x-button @click.native="confirmModification" type="primary" style="margin-top:100px;color:#fff;width:50%">确认修改</x-button>
    </template>

    <div v-transfer-dom>
      <confirm v-model="is_end"
      @on-confirm="endSeminar">
        <p style="text-align:center;">是否结束讨论?</p>
      </confirm>
    </div>

    <div v-transfer-dom>
      <popup v-model="show" height="23%">
        <div>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div>
          </cell>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div>
          </cell>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div>
          </cell>
        </div>
      </popup>
    </div>
  </div>
</template>

<script>
  import SockJS from 'sockjs-client'
  import Stomp from 'stompjs'
  import {
    XHeader,
    Cell,
    Group,
    XButton,
    XTable,
    Icon,
    Radio,
    Countdown,
    Confirm,
    TransferDom,
    Popup,
    Flexbox,
    FlexboxItem,
    XTextarea,
    XInput,
    Countup
  } from 'vux'
  export default {
    directives: {
      TransferDom
    },
    components: {
      XHeader,
      Cell,
      Group,
      XButton,
      XTable,
      Icon,
      Radio,
      Confirm,
      Flexbox,
      FlexboxItem,
      XTextarea,
      XInput,
      Countup,
      Popup
    },
    data() {
      return {
        show: false,
        Teams: [],
        questions:[],
        questionForShow: [],
        currentTeamIndex: 0,
        currentTeam: '',
        currentQuestion: '',
        question_scoring:false,
        is_modifying: false,
        is_end:false,
        presentationScore: '',
        questionScore: '',
      }
    },
    mounted: function () {
      this.initWebSocket()
      //this.$store.state.teacher.currentKlassSeminar.klassSeminarId
      this.$axios.get('/klassseminar/'+this.$store.state.teacher.currentKlassSeminar.klassSeminarId+'/run')
        .then((response) => {
          // this.questions = response.data.questions
          this.attendances = response.data.attendances
          this.questionForShow = []
          for (var i = 0; i < this.questions.length; i++) {
            this.$set(this.questionForShow,i,this.questions[i].name)
          }
          this.Teams = []
          this.currentTeam=this.attendances[0].team.teamName
          for (var i = 0; i < this.attendances.length; i++) {
            this.$set(this.Teams,i,this.attendances[i].team.teamName)
          }
        })
    },
    beforeDestroy: function () {
      // 页面离开时断开连接,清除定时器
      this.disconnect();
    },
    computed:{
      currentQuestionId:function(){
        for(var i=0;i<this.questions.length;i++){
          if(this.questions[i].name===this.currentQuestion){
            return this.questions[i].questionId
          }
        }
        return -1
      },
      currentAttendanceId:function(){
        for(var i=0;i<this.attendances.length;i++){
          if(this.attendances[i].team.teamName===this.currentTeam){
            return this.attendances[i].attendanceId
          }
        }
        return -1
      }
    },

    methods: {
      finish(index) {
        this.show = false
        this.value = 'completed'
        console.log('current index', index)
      },
      Undo() {
        this.$router.push('/mobile/teacher/notify')
      },
      TeacherInfo() {
        this.$router.push('/mobile/teacher')
      },
      GoSeminar() {
        this.$router.push('/mobile/teacher/seminars')
      },
      
      questionChange:function(value, label){
        this.question_scoring=true
      },

      confirmModification: function () {
        if(this.question_scoring){//提问分数
          var qid=this.currentQuestionId
          this.$axios.put('/question/'+qid,{
            score:this.presentationScore
          }).then((response)=>{
            this.is_modifying = false
            this.question_scoring=false
            this.presentationScore=null
            this.questionScore=null
          })
        }else if(!this.question_scoring){//展示分数
          this.$axios.put('/attendance/'+this.currentAttendanceId+'/presentationscore',{
            questionScore:this.questionScore
          }).then((response)=>{
            this.is_modifying=false
            this.question_scoring=false
            this.presentationScore=null
            this.questionScore=null
          })
        }
      },

      typeQuestionScore:function(value){
        this.is_modifying=true
        return{
          valid: (['0','1', '2', '3', '4', '5'].indexOf(value)) !== -1,
          msg: '分数为小于6的整数'
        }
      },
      typePresentationScore: function (value) {
        this.is_modifying = true
        return {
          valid: (['0','1', '2', '3', '4', '5'].indexOf(value)) !== -1,
          msg: '分数为小于6的整数'
        }
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
        //this.$store.state.teacher.currentKlassSeminar.klassSeminarId
        this.stompClient.connect({}, (frame) => {
          this.stompClient.subscribe('/topic/klassSeminar/'+this.$store.state.teacher.currentKlassSeminar.klassSeminarId
          , (KlassSeminarRun) => {
            this.KlassSeminarRun=JSON.parse(KlassSeminarRun.body)
            if(this.KlassSeminarRun.newQuestion){
              this.$set(this.questions,this.questions.length,this.KlassSeminarRun.newQuestion)
              this.$set(this.questionForShow,this.questionForShow.length,this.KlassSeminarRun.newQuestion.name)
            }else if(this.KlassSeminarRun.nowAttendance){

            }else if(this.KlassSeminarRun.selectQuestion){

            }else if(this.KlassSeminarRun.message){
              if(this.KlassSeminarRun.message==='end'){
                this.$router.push('/mobile/teacher/seminarFinished')
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

      nextQuestion: function () {
        this.stompClient.send('/app/'+this.$store.state.teacher.currentKlassSeminar.klassSeminarId
        +'/getQuestion', {}, {})
      },
      nextTeam: function () {
        if (this.currentTeamIndex >= 0 && this.currentTeamIndex < this.Teams.length -1) {
          this.stompClient.send('/app/'+this.$store.state.teacher.currentKlassSeminar.klassSeminarId
        +'/nextAttendance',{},{})
          this.currentTeamIndex = this.currentTeamIndex + 1
          this.currentTeam = this.Teams[this.currentTeamIndex]

        } else {
          this.is_end=true
        }
      },
      endSeminar:function(){
        this.stompClient.send('/app/'+this.$store.state.teacher.currentKlassSeminar.klassSeminarId
        +'/endAttendance',{},{})
      }
    }
  }

</script>
