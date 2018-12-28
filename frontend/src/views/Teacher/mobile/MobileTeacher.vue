<template>
<div class="student" style="background:#eee">
    <x-header title="我" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <x-header align="right" style="background:#fff;margin:20px 0px 0px;height:90px;padding-top:12px border:1" :left-options="{showBack:false}">
         <div slot="right" style="font-size:1.3em;color:#000;">{{this.myInfo.name}}&emsp;</div>
         <p slot="right" style="font-size:1.3em;color:#000">{{myInfo.account}}&emsp;</p>
    </x-header>
    <group>
      <cell is-link title="我的课程" link="/mobile/teacher/manageCourse"><img slot="icon" src="@/assets/lock.png" style="display:block;margin-right:12px;margin-left:3px" width="15px" height="20px"/></cell>
      <cell is-link title="账户与设置" link="/mobile/teacher/account"><img slot="icon" src="@/assets/package.png" style="display:block;margin-right:10px;" width="20px" height="20px"/></cell>
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
import axios from 'axios'
import {XHeader,
        XButton,Group,Cell,Popup,TransferDom} from 'vux'

  export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,Popup
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            show:false,
            myInfo:{
                id:'',
                account: "24322016231",
                role:'',
                name: '',
                email: "413142222@qq.com",
            }
        }
    },
    mounted:function(){
        this.$axios.get('/user/information')
        .then((response)=>{
            this.myInfo.account=response.data.account;
            this.myInfo.name=response.data.name;
            this.$store.state.teacher.account=response.data.account;
            this.$store.state.teacher.name=response.data.name;
        })
        this.$axios.get('/user/email')
        .then((response)=>{
            this.$store.state.email=response.data;
        })
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
        
  };
</script>