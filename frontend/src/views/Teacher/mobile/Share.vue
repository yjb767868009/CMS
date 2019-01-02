<template>
  <div class="course" style="background:#eee">
    <x-header title="共享设置" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <template v-for="share in shares">
    <group :key="share.shareTeamId">
        <template v-if="myCourseId===share.masterCourse.courseId&&share.status==true">
      <cell style="height:40px"
      :title="share.receiveCourse.courseName+' ('+share.receiveCourse.teacher.name+'老师)'"
      is-link
      :border-intent="false"
      :arrow-direction="share.showContent ? 'up' : 'down'"
      @click.native="current(share,'0')"></cell>
        <template v-if="share.showContent">
            <cell-box :border-intent="false" class="sub-item">共享类型&emsp;&emsp;&emsp;&emsp;&emsp;<span v-if="share.shareSeminarId!=null">共享讨论课</span>
            <span v-if="share.shareTeamId!=null">共享组队</span> </cell-box>
            <cell-box class="sub-item">共享情况&emsp;&emsp;&emsp;&emsp;该课程为从课程</cell-box>
            <x-button @click.native="cancel" mini type='warn'>取消共享</x-button>
        </template>
        </template>

        <template v-if="myCourseId===share.receiveCourse.courseId&&share.status==true">
      <cell
      :title="share.masterCourse.courseName+' ('+share.masterCourse.teacher.name+'老师)'"
      is-link
      :border-intent="false"
      :arrow-direction="share.showContent ? 'up' : 'down'"
      @click.native="current(share,'1')"></cell>

        <template v-if="share.showContent">
            <cell-box :border-intent="false" class="sub-item">共享类型&emsp;&emsp;&emsp;&emsp;&emsp;<span v-if="share.shareSeminarId!=null">共享讨论课</span>
            <span v-if="share.shareTeamId!=null">共享组队</span></cell-box>
            <cell-box class="sub-item">共享情况&emsp;&emsp;&emsp;&emsp;该课程为主课程</cell-box>
            <x-button @click.native="cancel" mini type='warn'>取消共享</x-button>
        </template>
        </template>
    </group>
    </template>

    <x-button @click.native="newshare" style="margin-top:40px" type="primary">新增共享</x-button>
    <div v-transfer-dom>
      <confirm v-model="nomoreshare"
        title="提示"
        @on-confirm="sure">
        <p style="text-align:center;">确定取消与该课程的共享吗？</p>
      </confirm>
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
import {XHeader,Cell,CellBox,Group,XButton,Popup,TransferDom,Confirm} from 'vux'
export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,
        XButton,Popup,Confirm
    },
    data(){
        return{
            show:false,
            showContent001: false,
            shares:'',
            myCourseId:'',
            nomoreshare:false,
            clickcourse:'',
            p:'',
        }   
    },
    mounted:function(){
        this.myCourseId=this.$store.state.teacher.currentCourse.courseId
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/share')
        .then((response)=>{
            this.shares=response.data;
            for(var i=0;i<this.shares.length;i++){
                this.$set(this.shares[i],'showContent',false);
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
        newshare:function(){
            this.$router.push('/mobile/teacher/createshare')
        },
        cancel(){
            this.nomoreshare=!this.nomoreshare
        },
        sure(){
            if(this.p==0){
            this.$axios.delete('/shareteam/'+this.clickcourse)
            .then((res)=>{
                console.log(res)
                window.location.reload()
            })}else if(this.p==1){
                this.$axios.delete('/shareseminar/'+this.clickcourse)
            .then((res)=>{
                console.log(res)
                window.location.reload()
            })
            }
        },
        current(share){
            share.showContent = !share.showContent
            if(share.shareTeamId!=null){
                this.clickcourse=share.shareTeamId
                this.p=0;
            }else if(share.shareSeminarId){
                this.clickcourse=share.shareSeminarId
                this.p=1;
            }
        }
    }
}
</script>

<style scoped>
.sub-item {
  color: #888;
}
.slide {
  padding: 0 20px;
  overflow: hidden;
  max-height: 0;
  transition: max-height .5s cubic-bezier(0, 1, 0, 1) -.1s;
}
.animate {
  max-height: 9999px;
  transition-timing-function: cubic-bezier(0.5, 0, 1, 0);
  transition-delay: 0s;
}
</style>