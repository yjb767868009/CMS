<template>
  <div >
    <x-header :title="'第'+this.$store.state.teacher.currentRound.order+'轮'" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <div style="height:30px;padding-top:12px;text-align:left" class="box">讨论课：
      <template v-for="seminar in this.$store.state.teacher.currentRound.seminars">
        <cell :key="seminar.seminarId">{{seminar.topic}}</cell>
      </template>
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
        </group>
      </div>
    </div>

    <x-button
      @click.native="modify"
      style="margin-top:50%;margin-left:20%;margin-right:20%;width:60%"
      type="primary"
    >修改</x-button>

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
import { XHeader, XButton, Group, PopupPicker, Picker,Popup,TransferDom,Cell } from "vux";
export default {
  directives:{
    TransferDom
  },
  components: {
    XHeader,
    XButton,
    Group,
    PopupPicker,
    Picker,Popup,Cell
  },
  data() {
    return {
      show:false,
      calculatePreType: ["最高分"],
      calculatePreTypes: [["最高分", "平均分"]],
      calculateQueType: ["最高分"],
      calculateQueTypes: [["最高分", "平均分"]],
      calculateRepType: ["最高分"],
      calculateRepTypes: [["最高分", "平均分"]],

      enrollNums: [["1", "2","3","4","5"]],
      
      classRounds: [
        {
          id: 1,
          enrollNum: ['2'],
          classSerial: 2
        }
      ]
    };
  },
  
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
      ).then((response)=>{
        this.$router.go(-1)
      });
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

<style scoped>
.box {
  padding: 0 15px;
}
.picker-buttons {
  margin: 0 15px;
}
</style>
