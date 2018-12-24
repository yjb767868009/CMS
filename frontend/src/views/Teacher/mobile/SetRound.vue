<template>
  <div class="course" style="background:#eee">
    <x-header
      title="第一轮"
      style="background-color:#35495e;height:60px;padding-top:12px"
      :left-options="{showBack:false}"
    ></x-header>
    <div style="height:30px;padding-top:12px;text-align:left" class="box">讨论课：
      <div style="padding:0 50px;padding-top:12px">业务流程分析</div>
      <div style="padding:0 50px;padding-top:12px">领域模型设计</div>
    </div>

    <div style="height:30px;padding-top:12px;text-align:left;margin-top:100px" class="box">成绩设置：
      <div>
        <group style="height:20px" label-width="5em">
          <popup-picker title="展示:" :data="calculatePreTypes" v-model="calculatePreType"></popup-picker>
          <popup-picker title="提问:" :data="calculateQueTypes" v-model="calculateQueType"></popup-picker>
          <popup-picker title="报告:" :data="calculateRepTypes" v-model="calculateRepType"></popup-picker>
        </group>
      </div>
    </div>

    <div
      style="height:30px;padding-top:12px;text-align:left;margin-top:140px"
      class="box"
    >本轮讨论课报名次数：
      <div>
        <group style="height:20px" label-width="5em">
          <template v-for="classRound in classRounds">
            <popup-picker
              :key="classRound.id"
              :title="'第'+classRound.classSerial+'班'"
              :data="enrollNums"
              v-model="classRound.enrollNum"
            ></popup-picker>
          </template>
          <!-- <popup-picker title="一班" :data="list2" v-model="value2"></popup-picker>
          <popup-picker title="二班" :data="list2" v-model="value2"></popup-picker>
          <popup-picker title="三班" :data="list2" v-model="value2"></popup-picker> -->
        </group>
      </div>
    </div>

    <x-button
      @click.native="modify"
      style="margin-top:100%;margin-left:20%;margin-right:20%;width:60%"
      mini
      type="primary"
    >修改</x-button>
  </div>
</template>

<script>
import axios from "axios";
import { XHeader, XButton, Group, PopupPicker, Picker } from "vux";
export default {
  components: {
    XHeader,
    XButton,
    Group,
    PopupPicker,
    Picker
  },
  data() {
    return {
      calculatePreType: ["最高分"],
      calculatePreTypes: [["最高分", "平均分"]],
      calculateQueType: ["最高分"],
      calculateQueTypes: [["最高分", "平均分"]],
      calculateRepType: ["最高分"],
      calculateRepTypes: [["最高分", "平均分"]],
      enrollNums: [["1", "2"]],
      roundSeminars: [
        {
          id: 45,
          topic: "业务流程分析",
          order: 2
        }
      ],
      classRounds: [
        {
          id: 1,
          enrollNum: 2,
          classSerial: 2
        }
      ]
    };
  },
  // mounted: function() {
  //   this.$axios
  //     .get(
  //       "/round/" + this.$store.state.teacher.currentRound.roundId + "/seminar"
  //     )
  //     .then(response => {
  //       this.roundSeminars = response.data;
  //     });
  //   this.$axios
  //     .get("/round/" + this.$store.state.teacher.currentRound.roundId)
  //     .then(response => {
  //       this.calculatePreType = response.data.calculatePreType;
  //       this.calculateQueType = response.data.calculateQueType;
  //       this.calculateRepType = response.data.calculateRepType;
  //     });
  // },
  methods: {
    modify: function() {
      this.$axios.put(
        "/round/" + this.$store.state.teacher.currentRound.roundId,
        {
          calculatePreType: this.calculatePreType[0],
          calculateQueType: this.calculateQueType[0],
          calculateRepType: this.calculateRepType[0],
          classRound: this.classRounds
        }
      );
    }
  }
};
</script>

<style scoped>
.box {
  padding: 0 15px;
}
.picker-buttons {
  margin: 0 15px;
}
</style>
