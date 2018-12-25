<template>
  <div class="course" style="background:#eee">
    <x-header title="新建班级" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>


    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">班级名：
        <input  style="margin-left:20%;width:50px;height:30px" placeholder="年级"/>
        <input  style="margin-left:5%;width:50px;height:30px" placeholder="班级"/>
    </div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">讨论课时间：
        <input  style="margin-left:20%;width:50px;height:30px" placeholder="星期"/>
        <input  style="margin-left:5%;width:50px;height:30px" placeholder="节次"/>
    </div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">讨论课地点：
        <input  style="margin-left:20%;width:100px;height:30px" placeholder="地点"/>
    </div>
    <div style="height:30px;padding-top:12px;text-align:left;margin-top:20px" class="box">班级学生名单：
        <x-button @click="post1" style="font-size:5px;height:20px;width:90px" mini plain>选择文件</x-button>
    </div>






    <x-button @click="newclass" type="primary" style="margin-top:100px;color:#fff">保存</x-button>
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
import {XHeader,XButton,Divider,Group,DatetimeRange,TransferDom,Popup,Cell} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Divider,
        Group,
        DatetimeRange,Popup,Cell
    },
    methods: {
    onChange (val) {
      console.log('change', val)
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
    },
    data () {
        return {
            value: ['周一-第一节-第十节'],
            show:false,
        }
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
