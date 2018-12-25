<template>
  <div class="course">
    <x-header title="新建课程" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>



    <group label-width="5em">
        <x-input v-model="courseName" placeholder="课程名称"></x-input>
        <x-textarea v-model="courseRequirement" placeholder="课程要求"></x-textarea>
    </group>

    <div style="height:30px;padding-top:12px;text-align:left" class="box">
        成绩计算规则（百分比）：
            <group style="height:20px" label-width="5em">
            <popup-picker title="展示" :data="percentageOptions" v-model="presentation"></popup-picker>
            <popup-picker title="提问" :data="percentageOptions" v-model="question"></popup-picker>
            <popup-picker title="报告" :data="percentageOptions" v-model="report"></popup-picker>
            </group>
    </div>

    <group label-width="6em" style="margin-top:140px;padding-top:12px;text-align:left">
        <cell is-link title="组队要求:" @click.native="teamRequirement"></cell>
        <datetime v-model="teamStartTime" :start-date="startDateS" :end-date="endDateS" format="YYYY-MM-DD HH:mm"  title="组队开始时间"></datetime>
        <datetime v-model="teamEndTime" :start-date="startDateE" :end-date="endDateE" format="YYYY-MM-DD HH:mm"  title="组队截止时间"></datetime>
    </group>

    <x-button @click.native="newCourse" type="primary" style="margin-top:100px;color:#fff">发布</x-button>
    
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
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,TransferDom,Popup,Cell,XTextarea} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Divider,
        Group,
        Datetime,
        XInput,
        PopupPicker,Popup,Cell,XTextarea
    },
        data () {
        return {
            percentageOptions: [['10','15', '20','25', '30','35', '40','45','50','55','60','65','70','75','80','85','90','95','100']],
            startDateS:'2018-01-01',
            endDateS: '2018-12-31',
            startDateE:'2018-01-01',
            endDateE: '2018-12-31',
            show:false,
            courseName:'',
            courseRequirement:'',
            presentation:['10'],
            question:['10'],
            report:['10'],
            teamStartTime:'',
            teamEndTime:'',
        }
    },
    methods: {
    Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
    TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
    GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
    newCourse(){
        console.log(
        this.courseName,//courseName
        this.courseRequirement,//introduction
        this.presentation[0],//presentationWeight
        this.question[0],//questionWeight
        this.report[0],
        this.teamStartTime,//~~~
        this.teamEndTime,)//~~~
        //rule
    },
    teamRequirement(){
        console.log('team require')
        this.$router.push('')
    }
    },

}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
