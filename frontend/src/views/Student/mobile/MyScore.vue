<template>
<div class="student" style="height:20px;background:#fff">
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group>
    <template v-for="round in rounds">
      <cell :key="round.roundId" is-link :border-intent="false" :arrow-direction="round.showRoundContent ? 'up' : 'down'"
      @click.native="getRoundScore(round)" value-align="left">
      <span>第{{round.order}}轮</span>
      </cell>

        <template v-if="round.showRoundContent">
            <template v-for="seminar in round.seminars">
            <cell :key="seminar.seminarId" is-link :border-intent="false" :arrow-direction="seminar.showSeminarContent ? 'up' : 'down'"
            @click.native="getSeminarScore(seminar)" value-align="left" style="padding-left:130px">
            <span>{{seminar.topic}}</span>
        </cell>

            <template v-if="seminar.showSeminarContent">
                <cell :key="seminar.seminarId" primary="content" :border-intent="false" value-align="left">
                    <div style="padding-left:20px;color:#000;font-size:0.8em">
                    展示：{{score.preScpre}}&emsp;&emsp;&emsp;提问：{{score.questionScore}}&emsp;&emsp;&emsp;书面报告：{{score.reportScore}}
                    <p style="padding-left:4px">本次总成绩：{{score.totalScore}}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>
                    </div>
                    <p style="color:#000;padding-left:110px;margin-bottom:5px">本轮成绩：{{roundscore.preScore}}</p>
                </cell>
            </template>
            </template>
        </template>
      </template>
    </group>


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
    .vux-header-more{
        padding-top:9px;
    }
    .weui-cells{
        color:#000;
    }
</style>

<script>
import axios from 'axios'
import {TransferDom,XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup} from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,
        Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup
    },
    data() {
        return{
        newItem:'newItem',
        show:false,
        showContent001: false,
        showContent002:false,
        showContent003:false,
        score:'',
        roundscore:{
  "preScore": 3,
  "reportScore": 4.5,
  "questionScore": 5,
  "totalScore":4,
  "team": {
    "id": 1,
    "name": "早早鸟小组"
  },
  "round": {
    "id": 1,
    "order": 1
  }
},
         rounds: '',
        }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/round')
        .then((response)=>{
            this.rounds=response.data;
            
            this.rounds.forEach(rounditem=>{
              this.$set(rounditem,'showRoundContent',false);
              rounditem.seminars.forEach(seminaritem=>{
                this.$set(seminaritem,'showSeminarContent',false)
              });
            });

            // for(var i=0;i<this.rounds.length;i++){
            //     this.$set(this.rounds[i],'showRoundContent',false);
            //     for(var j=0;j<this.rounds[i].seminars.length;j++){
            //         this.$set(this.rounds[i].seminars[j],'showSeminarContent',false);
            //     }
            // }
        })
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            console.log("sss");
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        getSeminarScore:function(Seminar){
            Seminar.showSeminarContent = !Seminar.showSeminarContent;
            // this.$axios.get('/seminar/'+Seminar.seminarId+'/team/'+this.$store.state.student.myteam.id+'/seminarscore')
            // .then((response)=>{
            //     this.score=response.data;
            // })
        },
        getRoundScore:function(Round){
            Round.showRoundContent = !Round.showRoundContent;
            // this.$axios.get('/seminar/'+Round.roundId+'/team/'+this.$store.state.student.myteam.id+'/roundscore')
            // .then((response)=>{
            //     this.roundscore=response.data;
            // })
        }
    }
        
  };
</script>