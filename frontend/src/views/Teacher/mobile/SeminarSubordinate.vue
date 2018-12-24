<template>
  <div class="login" style="background:#eee">
<x-header :title="this.$store.state.teacher.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group :title="轮次">
      <template v-for="round in rounds">
        <cell
          :title="'第'+round.order+'轮'"
          :key="round.roundId"
          is-link
          :border-intent="false"
          :arrow-direction="round.showRoundContent ? 'up' : 'down'"
          @click.native="round.showRoundContent=!round.showRoundContent"
        ></cell>
        <template v-if="round.showRoundContent">
          <!-- 从不能设置轮次 -->
          <!-- <cell-box
            :border-intent="false"
            class="sub-item"
            is-link
            :key="round.roundId"
            @click.native="setRound(round)"
          >该轮轮次设置</cell-box> -->
          <template v-for="seminar in round.seminars">
            <cell
              :key="seminar.seminarId"
              :title="seminar.topic"
              is-link
              :arrow-direction="seminar.showSeminarContent ? 'up' : 'down'"
              @click.native="seminar.showSeminarContent=!seminar.showSeminarContent"
            ></cell>
            <template v-if="seminar.showSeminarContent">
              <template v-for="klassSeminar in seminar.klassSeminars">
                <cell-box
                  :key="klassSeminar.klassSeminarId"
                  is-link
                  @click.native="clickClassSeminar(klassSeminar)"
                >{{klassSeminar.klass.name}}</cell-box>
              </template>
            </template>
          </template>
        </template>
      </template>
    </group>

    
  </div>
</template>

<script>
import { XHeader, Cell, Group, CellBox, XButton } from "vux";
export default {
  components: {
    XHeader,
    Cell,
    CellBox,
    Group,
    XButton
  },
  data() {
    return {
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
        {
          roundId: 2,
          showRoundContent: false,
          order: 2,
          course: {
            courseName: "j2ee"
          },
          presentationScoreType: 1,
          reportScoreType: 0,
          questionScoreType: 1,
          seminars: [
            {
              seminarId: 2,
              showSeminarContent: false,
              course: {
                courseId: 4,
                courseName: "ssss"
              },
              round: {
                roundId: 2
              },
              maxTeamNum: 6,
              topic: "2222",
              introduction: "2",
              signStartTime: "2018-11-17T14:00:00.000+0000",
              signEndTime: "2018-11-24T14:00:00.000+0000",
              signOrder: true,
              visible: true,
              klassSeminars: [
                {
                  klassSeminarId: 2,
                  klass: {
                    klassId: 1,
                    grade: 2016,
                    klassSerial: 1,
                    name: "2016-1"
                  },
                  reportDDL: "2018-12-18T18:28:39.000+0000",
                  status: 1
                }
              ]
            }
          ]
        },
        {
          roundId: 3,
          showRoundContent: false,
          order: 3,
          course: {
            courseName: "j2ee"
          },
          presentationScoreType: 0,
          reportScoreType: 1,
          questionScoreType: 0,
          seminars: [
            {
              seminarId: 3,
              showSeminarContent: false,
              course: {
                courseId: 4,
                courseName: "ssss"
              },
              round: {
                roundId: 3
              },
              maxTeamNum: 4,
              topic: "3333",
              introduction: "3333",
              signStartTime: "2018-11-17T14:00:00.000+0000",
              signEndTime: "2018-11-24T14:00:00.000+0000",
              signOrder: true,
              visible: false,
              klassSeminars: [
                {
                  klassSeminarId: 3,
                  klass: {
                    klassId: 1,
                    grade: 2016,
                    klassSerial: 1,
                    name: "2016-1"
                  },
                  reportDDL: "2018-12-18T18:28:48.000+0000",
                  status: 2
                }
              ]
            }
          ]
        }
      ]
    };
  },
  //   mounted: function() {
  //     this.$axios
  //       .get(
  //         "/course/" + this.$store.state.teacher.currentCourse.courseId + "/round"
  //       )
  //       .then(response => {
  //         this.rounds = response.data;
  //       });
  //   },
  methods: {
    onClick() {
      console.log("on click");
    },
    back: function() {
      this.$router.push("/mobile/teacher/course");
    },
    more: function() {},
    newround: function() {
      this.$router.push("/mobile/teacher/createround");
    },
    clickClassSeminar: function(klassSeminar) {}
  }
};
</script>
