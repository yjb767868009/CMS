<template>
  <div class="student" style="height:20px;background:#fff">
    <x-header
      v-bind:title="this.$store.state.currentCourse.name"
      style="height:60px;padding-top:12px"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>

    <group>
      <cell
        is-link
        :border-intent="false"
        :arrow-direction="showContent1?'down':'up'"
        @click.native="showContent1=!showContent1"
        value-align="left"
      >
        <span>第{{rounds[0].order}}轮</span>
      </cell>
      <template v-if="showContent1">
        <cell-box
          v-for="seminar in rounds[0].seminar"
          :key="seminar.id"
          @click.native="click(seminar)"
          is-link
        >{{seminar.topic}}</cell-box>
      </template>

      <cell
        is-link
        :border-intent="false"
        :arrow-direction="showContent2?'down':'up'"
        @click.native="showContent2=!showContent2"
        value-align="left"
      >
        <span>第{{rounds[1].order}}轮</span>
      </cell>
      <template v-if="showContent2">
        <cell-box
          v-for="seminar in rounds[1].seminar"
          :key="seminar.id"
          @click.native="click(seminar)"
          is-link
        ></cell-box>
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
      showContent: {},
      showContent1: false,
      showContent2: false,
    };
  },
  mounted: function() {//挂载:获取round 和 seminar

    this.$axios
      .get("/course/" + this.$store.state.currentCourse.id + "/round")
      .then(response => {
        this.rounds = respnose.data;
      });
    for (var i = 0; i < this.rounds.length; i++) {
      this.$axios
        .get("/round/" + this.rounds[i].id + "/seminar")
        .then(response => {
          this.rounds[i].seminars = response.data;
        });
    }
    for (var i = 0; i < this.rounds.length; i++) {
      this.rounds[i].seminar = this.seminars;
    }
  },
  methods: {
    click: function(seminar) {
      this.$store.state.currentSeminar = seminar;
      this.$axios
        .get(
          "/seminar/" + seminar.seminarId + "/class/" + seminar.class.classId
        )
        .then(response => {
          this.$stoer.state.currentClass=response.data
          this.$store.state.reportDDL=response.data.klassSeminar.reportDDL
          this.$store.state.seminarSignEndTime=moment(response.data.seminar.signEndTime)
          
          if(response.data.status===0&&response.data.attendance===null){
            //未开未报
            this.$router.push({name:'SeminarRegistration'})
          }
          else if(response.data.status===1&&response.data.attendance===null){
            //正在未报
            this.$router.push({name:'SeminarDetail'})
          }
          else if(response.data.status===2&&response.data.attendance===null){
            //已完未报
            this.$router.push({name:'SeminarSeqFinished'})
          }
          else if(response.data.status===0&&response.data.attendance!==null){
            //未开始已报
            this.$router.push({name:''})
          }
          else if(response.data.status===1&&response.data.attendance!==null){
            //正在已报
            this.$router.push({name:''})
          }
          else if(response.data.status===2&&response.data.attendance!==null){
            //已完已报
            this.$router.push({name:''})
          }
        });
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
