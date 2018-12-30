<template>
  <div class="course" style="background:#eee">
    <x-header title="共享设置" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <template v-for="share in shares">
    <group :key="share.shareTeamId">
        <template v-if="myCourseId===share.masterCourse.courseId">
      <cell style="height:40px"
      :title="share.receiveCourse.courseName+' ('+share.receiveCourse.teacher.name+'老师)'"
      is-link
      :border-intent="false"
      :arrow-direction="share.showContent ? 'up' : 'down'"
      @click.native="current(share,'0')"></cell>
        <template v-if="share.showContent">
            <cell-box :border-intent="false" class="sub-item">共享类型&emsp;&emsp;&emsp;&emsp;&emsp;共享组队 </cell-box>
            <cell-box class="sub-item">共享情况&emsp;&emsp;&emsp;&emsp;该课程为从课程</cell-box>
            <x-button @click.native="cancel" mini type='warn'>取消共享</x-button>
        </template>
        </template>

        <template v-if="myCourseId===share.receiveCourse.courseId">
      <cell
      :title="share.masterCourse.courseName+' (邱明老师)'"
      is-link
      :border-intent="false"
      :arrow-direction="share.showContent ? 'up' : 'down'"
      @click.native="current(share,'1')"></cell>

        <template v-if="share.showContent">
            <cell-box :border-intent="false" class="sub-item">共享类型&emsp;&emsp;&emsp;&emsp;&emsp;共享组队 </cell-box>
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
            this.$axios.delete('/shareteam/'+this.clickcourse)
            .then((res)=>{
                console.log(res)
            })
        },
        current(share,p){
            share.showContent = !share.showContent
            if(p=='0'){
                this.clickcourse=share.receiveCourse.courseId
            }else if(p=='1'){
                this.clickcourse=share.masterCourse.courseId
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