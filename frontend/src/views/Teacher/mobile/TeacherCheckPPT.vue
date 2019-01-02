<template>
  <div>
    <x-header :title="this.$store.state.teacher.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <group>
      <cell :title="'第一组'">
          <template v-if="AttendancesForShow[0]===null">未报名</template>
          <template v-else-if="!AttendancesForShow[0].reportFile">{{AttendancesForShow[0].team.teamName+' 未提交'}}</template>
          <template v-else>{{AttendancesForShow[0].team.teamName+' 已提交'}}</template>
          </cell>
      <cell :title="'第二组'">
          <template v-if="AttendancesForShow[1]===null">未报名</template>
          <template v-else-if="!AttendancesForShow[1].reportFile">{{AttendancesForShow[1].team.teamName+' 未提交'}}</template>
          <template v-else>{{AttendancesForShow[1].team.teamName+' 已提交'}}</template>
      </cell>
      <cell :title="'第三组'">
          <template v-if="AttendancesForShow[2]===null">未报名</template>
          <template v-else-if="!AttendancesForShow[2].reportFile">{{AttendancesForShow[2].team.teamName+' 未提交'}}</template>
          <template v-else>{{AttendancesForShow[2].team.teamName+' 已提交'}}</template>
      </cell>
      <cell :title="'第四组'">
          <template v-if="AttendancesForShow[3]===null">未报名</template>
          <template v-else-if="!AttendancesForShow[3].reportFile">{{AttendancesForShow[3].team.teamName+' 未提交'}}</template>
          <template v-else>{{AttendancesForShow[3].team.teamName+' 已提交'}}</template>
      </cell>
      <cell :title="'第五组'">
          <template v-if="AttendancesForShow[4]===null">未报名</template>
          <template v-else-if="!AttendancesForShow[4].reportFile">{{AttendancesForShow[4].team.teamName+' 未提交'}}</template>
          <template v-else>{{AttendancesForShow[4].team.teamName+' 已提交'}}</template>
      </cell>
      <cell :title="'第六组'">
          <template v-if="AttendancesForShow[5]===null">未报名</template>
          <template v-else-if="!AttendancesForShow[5].reportFile">{{AttendancesForShow[5].team.teamName+' 未提交'}}</template>
          <template v-else>{{AttendancesForShow[5].team.teamName+' 已提交'}}</template>
      </cell>
      <x-button @click.native="back">返回</x-button>
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
import {XHeader,Cell,Group,CellBox,XButton,TransferDom,Popup} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,
        XButton,Popup
    },
    data(){
        return{
            show:false,
            AttendancesForShow:[null,null,null,null,null,null],
        Attendances: [
          {
            "attendanceId": 2,
            "team": {
              "teamId": 21,
              "leader": {
                "studentId": 141,
                "name": "吴悠"
              },
              "teamName": "晚晚鸟"
            },
            "teamOrder": 2,
            "present": false
          },
          {
            "attendanceId": 3,
            "team": {
              "teamId": 20,
              "leader": {
                "studentId": 129,
                "name": "邱学良"
              },
              "teamName": "超励志小组"
            },
            "teamOrder": 3,
            "present": false
          },
          {
            "attendanceId": 4,
            "team": {
              "teamId": 7,
              "leader": {
                "studentId": 156,
                "name": "朱演演"
              },
              "teamName": "FLAG"
            },
            "teamOrder": 4,
            "present": false
          },
          {
            "attendanceId": 5,
            "team": {
              "teamId": 6,
              "leader": {
                "studentId": 122,
                "name": "林晓明"
              },
              "teamName": "Intellij"
            },
            "teamOrder": 5,
            "present": false
          },
          {
            "attendanceId": 6,
            "team": {
              "teamId": 14,
              "leader": {
                "studentId": 126,
                "name": "马子晋"
              },
              "teamName": "Flyway"
            },
            "teamOrder": 6,
            "present": false
          }
        ],
        } 
    },
    mounted:function(){
        this.$axios.get('/klassseminar/'+this.$store.state.teacher.currentKlassSeminar.klassSeminarId+'/attendance')
        .then((res)=>{
            this.Attendances=res.data.attendance
            this.AttendancesForShow=[null,null,null,null,null,null]
        for (var i=1;i<this.AttendancesForShow.length+1;i++){
          for(var j=0;j<this.Attendances.length;j++){
            if(this.Attendances[j].teamOrder===i){
              this.AttendancesForShow[i-1]=this.Attendances[j]
            }
          }
        }
        })
        
    },
    methods: {
        back(){
            this.$router.go(-1)
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
