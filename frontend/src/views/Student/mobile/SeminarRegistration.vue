<template>
<div>
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px;font-size:20px" :left-options="{showBack:false}" :right-options="{showMore: true}"  @on-click-more="show=!show">
    </x-header>
    
      <cell :title="'轮次'" :value="'第'+this.$store.state.student.currentRound.order+'轮'"></cell>
      <cell :title="'主题'" :value="this.$store.state.student.currentSeminar.topic"></cell>
      <cell :title="'课次序号'" :value="this.$store.state.student.currentSeminar.klassSeminars[0].klass.klassSerial"></cell>
      <x-textarea :title="'要求'" :show-counter="false" :placeholder="this.$store.state.student.currentSeminar.introduction" disabled></x-textarea>
      <cell :title="'课程情况'">未开始&emsp;&emsp;&emsp;<a @click="checkInfo" style="text-decoration:underline;color:#1AAD19">报名情况</a></cell>
      <x-button @click.native="register" type="primary" style="margin-top:18px;color:#fff">报名</x-button>

    <div v-transfer-dom>
        <popup v-model="show" height="15%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div></cell>
          </div>
      </popup>
    </div>
</div>
</template>

<style>
.vux-header-back{
    padding:9px;
    font-size:1.1em;
  }
.left-arrow{
    margin-top:10px;
}
.vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import axios from 'axios'
import {XHeader,
        XButton,Cell,Popup,TransferDom,Flexbox,Confirm,XTextarea} from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        XButton,
        Cell,Popup,Flexbox,Confirm,XTextarea
    },
    data() {
       return{ 
        show:false,
        registration:false,
        rounds:''
    }
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
                                this.$router.push({name:'SeminarSigned'})
                            }
                        }
                    }
                }
            }
          });
    },
    methods:{
        edit:function(){
            this.$router.push('/seminarSeqFinished')
        },
        sign:function(){
            this.$router.push('/seminarSeqUnsigned')
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        checkInfo:function(){
            this.$router.push('/mobile/student/course/seminar/seminarRegistrationModification')
        },
        register:function(){
            this.$router.push('/mobile/student/course/seminar/seminarRegistrationModification')
        }
    }
        
  };
</script>