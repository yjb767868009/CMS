<template>
  <div class="login" style="background:#eee">
    <x-header :title="this.$store.state.teacher.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group>
      <template v-for="round in rounds">
        <cell style="height:40px"
          :key="round.roundId"
          is-link
          value-align="left"
          :border-intent="false"
          :arrow-direction="round.showRoundContent ? 'up' : 'down'"
          @click.native="round.showRoundContent=!round.showRoundContent"
        >第{{round.order}}轮</cell>
        <template v-if="round.showRoundContent">
          <template v-for="seminar in round.seminars">
            <cell
              :key="seminar.seminarId"
              :title="seminar.topic"
              is-link
              :arrow-direction="seminar.showSeminarContent ? 'up' : 'down'"
              @click.native="seminar.showSeminarContent=!seminar.showSeminarContent"
              style="height:30px;margin-left:5px"
            ></cell>
            <template v-if="seminar.showSeminarContent">
              <template v-for="klassSeminar in seminar.klassSeminars">
                <cell-box
                  :key="klassSeminar.klassSeminarId"
                  is-link
                  @click.native="clickClassSeminar(round,seminar,klassSeminar)"
                  style="padding-left:140px"
                >{{klassSeminar.klass.name}}</cell-box>
              </template>
            </template>
          </template>
        </template>
      </template>
    </group>


    <div v-transfer-dom>
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
import { XHeader, Cell, Group, CellBox, XButton,TransferDom,Popup } from "vux";
export default {
  directives:{
    TransferDom
  },
  components: {
    XHeader,
    Cell,
    CellBox,
    Group,
    XButton,Popup
  },
  data() {
    return {
      show:false,
      rounds: [
        // fake data
        {
          roundId: 1,
          showRoundContent: false,
          order: 1,
          course: {
            courseName: "j2ee"
          },
          presentationScoreType: 0,
          reportScoreType: 0,
          questionScoreType: 0,
          seminars: [
            {
              seminarId: 1,
              showSeminarContent: false,
              course: {
                courseId: 4,
                courseName: "ssss"
              },
              round: {
                roundId: 1
              },
              maxTeamNum: 6,
              topic: "1111",
              introduction: "1111",
              signStartTime: "2018-11-17T14:00:00.000+0000",
              signEndTime: "2018-11-24T14:00:00.000+0000",
              signOrder: false,
              visible: false,
              klassSeminars: [
                {
                  klassSeminarId: 1,
                  klass: {
                    klassId: 1,
                    grade: 2016,
                    klassSerial: 1,
                    name: "2016-1"
                  },
                  reportDDL: "2018-12-18T18:28:25.000+0000",
                  status: 0
                }
              ]
            }
          ]
        },
      ]
    };
  },
    mounted: function() {
      this.$axios
        .get(
          "/course/" + this.$store.state.teacher.currentCourse.courseId + "/round"
        )
        .then(response => {
          this.rounds = response.data;
          //为返回数据绑定show属性用于折叠显示
          for(var i=0;i<this.rounds.length;i++){
              this.$set(this.rounds[i],'showRoundContent',false)
              for(var j=0;j<this.rounds[i].seminars.length;j++){
                  this.$set(this.rounds[i].seminars[j],'showSeminarContent',false)
              }
          }
        });
    },
  methods: {
    clickClassSeminar: function(round,seminar,klassSeminar) {
        this.$store.state.teacher.currentRound=round
        this.$store.state.teacher.currentSeminar=seminar
        this.$store.state.teacher.currentKlassSeminar=klassSeminar
        if(klassSeminar.status===0){
            //未开始
            this.$router.push('/mobile/teacher/seminarUnstarted')
        }
        else if(klassSeminar.status===1){
            //正在进行
            this.$router.push('/mobile/teacher/seminarOngoing')
        }
        else if(klassSeminar.status===2){
            //已结束
            this.$router.push('/mobile/teacher/seminarFinished')
        }
    },
    Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
    TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
    GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
  }
};
</script>
