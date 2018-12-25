<template>
  <div class="course" style="background:#eee">
    <x-header title="课程信息" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <div style="height:30px;padding-top:12px;text-align:left" class="box">课程要求</div>
    <divider></divider>
    <div style="height:30px;padding-top:12px;text-align:left" class="box">
        成绩计算规则：
        <div style='text-align:right'>课堂展示</div>
        <div style='text-align:right'>课堂提问</div>
        <div style='text-align:right'>书面报告</div>
    </div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:50px" class="box">小组人数：</div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">组队开始时间：</div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">组队截止时间：</div>

    <x-button @click="post1" style="margin-top:100px;margin-left:50%" mini plain type="warn">删除课程</x-button>
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
import {XHeader,XButton,TransferDom,Popup,Cell} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,Popup,Cell
    },
    data(){
        return{
            show:false,
        }
    },
    methods:{
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

<style>
.box {
  padding: 0 15px;
}
</style>
