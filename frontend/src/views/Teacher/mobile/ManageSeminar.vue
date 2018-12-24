<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD讨论课" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <cell title="业务流程分析" style="background-color:#fff">
      <button style="color:#000;background:0;border:0" @click="pause">
        <img slot="right" @click.native='pause' src="@/assets/pause.png" style="display:block;margin-right:10px;" width="20px" height="20px" />
      </button>
      <confirm v-model="pause" :title="确认暂停" :content="OOAD业务流程讨论课"></confirm>
    </cell>
    
    <div>
      <group title="1.1组展示" v-model="value">
        <countdown v-model="time" @on-finish="finish" v-show="show"></countdown>
        <radio :options="radio001"></radio>
      </group>
      <div style="margin-top:20px">
        展示分数
        <input slot="right" style="margin-left:40%;width:50px;height:30px" placeholder="成绩" />
      </div>

      <group title="1.1组提问">
        <radio :options="radio002"></radio>
      </group>
    </div>


    <x-button @click="comfirm" type="primary" style="margin-top:100px;color:#fff;width:50%">抽取提问</x-button>
    <x-button @click="modify" type="primary" plain style="margin-top:10px;width:50%">下组展示</x-button>
    
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
  import {
    XHeader,
    Cell,
    Group,
    XButton,
    XTable,
    Icon,
    Radio,
    Countdown,
    Confirm,TransferDom,Popup
  } from 'vux'
  export default {
  directives:{
    TransferDom
  },
    components: {
      XHeader,
      Cell,
      Group,
      XButton,
      XTable,
      Icon,
      Radio,
      Countdown,
      Confirm,
      Popup
    },
    data() {
      return {
        grade: '1',
        radio001: ['1-1', '1-2', '1-3'],
        radio002: ['111', '1111', '11111'],
        show: true,
        time: 300,
        value: '',
        show:false,
      }
    },
    methods: {
      onClick() {
        console.log('on click')
      },
      back: function () {
        this.$router.push('/mobile/teacher/seminar')
      },
      more: function () {},
      comfirm: function () {

      },
      modify: function () {

      },
      pasue:function(){
          console.log('pause')
      },
      finish(index) {
        this.show = false
        this.value = 'completed'
        console.log('current index', index)
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
