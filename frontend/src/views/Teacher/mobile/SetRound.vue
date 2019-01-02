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
          <template v-for="klassEnrollNumber in round.klassEnrollNumbers">
            <popup-picker
              :key="klassEnrollNumber.klass.id"
              :title="'第'+klassEnrollNumber.klass.klassSerial+'班'"
              :data="enrollNums"
              v-model="klassEnrollNumber.enrollNumber"
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
      
      round:{
            "roundId": 3,
            "order": 1,
            "course": {
                "courseId": 16,
                "courseName": "OOAD"
            },
            "presentationScoreType": 0,//最高分是0，平均分是1
            "reportScoreType": 0,
            "questionScoreType": 0,
            "klassEnrollNumbers": [
                {
                    "klass": {
                        "klassId": 21,
                        "grade": 2016,
                        "klassSerial": 1,
                        "name": "2016-1"
                    },
                    "enrollNumber": 1
                },
                {
                    "klass": {
                        "klassId": 22,
                        "grade": 2016,
                        "klassSerial": 2,
                        "name": "2016-2"
                    },
                    "enrollNumber": 1
                },
                {
                    "klass": {
                        "klassId": 23,
                        "grade": 2016,
                        "klassSerial": 3,
                        "name": "2016-3"
                    },
                    "enrollNumber": 2
                }
            ]
        },
    };
  },
  computed:{
    postRound:function(){
    }
  },
  mounted:function(){
    this.$axios.get('/round/'+this.$store.state.teacher.currentRound.roundId)
    .then((response)=>{
      this.round=response.data
      for(var i=0;i<this.round.klassEnrollNumbers.length;i++){
        this.round.klassEnrollNumbers[i].enrollNumber
        =[this.round.klassEnrollNumbers[i].enrollNumber]
      }
    })
  },
  methods: {
    modify: function() {
      if(this.calculatePreType[0]==='最高分'){
        this.round.presentationScoreType=0
      }else{
        this.round.presentationScoreType=1
      }

      if(this.calculateQueType[0]==='最高分'){
        this.round.questionScoreType=0
      }else{
        this.round.questionScoreType=1
      }

      if(this.calculateRepType[0]==='最高分'){
        this.round.reportScoreType=0
      }else{
        this.round.reportScoreType=1
      }
      for(var i=0;i<this.round.klassEnrollNumbers.length;i++){
        this.round.klassEnrollNumbers[i].enrollNumber
        =parseInt(this.round.klassEnrollNumbers[i].enrollNumber)
      }
      console.log(this.round)

      this.$axios.put(
        "/round/" + this.$store.state.teacher.currentRound.roundId,this.round
        ).then((response)=>{
          this.$router.go(-1)
        })
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
}
</script>

<style scoped>
.box {
  padding: 0 15px;
}
.picker-buttons {
  margin: 0 15px;
}
</style>
