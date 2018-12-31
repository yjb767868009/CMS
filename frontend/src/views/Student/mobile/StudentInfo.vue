<template>
<div class="student" style="height:370px;background:#eee">
    <x-header title="我" style="height:60px;padding-top:12px" :left-options="{showBack:false}"  :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <x-header align="right" style="background:#fff;margin:20px 0px 0px;height:90px;padding-top:12px border:1" :left-options="{showBack:false}">
         <div slot="right" style="font-size:1.3em;color:#000;">{{this.myInfo.name}}&emsp;</div>
         <p slot="right" style="font-size:1.3em;color:#000">{{this.myInfo.account}}&emsp;</p>
    </x-header>
    <group>
      <cell is-link title="我的课程" link="/mobile/Student/myClass"><img slot="icon" src="@/assets/lock.png" style="display:block;margin-right:12px;margin-left:3px" width="15px" height="20px"/></cell>
      <cell is-link title="账户与设置" link="/mobile/Student/stuSetAccount"><img slot="icon" src="@/assets/package.png" style="display:block;margin-right:10px;" width="20px" height="20px"/></cell>
    </group>
    {{this.$store.state.student.myId}}
	<!--个人信息和讨论课弹框栏，x-header的onlick-more中与Popup的v-model绑定，工具中引入Popup和TransferDom-->
	<div v-transfer-dom>
      <popup v-model="show" height="15%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div></cell>
          </div>
      </popup>
    </div>
</div>
</template>

<style>
    .vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import axios from 'axios'
import {TransferDom,XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,Popup,
        ButtonTabItem} from 'vux'
  export default {
	directives:{
        TransferDom
    },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,
        Actionsheet,
        ButtonTab,
		ButtonTabItem,
		Popup
    },
    mounted:function(){
        this.$axios.get('/user/information')
        .then((response)=>{
            this.myInfo.account=response.data.account;
            this.myInfo.name=response.data.name;
            this.$store.state.student.account=response.data.account;
            this.$store.state.student.name=response.data.name;
        })
        this.$axios.get('/user/email')
        .then((response)=>{
            this.$store.state.email=response.data;
        })
    },
    data() {
        return{
			newItem:'newItem',
            show:false,
            myInfo:{
                id:'',
                account: "24322016231",
                role:'',
                name: "张三",
                email: "413142222@qq.com",
            }
		}
    },
    methods:{
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        }
    }
        
  };
</script>