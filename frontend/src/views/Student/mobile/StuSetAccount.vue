<template>
<div class="student" style="background:#eee;">
    <x-header title="账户与设置" style="height:60px;padding-top:12px;font-size:20px" :left-options="{showBack:false}">
    </x-header>
    <cell primary="content" title="姓名：" value-align="left" style="background:#fff"><div style="color:#000;padding-left:110px">{{this.$store.state.student.name}}</div></cell>
    <cell primary="content" title="学号：" value-align="left" style="background:#eee"><div style="color:#000;padding-left:70px">{{this.$store.state.student.account}}</div></cell>
    <cell primary="content" title="联系方式(邮箱)：" value-align="left" style="background:#fff">
        <div style="color:#000;padding-left:0px">{{this.$store.state.email}}<span @click="modifyEmail" style="text-decoration:underline;color:#DC143C;font-size:0.5em;">修改</span>  
        </div>  
    </cell>
    <cell is-link title="账户密码" link="/mobile/modifypw" style="font-size:1.1em;padding-left:18px"></cell>
    <cell primary="content" title="管理员邮箱：" value-align="left" style="background:#fff"><div style="color:#000;padding-left:70px">86738468@qq.com</div></cell>
    
    <x-button type="warn" style="margin-top:40%" @click.native="out=!out">退出登录</x-button>

    <div v-transfer-dom>
        <confirm v-model="out"
        title="提示"
        theme="android"
        @on-cancel="onCancel"
        @on-confirm="onConfirm">
        <p style="text-align:center;">确认退出吗</p>
      </confirm>
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
.vux-header-back{
    padding:9px;
    font-size:1.1em;
  }
.left-arrow{
    margin-top:10px;
}
</style>


<script>
import axios from 'axios'
import {XHeader,
        XButton,GroupTitle,Cell,Picker,Popup,TransferDom,Confirm,Flexbox} from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        XButton,
        GroupTitle,
        Cell,
        Picker,
        Popup,
        Confirm,
        Flexbox
    },
    data() {
       return{ 
           show:false,
           out:false,
           email:'',
    }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        edit:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        logout:function(){
            
        },
        modifyEmail:function(){
            this.$router.push('/mobile/modifyEmail')
        },
        onCancel:function(){
            console.log('取消')
        },
        onConfirm:function(){
            console.log('确认')
            this.$router.push('/')
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        }
    },
        
  };
</script>