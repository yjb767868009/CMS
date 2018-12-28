<template>
<div class="student" style="height:20px;background:#fff">
    <x-header :title="this.$store.state.student.currentCourse.courseName" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group>
    <template v-for="round in rounds">
      <cell :key="round.roundId" is-link :border-intent="false" :arrow-direction="round.showRoundContent ? 'up' : 'down'"
      @click.native="getRoundScore(round)" value-align="left">
      <span style="color:#000">第{{round.order}}轮</span>
      </cell>

        <template v-if="round.showRoundContent">
            <template v-for="seminar in round.seminars">
            <cell  is-link :border-intent="false" :arrow-direction="seminar.showSeminarContent ? 'up' : 'down'"
            @click.native="getSeminarScore(seminar,round)" value-align="left" style="padding-right:10px">
            <span>{{seminar.topic}}</span>
        </cell>

            <template v-if="seminar.showSeminarContent">
                <cell  primary="content" :border-intent="false" value-align="left">
                    <div style="padding-left:20px;color:#000;font-size:0.8em">
                    展示：{{score.preScpre}}<span v-if="score.preScore==null">0</span>&emsp;&emsp;&emsp;提问：{{score.questionScore}}<span v-if="score.questionScore==null">0</span>&emsp;&emsp;&emsp;书面报告：{{score.reportScore}}<span v-if="score.reportScore==null">0</span>
                    </div>
                </cell>
            </template>
            </template>
                    <p  style="color:#000;padding-left:220px;margin-top:0">本轮成绩：{{roundscore.totalScore}}<span v-if="roundscore.totalScore==null">0</span></p>
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
        myteam:'',
        roundscore:'',
        score:'',
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
        this.$axios.get('/course/'+this.$store.state.student.currentCourse.courseId+'/team')
        .then((response)=>{
            this.myteam=response.data.myTeam;
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
        getSeminarScore:function(Seminar,Round){
            this.$axios.get('/seminar/'+Seminar.seminarId+'/team/'+this.myteam.teamId+'/seminarscore')
            .then((response)=>{
                this.score=response.data;
                Seminar.showSeminarContent = !Seminar.showSeminarContent;
                for(var i=0;i<Round.seminars.length;i++){
                    if(Round.seminars[i].showSeminarContent===true&&Round.seminars[i]!=Seminar){
                        Round.seminars[i].showSeminarContent=false;
                    }
                }
            })
        },
        getRoundScore:function(Round){
            this.$axios.get('/round/'+Round.roundId+'/team/'+this.myteam.teamId+'/roundscore')
            .then((response)=>{
                this.roundscore=response.data;
                Round.showRoundContent = !Round.showRoundContent;
            for(var i=0;i<this.rounds.length;i++){
                if(this.rounds[i].showRoundContent===true&&this.rounds[i]!=Round){
                    this.rounds[i].showRoundContent=false;
                }
            }
            })
        }
    }
        
  };
</script>