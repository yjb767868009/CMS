<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD-讨论课" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}"
      @on-click-more="show=!show">
    </x-header>

    <cell title="业务流程分析" style="text-align:center">
      <!-- <button style="color:#000;background:0;border:0">
        <template v-if="!is_pause">  
            <img slot="right"  @click="pause" src="@/assets/pause.png" style="display:block;margin-right:10px;" width="20px" height="20px" />
        </template>
        <template v-if="is_pause">
            <img slot="right" @click="keepUp" src="@/assets/play.png" style="display:block;margin-right:10px;" width="20px" height="20px" />
        </template>
      </button> -->
      <!-- <img @click="pause" src="@/assets/pause.png"/> -->
    </cell>
    <div style="text-align:center">
      当前组已用时:{{''+this.usedSecond+'秒'}}
    </div>
    <flexbox :gutter="10">
      <flexbox-item>
        <group title="展示队伍">
          <radio v-model="currentTeam" :options="Teams" disabled></radio>
        </group>
      </flexbox-item>
      <flexbox-item>
        <group>
          展示分数:
          <x-input v-model="presentationScore" :is-type="typePresentationScore"></x-input>
        </group>
      </flexbox-item>
      <flexbox-item>
        <group :title="'已有'+Questions.length+'位同学提问'">
          <radio v-model="currentQuestion" :options="Questions"></radio>
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
  import axios from 'axios'
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
        Teams: ['1-1', '1-2', '1-3'],
        Questions: ['111', '1111', '11111', 'fdfad'],
        currentTeamIndex: -1,
        currentTeam: '',
        currentQuestionIndex: -1,
        currentQuestion: '',
        is_modifying: false,
        usedMillisecond: 0,
        usedSecond: 0,
        presentationScore: '',
        questionScore: '',
      }
    },
    mounted: function () {
      this.initWebSocket()
      //this.$store.state.teacher.currentKlassSeminar.klassSeminarId
      this.$axios.get('/klassseminar/1/run')
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
      back: function () {
        this.$router.push('/mobile/teacher/seminar')
      },
      pause: function () {
        this.is_pause = true
      },
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
      keepUp: function () {
        this.is_pause = false
      },


      confirmModification: function () {
        console.log('post')
        this.is_modifying = false
      },
      typePresentationScore: function (value) {
        this.is_modifying = true
        return {
          valid: (['1', '2', '3', '4', '5'].indexOf(value)) !== -1,
          msg: '分数为大于0，小于6的整数'
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
        //空header
        //this.$store.state.teacher.currentKlassSeminar.klassSeminarId
        this.stompClient.connect({}, (frame) => {
          this.stompClient.subscribe('/topic/klassSeminar/1', (KlassSeminarRun) => {
            //   this.KlassSeminarRun=JSON.parse(KlassSeminarRun.body)
            //   this.showKlassSeminarRun(this.KlassSeminarRun)
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

      nextQuestion: function () {
        this.stompClient.send('/app/1/getQuestion', {}, {})
      },
      nextTeam: function () {
        this.stompClient.send('/app/1/nextAttendance')
        if (this.currentTeamIndex >= -1 && this.currentTeamIndex < this.Teams.length - 1) {
          console.log('nextTeam')
          this.currentTeamIndex = this.currentTeamIndex + 1
          this.currentTeam = this.Teams[this.currentTeamIndex]
        } else {
          console.log('no more team')
        }
      },
      showKlassSeminarRun: function (v) {
        console.log(v)
      }
    }
  }

</script>
