<template>
  <div style="background:#eee;height:0px">
    <x-header title="学生组队" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}"
      @on-click-more="show=!show">
    </x-header>

    <template v-for="team in allteam">
       <cell :key="team.teamId" is-link :border-intent="false" :arrow-direction="team.showMemberContent ? 'up' : 'down'"
         @click.native="getmembers(team)" value-align="left">
        
        <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/>
        <span style="color:#000">{{team.teamName}}</span> <span v-if="!team.valid" style="color:#E80000">invalid</span>
       </cell>
        <template v-if="team.showMemberContent">
                <cell :key="team.teamId" :border-intent="false" value-align="left" title="组长：" style="height:20px;padding-left:40px"><span style="color:#000">&emsp;{{team.members.leader.account}}&emsp;{{team.members.leader.name}}</span></cell>
                <template v-for="member in team.members.members">
                <cell :key="member.studentId" :border-intent="false" value-align="left" title="组员：" style="height:20px;padding-left:40px"><span style="color:#000">&emsp;{{member.account}}&emsp;{{member.name}}</span></cell>
                </template>
          </template>
    </template>

    <div v-transfer-dom>
      <popup v-model="show" height="23%">
        <div>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div>
          </cell>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div>
          </cell>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div>
          </cell>
        </div>
      </popup>
    </div>
  </div>
</template>


<style scoped>
  .sub-item {
    color: #888;
  }

  .slide {
    padding: 0 20px;
    max-height: 0;
    transition: max-height .5s cubic-bezier(0, 1, 0, 1) -.1s;
  }

  .animate {
    max-height: 9999px;
    transition-timing-function: cubic-bezier(0.5, 0, 1, 0);
    transition-delay: 0s;
  }

</style>

<script>
  import {
    XHeader,
    Cell,
    CellBox,
    Group,
    TransferDom,
    Popup
  } from 'vux'
  export default {
    directives: {
      TransferDom
    },
    components: {
      XHeader,
      Cell,
      CellBox,
      Group,
      Popup
    },
    data() {
      return {
        show: false,
        showContent001: false,
        showContent002: false,
        allteam:[{
          teamId:'2',
          teamName:'2-6',
          valid:true,
          showMemberContent:false,
        },],
        shows:false,
        memb:[{
          teamId:'',
          leader:'',
          members:'',
        }],
      }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/team')
        .then((response)=>{
            this.allteam=response.data.teams
            for(var i=0;i<this.allteam.length;i++){
              this.$set(this.allteam[i],'showMemberContent',false)
            }
        })
    },
    methods: {
      Undo() {
        this.$router.push('/mobile/teacher/notify')
      },
      TeacherInfo() {
        this.$router.push('/mobile/teacher')
      },
      GoSeminar() {
        this.$router.push('/mobile/teacher/seminars')
      },
      getmembers(teams){
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/team/'+teams.teamId)
        .then((res)=>{
            this.$set(teams,'members','')
            teams.members=res.data.team
            teams.showMemberContent=!teams.showMemberContent
        })
      }
    }
  }

</script>
