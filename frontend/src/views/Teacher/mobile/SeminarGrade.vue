<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD-书面报告" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

      <x-table :cell-bordered="false" style="background-color:#fff;">
        <thead>
          <tr>
            <th></th>
            <th>展示</th>
            <th>提问</th>
            <th>书面报告</th>
            <th style="color:green">总分</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>213</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
          </tr>
          <tr>
            <td>1-2</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
          </tr>
          <tr>
            <td>1-1</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
          </tr>
          <tr>
            <td>1-2</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
          </tr>
          <tr>
            <td>1-1</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
          </tr>
          <tr>
            <td>1-2</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
          </tr>
        </tbody>
      </x-table>



    <x-button @click.native="comfirm" type="primary" style="margin-top:100px;color:#fff">确认</x-button>
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
import {XHeader,Cell,Group,CellBox,XButton,XTable,TransferDom,Popup} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,
        XButton,
        XTable,Popup
    },
    data(){
        return{
          show:false,
          roundScore:'',
          teamScores:[null,null,null,null,null,null]
        } 
    },
    mounted:function(){
      this.teamScores=[null,null,null,null,null,null]
      this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/round/'+this.$store.state.teacher.currentRound.roundId+'/teamscore')
      .then((res)=>{
        this.roundScore=res.data
        for(var i=0;i<this.roundScore.length;i++){
          if(this.roundScore[i]){
            this.$axios.get('/round/'+this.$store.state.teacher.currentRound.roundId+'/team/'+roundScore[i].team.teamId+'/score')
            .then((res)=>{
              this.teamScores[i]=res.data
            })
          }
        }
      })
    },
    methods: {
    comfirm:function(){
          this.$router.push('/mobile/teacher/seminarFinished')
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
