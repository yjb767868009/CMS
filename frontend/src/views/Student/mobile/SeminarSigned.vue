<template>
  <div>
    <x-header
      :title="this.$store.state.student.currentCourse.courseName"
      style="height:60px;padding-top:12px;font-size:20px"
      :left-options="{showBack:false}"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>

      <cell :title="'轮次'" :value="'第'+this.$store.state.student.currentRound.order+'轮'"></cell>
      <cell :title="'主题'" :value="this.$store.state.student.currentSeminar.topic"></cell>
      <cell :title="'课次序号'" :value="this.$store.state.student.currentSeminar.klassSeminars[0].klass.klassSerial"></cell>
      <x-textarea :title="'要求'" :show-counter="false" :placeholder="this.$store.state.student.currentSeminar.introduction" disabled></x-textarea>
      <cell :title="'报名:'">已报名</cell>
      <cell :title="'课程情况'">已完成&emsp;&emsp;&emsp;<a @click="checkInfo" style="text-decoration:underline;color:#1AAD19">报名情况</a></cell>

      <template v-if="is_due">
          <!-- 截止 -->
          <template v-if="this.$store.state.student.currentAttendance.attendance[attendanceIndex].presentationFile">
            <cell title="PPT">已提交</cell>
          </template>
          <template v-if="!this.$store.state.student.currentAttendance.attendance[attendanceIndex].presentationFile">
            <cell title="PPT">未提交</cell>
          </template>
          <template v-if="this.$store.state.student.currentAttendance.attendance[attendanceIndex].reportFile">
            <cell title="书面报告">已提交</cell>
          </template>
          <template v-if="!this.$store.state.student.currentAttendance.attendance[attendanceIndex].reportFile">
            <cell title="书面报告">未提交</cell>
          </template>
          <x-button type="primary" style="margin-top:18px;color:#fff">查看成绩</x-button>
      </template>

      <template v-if="!is_due">
          <!-- 未截止 -->
          <template v-if="this.$store.state.student.currentAttendance.attendance[attendanceIndex].presentationFile">
            <cell title="PPT">已提交</cell>
          </template>
          <template v-if="!this.$store.state.student.currentAttendance.attendance[attendanceIndex].presentationFile">
            <cell title="PPT">未提交</cell>
            <el-upload :action="'http://localhost:8000/attendance/'+this.$store.state.student.currentAttendance.attendance[attendanceIndex].attendanceId+'/powerpoint'"
            :headers="headers">
                <x-button type="primary" style="width:200px;margin-top:18px;color:#fff">提交PPT</x-button>
            </el-upload>
          </template>
          <template v-if="this.$store.state.student.currentAttendance.attendance[attendanceIndex].reportFile">
            <cell title="书面报告">已提交</cell>
          </template>
          <template v-if="!this.$store.state.student.currentAttendance.attendance[attendanceIndex].reportFile">
            <cell title="书面报告">未提交</cell>
            <el-upload :action="'http://localhost:8000/attendance/'+this.$store.state.student.currentAttendance.attendance[attendanceIndex].attendanceId+'/report'"
            :headers="headers">
                <x-button type="primary" style="width:200px;margin-top:18px;color:#fff">提交书面报告</x-button>
            </el-upload>
          </template>
      </template>


    <div v-transfer-dom>
      <confirm
        v-model="submit"
        ref="confirm"
        :show-cancel-button="false"
        title="上传文件"
        show-input
        @on-confirm="onConfirm"
        @on-show="onshow"
      ></confirm>
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
.vux-header-back {
  padding: 9px;
  font-size: 1.1em;
}
.left-arrow {
  margin-top: 10px;
}
.vux-header-more {
  padding-top: 9px;
}
</style>


<script>
import axios from "axios";
import moment from "moment"
import {
  XHeader,
  XButton,
  GroupTitle,
  Cell,
  Picker,
  TransferDom,
  Popup,
  Confirm,
  Flexbox,
  FlexboxItem,XTextarea
} from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XHeader,
    XButton,
    GroupTitle,
    Cell,
    Popup,
    Confirm,
    Flexbox,
    FlexboxItem,XTextarea
  },
  data() {
    return {
      show: false,
      submit: false,
      is_due:false,
    };
  },
  computed:{
    headers(){
      return{
        'Authorization':"Bearer "+this.$store.state.token
      }
    },
    attendanceIndex(){
      for(var i=0;i<this.$store.state.student.currentAttendance.attendance.length;i++){
        if(this.$store.state.student.currentAttendance.attendance[i].teamOrder===
        this.$store.state.student.currentAttendance.message){
          return i
        }
      }
    },
  },
  mounted:function(){
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/round')
        .then((response)=>{
            this.rounds=response.data;
            for(var i=0;i<this.rounds.length;i++){
                if(this.rounds[i].roundId===this.$store.state.student.currentRound.roundId){
                    for(var j=0;j<this.rounds[i].seminars;j++){
                        if(this.rounds[i].seminars[j].seminarId===this.$store.state.student.currentSeminar.seminarId){
                            if(parseInt(this.rounds[i].seminars[j].klassSeminars[0].status)===0&&this.$store.state.student.currentAttendance.message==='other'){
                                console.log('未开未报')
                                this.$router.push({name:'SeminarRegistration'})
                            }else if(parseInt(this.rounds[i].seminars[j].klassSeminars[0].status)===1&&this.$store.state.student.currentAttendance.message==='other'){
                                console.log('正在未报')
                                this.$router.push({name:'SeminarDetail'})
                            }
                            else if(parseInt(this.rounds[i].seminars[j].klassSeminars[0].status)===2&&this.$store.state.student.currentAttendance.message==='other'){
                                console.log('已完未报')
                                this.$router.push({name:'SeminarSeqFinished'})
                            }else if(parseInt(this.rounds[i].seminars[j].klassSeminars[0].status)===0&&this.$store.state.student.currentAttendance.message!=='other'){
                                console.log('未开始已报')
                                this.$router.push({name:'SeminarUnstartSigned'})
                            }else if(parseInt(this.rounds[i].seminars[j].klassSeminars[0].status)===1&&this.$store.state.student.currentAttendance.message!=='other'){
                                console.log('正在已报')
                                this.$router.push({name:'SeminarRunningSigned'})
                            }
                            else if(parseInt(this.rounds[i].seminars[j].klassSeminars[0].status)===2&&this.$store.state.student.currentAttendance.message!=='other'){
                                console.log('已完已报')
                            }
                        }
                    }
                }
            }
          });
    },
  mounted:function(){
    this.is_due=moment(this.$store.state.student.currentSeminar.klassSeminars[0].reportDDL)<moment()
  },
  methods: {
    state: function() {
      this.$router.push("/mobile/student/course/seminar/finished");
    },
    alter: function() {
      this.$router.push(
        "/mobile/student/course/seminar/seminarRegistrationModification"
      );
    },
    running: function() {
      this.$router.push("/mobile/Student/studentSeminarList");
    },
    StudentInfo: function() {
      this.$router.push("/mobile/student/studentInfo");
    },
    onCancel: function() {
      console.log("取消");
    },
    onConfirm: function() {
      console.log("确认");
    },
    onshow: function() {
      this.$refs.confirm.setInputValue("业务流程分析1-1.doc");
    },
    score: function() {
      this.$router.push("/mobile/student/course/seminar/seminarScore");
    },
    checkInfo:function(){
            this.$router.push('/mobile/student/course/seminar/seminarRegistrationModification')
        },
  }
};
</script>