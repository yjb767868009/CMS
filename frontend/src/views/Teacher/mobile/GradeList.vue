<template>
  <div class="course" style="background:#eee">
    <x-header title="学生成绩" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group>
      <!-- <template v-for="round in rounds">
        <cell style="height:30px"
          :key="round.roundId"
          is-link
          value-align="left"
          :border-intent="false"
          :arrow-direction="round.showRoundContent ? 'up' : 'down'"
          @click.native="getTeamInRound(round)"
        >第{{round.order}}轮</cell>


        <template v-if="round.showRoundContent">

        </template>
      </template> -->

      <template v-for="team in allteam">
            <cell :key="team.teamId" is-link :border-intent="false" :arrow-direction="team.showTeamContent ? 'up' : 'down'"
                @click.native="showOneTeam(team)" value-align="left">
                
                <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/>
                <span style="color:#000">{{team.teamName}}</span> <span v-if="!team.valid" style="color:#E80000">invalid</span>
            </cell>
            <!-- 显示所有小组的列表 -->
            <template v-if="team.showTeamContent">
        <template v-for="round in rounds">
        <cell style="height:30px"
          :key="round.roundId"
          :title="'第'+round.order+'轮'"
          is-link
          value-align="left"
          :border-intent="false"
          :arrow-direction="round.showRoundContent ? 'up' : 'down'"
          @click.native="getTeamInRound(round,team)"
        ></cell>


        <!-- 小组的round -->
        <template v-if="round.showRoundContent">
          <template v-for="seminar in round.seminars">
            <cell :key="seminar.seminarId" is-link :border-intent="false" :arrow-direction="seminar.showSeminarContent ? 'up' : 'down'"
            @click.native="getSeminarScore(team,seminar,round)" value-align="left" style="padding-right:10px">
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
        
            </template><p  style="color:#000;padding-left:180px;margin-top:0">课程总成绩：未结课</p>
        </template>
    </template>
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
import {XHeader,Cell,CellBox,Group,TransferDom,Popup} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,Popup
    },
    data(){
        return{
            score:'',
            show:false,
            roundScore:'',
            rounds: [],
             allteam:[{
          teamId:'2',
          teamName:'2-6',
          valid:true,
          showMemberContent:false,
        },],
    }   
    },
    mounted: function() {
      this.$axios
        .get(
          "/course/" + this.$store.state.teacher.currentCourse.courseId + "/round"
        )
        .then(response => {
          this.rounds = response.data;
          //为返回数据绑定show属性用于折叠显示
          for(var i=0;i<this.rounds.length;i++){
              this.$set(this.rounds[i],'showRoundContent',false)
              for(var j=0;j<this.rounds[i].seminars.length;j++){
                  this.$set(this.rounds[i].seminars[j],'showSeminarContent',false)
              }
          }
        });

        //获取team列表
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/team')
        .then((response)=>{
            this.allteam=response.data.teams
            for(var i=0;i<this.allteam.length;i++){
              this.$set(this.allteam[i],'showTeamContent',false)
            }
        })
        
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        back:function(){
            this.$router.push('/mobile/teacher')
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
    getTeamInRound(Round,Team){
        this.$axios.get('/round/'+Round.roundId+'/team/'+Team.teamId+'/roundscore')
            .then((response)=>{
                this.roundscore=response.data;
                Round.showRoundContent=!Round.showRoundContent
                for(var i=0;i<this.rounds.length;i++){
                if(this.rounds[i].showRoundContent===true&&this.rounds[i]!=Round){
                    this.rounds[i].showRoundContent=false;
                    
                    for(var j=0;j<this.rounds[i].seminars.length;j++){
                    if(this.rounds[i].seminars[j].showSeminarContent===true&&this.rounds[i]!=Round){
                        this.rounds[i].seminars[j].showSeminarContent=false;
                    }
                }

                }
            }
        })
    },getSeminarScore:function(Team,Seminar,Round){
            this.$axios.get('/seminar/'+Seminar.seminarId+'/team/'+Team.teamId+'/seminarscore')
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
    showOneTeam(Team){
        Team.showTeamContent=!Team.showTeamContent
        for(var i=0;i<this.allteam.length;i++){
            if(this.allteam[i].showTeamContent===true&&this.allteam[i]!=Team){
                this.allteam[i].showTeamContent=false;
            }
        }
    }
    
    }
}
</script>
