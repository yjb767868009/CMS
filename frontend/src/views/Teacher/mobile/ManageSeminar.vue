<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD-讨论课" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
      <button @click.native="onClick" style="background:0;height:0px;border:0" slot="right">
        <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
      </button>
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
    Confirm,
    Flexbox,
    FlexboxItem,
    XTextarea,
    XInput,
    Countup
  } from 'vux'
  export default {
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
      Countup
    },
    data() {
      return {
        Teams: ['1-1', '1-2', '1-3'],
        Questions: ['111', '1111', '11111', 'fdfad'],
        currentTeamIndex: -1,
        currentTeam: '',
        currentQuestionIndex: -1,
        currentQuestion: '',
        is_modifying: false,
        usedMillisecond: 0,
        usedSecond: 0,
        timerInterval: '',
        presentationScore:'',
        questionScore:'',
      }
    },
    mounted:function(){
        this.initWebSocket()
    },
    methods: {
      onClick() {
        console.log('on click')
      },
      back: function () {
        this.$router.push('/mobile/teacher/seminar')
      },
      more: function () {},
      comfirm: function () {

      },
      modify: function () {

      },
      pause: function () {
        this.is_pause = true
      },
      keepUp: function () {
        this.is_pause = false
      },
      nextTeam: function () {
        this.resetTimer()
        this.startTimer()
        if (this.currentTeamIndex >= -1 && this.currentTeamIndex < this.Teams.length - 1) {
          console.log('nextTeam')
          this.currentTeamIndex = this.currentTeamIndex + 1
          this.currentTeam = this.Teams[this.currentTeamIndex]
        } else {
          console.log('no more team')
        }
      },
      nextQuestion: function () {
        
      },
      startTimer: function () {
        this.timerInterval = setInterval(this.timer, 50)
      },
      stopTimer: function () {
        window.clearInterval(this.timerInterval)
      },
      resetTimer: function () {
        if (this.timerInterval !== '') {
          window.clearInterval(this.timerInterval)
        }
        this.usedMillisecond = 0
        this.usedSecond = 0
      },
      timer: function () {
        this.usedMillisecond = this.usedMillisecond + 50
        if (this.usedMillisecond >= 1000) {
          this.usedMillisecond = 0;
          this.usedSecond = this.usedSecond + 1
        }
      },
      confirmModification:function(){
          console.log('post')
          this.is_modifying=false
      },
      typePresentationScore:function(value){
          this.is_modifying=true
          return{
              valid:(['1','2','3','4','5'].indexOf(value))!==-1,
              msg:'分数为大于0，小于6的整数'
          }
      },

      //websocket
      initWebSocket:function(){
          this.connection()
      },

      connection:function(){
          //建立链接对象
          this.socket=new SockJs('http://localhost:8000/gs-guide-websocket')
          //获取STOMP子协议的客户端对象
          this.stompClient=Stomp.over(this.socket)
          //空header
          this.stompClient.connect({},(frame)=>{
              this.stompClient.subscribe('/topic/klassSeminar/'+this.$store.state.teacher.currentKlassSeminar.klassSeminarId,(KlassSeminarRun)=>{
                  this.KlassSeminarRun=JSON.parse(KlassSeminarRun.body)
              })
          })
      },

      disconnect(){
          if(this.stompClient!=null){
              this.stompClient.disconnect()
              console.log("Disconnected")
          }
      },

    }
  }

</script>
