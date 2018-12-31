<template>
  <div class="course">
    <x-header title="新增共享" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group>
        <cell is-link :border-intent="false" :arrow-direction="showContent001 ? 'up' : 'down'"
         @click.native="showContent001 = !showContent001" value-align="left">
        <span style="color:#000">共享类型：<span style="padding-left:50px">{{this.type}}</span>&emsp;&emsp;</span>
        </cell>
    </group>

            <template v-if="showContent001">
                <cell-box  :border-intent="false" class="sub-item" style="padding-left:130px" @click.native="changetype(type1)">共享讨论课</cell-box>
                <cell-box  :border-intent="false" class="sub-item" style="padding-left:135px" @click.native="changetype(type2)">共享分组</cell-box>
            </template>
    <group>
        <cell is-link :border-intent="false" :arrow-direction="showContent002 ? 'up' : 'down'"
         @click.native="newOB" value-align="left">
        <span style="color:#000">共享对象：&emsp;&emsp;&emsp;{{this.shareObject}}</span>
        </cell>
    </group>
            <template v-if="showContent002">
                <template v-for="ob in Objects">
                <template v-if="ob.courseId!=mycourseId">
                <cell-box :key="ob.courseId" :border-intent="false" class="sub-item" style="padding-left:130px" @click.native="makeob(ob)">{{ob.courseName}} ({{ob.teacher.name}})</cell-box>
                </template>
                </template>
            </template>

    <x-button @click.native="newshare" type="primary" style="margin-top:100px;color:#fff">确认共享</x-button>


    <div v-transfer-dom>
      <confirm v-model="request"
      @on-cancel="onCancel"
      @on-confirm="onConfirm">
        <p style="text-align:center;">确认发送共享请求？</p>
      </confirm>
    </div>
    <div v-transfer-dom>
      <popup v-model="show" height="23%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">待办</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div></cell>
          </div>
      </popup>
    </div>
  </div>
</template>

<script>
import {XHeader,XButton,Group,PopupPicker,Confirm,TransferDom,Popup,CellBox,Cell} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Group,
        PopupPicker,
        Confirm,Popup,CellBox,Cell
    },
    mounted:function(){
        this.$axios.get('/allcourse')
            .then((response)=>{
                console.log(response.data)
                this.Objects=response.data
            })
            this.mycourseId=this.$store.state.teacher.currentCourse.courseId
    },
    methods: {
    changetype:function(string){
                this.type=string
                this.showContent001=!this.showContent001
    },
    onChange (val) {
      console.log('change', val)
        },
    onCancel () {
      console.log('on cancel')
    },
    onConfirm () {
        if(this.type=='共享讨论课'){
            // this.$axios.post('/course/'+this.$store.state.teacher.currentCourse.courseId+'/seminarsharerequest',{masterCourse:{courseId:this.$store.state.teacher.currentCourse.courseId},receiveCourse:{courseId:this.followklassId},receiveTeacher:{teacherId:this.followteacherId}})
            // .then((response)=>{
            //     console.log(response.data)
            // })
            this.$message.error('该功能已取消！')
        }else if(this.type=='共享组队'){
            this.$axios.post('/course/'+this.$store.state.teacher.currentCourse.courseId+'/teamsharerequest',{masterCourse:{courseId:this.$store.state.teacher.currentCourse.courseId},receiveCourse:{courseId:this.followklassId},receiveTeacher:{teacherId:this.followteacherId}})
            .then((response)=>{
                console.log(response.data)
                this.$router.push('/mobile/teacher/share')
            })
        }
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
    newOB:function(){
            this.showContent002 = !this.showContent002
        },
    newshare:function(){
      if(this.shareObject==''){
          this.$message.error('请选择共享对象')
      }else{
            this.request=!this.request}
        },
    makeob:function(ob){
        this.shareObject=ob.courseName+'('+ob.teacher.name+')'
        this.followklassId=ob.courseId
        this.followteacherId=ob.teacher.teacherId
        this.showContent002=!this.showContent002
    }
    },
    data () {
        return {
            show:false,
            showContent001:false,
            showContent002:false,
            showContent003:false,
            request:false,
            shareObject:'',
            Objects:'',
            type:'共享讨论课',
            type1:'共享讨论课',
            type2:'共享组队',
            followklassId:'',
            followteacherId:'',
            mycourseId:'',
        }
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
