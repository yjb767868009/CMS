<template>
  <div style="background:#eee;height:1000px">
    <x-header title="学生组队" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}"
      @on-click-more="show=!show">
    </x-header>
  
    <template v-for="t in allteam">
      {{t.teamName+' & '}}
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
        allteam:'',
      }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/team')
        .then((response)=>{
            this.allteam=response.data.teams
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
    }
  }

</script>

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
