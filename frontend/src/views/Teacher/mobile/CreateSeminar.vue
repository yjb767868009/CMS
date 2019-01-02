<template>
  <div class="course">
    <x-header :title="this.$store.state.teacher.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <div style="margin-left:10px">
    <div title='新建讨论课' style="text-align:center;margin-top:20px">新建讨论课</div>


    <group>
        <x-input v-model="topic" placeholder="主题  讨论课大纲"></x-input>
        <x-textarea v-model="intro" placeholder="讨论课主要内容 要求 具体讨论部分"></x-textarea>
        <popup-picker title="讨论课次序号" :data="seminarOrders" v-model="seminarOrder"></popup-picker>
        <x-switch v-model="visible" title="讨论课可见" :value-map="[false,true]"></x-switch>

        <datetime style="height:20px" v-model="signupStartTime" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm"  title="报名开始时间"></datetime>
        <datetime style="height:20px" v-model="signupEndTime" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm" title="报名截止时间"></datetime>
        <popup-picker title="报名小组数" :data="teamNums" v-model="teamNum" ></popup-picker>
        <popup-picker title="所属round" :data="roundOrders" v-model="roundOrder"></popup-picker>
    </group>
    </div>

    <x-button @click.native="submit" type="primary" style="margin-top:55px;color:#fff">发布</x-button>
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
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,XSwitch,XTextarea,TransferDom,Popup,Cell} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,XSwitch,XTextarea,Popup,Cell
    },
    methods: {
    change (value) {
      console.log('change', value)
    }
    },
    data () {
        return {
            seminarOrder: ['1'],
            seminarOrders: [['1', '2', '3','4','5']],
            startDate: '2018-01-01',
            endDate: '2020-12-31',
            teamNum: ['6'],
            teamNums: [['1', '2', '3','4','5','6']],
            roundOrder: ['1'],
            roundOrders: [['1' ,'2', '3','4','5','6','7','8','9','10']],
            topic:'',
            intro:'',
            visible:false,
            signupStartTime:'',
            signupEndTime:'',
            show:false,
            roundList:[],
        }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/roundlist')
        .then((res)=>{
            this.roundList=res.data
            this.roundOrders=[this.roundOrders[0].slice(0,this.roundList.length)]

        })
    },
    methods:{
        submit:function(){
            console.log({
                topic:this.topic,
                intro:this.intro,
                signOrder:this.seminarOrder[0],
                round:{roundId:this.roundList[parseInt(this.roundOrder[0])-1].roundId,},
                visible:this.visible,
                maxTeamNum:this.teamNum[0],
                signupStartTime:this.signupStartTime.slice(0,4)+'/'+this.signupStartTime.slice(5,7)+'/'+this.signupStartTime.slice(8,10),
                signupEndTime:this.signupEndTime.slice(0,4)+'/'+this.signupEndTime.slice(5,7)+'/'+this.signupEndTime.slice(8,10),
            })
            this.$axios.post('/course/'+this.$store.state.teacher.currentCourse.courseId+'/seminar',{
                topic:this.topic,
                intro:this.intro,
                signOrder:this.seminarOrder[0],
                round:{roundId:this.roundList[parseInt(this.roundOrder[0])].roundId,},
                visible:this.visible,
                maxTeamNum:this.teamNum[0],
                signupStartTime:this.signupStartTime.slice(0,4)+'/'+this.signupStartTime.slice(5,7)+'/'+this.signupStartTime.slice(8,10),
                signupEndTime:this.signupEndTime.slice(0,4)+'/'+this.signupEndTime.slice(5,7)+'/'+this.signupEndTime.slice(8,10),
            }).then((res)=>{
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

<style>
.box {
  padding: 0 15px;
}
</style>
