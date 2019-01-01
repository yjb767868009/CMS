<template>
  <div class="student" style="height:20px;background:#fff">
    <x-header
      v-bind:title="this.$store.state.student.currentCourse.courseName"
      style="height:60px;padding-top:12px"
      :left-options="{showBack:false}"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>

    <group>
      <template v-for="round in this.rounds">
      <cell :key="round.roundId"
        is-link
        :border-intent="false"
        :arrow-direction="round.showRoundContent?'down':'up'"
        @click.native="round.showRoundContent=!round.showRoundContent"
        value-align="left">
        <span>第{{round.order}}轮</span>
      </cell>
      <template v-if="round.showRoundContent">
        <cell-box
          v-for="seminar in round.seminars"
          :key="seminar.seminarId"
          @click.native="click(round,seminar)"
          is-link
        >{{seminar.topic}}</cell-box>
      </template>
      </template>
    </group>
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
.vux-header-more {
  padding-top: 9px;
}
.weui-cells {
  color: #000;
}
</style>

<script>
import axios from "axios";
import moment from 'moment'
import { XHeader, Cell, CellBox, TransferDom, Popup, Group } from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XHeader,
    Cell,
    CellBox,
    Popup,
    Group
  },
  data() {
    return {
      rounds: [
        {
          id: 1,
          order: 2
        },
        {
          id: 2,
          order: 3
        }
      ],
      seminars: [
        {
          id: 45,
          topic: "业务流程分析",
          order: 2
        }
      ],
      show: false,
    };
  },
  mounted: function() {//挂载:获取round 和 seminar
     this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/round')
        .then((response)=>{
            this.rounds=response.data;
            
            this.rounds.forEach(rounditem=>{
              this.$set(rounditem,'showRoundContent',false);
            })
          });
          
  },
  methods: {
    click: function(round,seminar) {
      this.$store.state.student.currentRound=round
      this.$store.state.student.currentSeminar = seminar
      
      this.$axios.get('/klassseminar/'+seminar.klassSeminars[0].klassSeminarId+'/attendance')
        .then((response)=>{
          this.$store.state.student.currentAttendance=response.data
          if(parseInt(seminar.klassSeminars[0].status)===0&&response.data.message==='other'){
            console.log('未开未报')
            this.$router.push({name:'SeminarRegistration'})
          }else if(parseInt(seminar.klassSeminars[0].status)===1&&response.data.message==='other'){
            console.log('正在未报')
            this.$router.push({name:'SeminarDetail'})
          }
          else if(parseInt(seminar.klassSeminars[0].status)===2&&response.data.message==='other'){
            console.log('已完未报')
            this.$router.push({name:'SeminarSeqFinished'})
          }else if(parseInt(seminar.klassSeminars[0].status)===0&&response.data.message!=='other'){
            console.log('未开始已报')
            this.$router.push({name:'SeminarUnstartSigned'})
          }else if(parseInt(seminar.klassSeminars[0].status)===1&&response.data.message!=='other'){
            console.log('正在已报')
            this.$router.push({name:'SeminarRunningSigned'})
          }
          else if(parseInt(seminar.klassSeminars[0].status)===2&&response.data.message!=='other'){
            console.log('已完已报')
            this.$router.push({name:'SeminarSigned'})
          }
        })
          
          
    },
    running: function() {
      this.$router.push("/mobile/Student/studentSeminarList");
    },
    StudentInfo: function() {
      this.$router.push("/mobile/student/studentInfo");
    }
  }
};
</script>
