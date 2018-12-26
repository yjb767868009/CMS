<template>
<div class="student" style="background:#eee;">

    <template v-if="!modifyingPassword&&!modifyingEmail">
    <x-header title="账户与设置" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <x-header style="background:#fff;margin:20px 0px 0px;height:60px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:6px;">姓名：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;www</div>
    </x-header>
    <x-header style="background:#eee;height:60px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:6px;">学号：&emsp;&emsp;&emsp;25120152202348</div>
    </x-header>
    <x-header style="background:#fff;height:85px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:18px;">联系方式(邮箱)：&emsp;&emsp;135768543@qq.com</div>
         <p slot="right" @click="modifyEmail" style="text-decoration:underline;color:#DC143C;font-size:1.1em;padding-top:30px">修改</p>
    </x-header>
    <cell is-link title="账户密码" @click.native="modifyPassword" style="font-size:1.1em;padding-left:18px"></cell>
    <x-header style="background:#fff;height:85px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:18px;">管理员邮箱：&emsp;&emsp;86738468@qq.com</div>
    </x-header>

    <div style="margin-top:100%">
    <x-button type="warn" @click.native="logOut">退出登陆</x-button>
    </div>

    <!-- 模态框 -->
    <div v-transfer-dom>
      <popup v-model="show" height="23%">
        <div>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div>
          </cell>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div>
          </cell>
          <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;"
              width="30px" height="30px" />
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div>
          </cell>
        </div>
      </popup>
    </div>
    </template>

    <template v-if="modifyingPassword&&!modifyingEmail">
        <!-- 修改密码 -->
    <x-header title="修改密码" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: false}">
    </x-header>
    <group>
    <x-input title="旧密码" placeholder="原密码" type="password" v-model="oldPassword" placeholder-align="right" text-align="right"></x-input>
    <x-input title="新密码" placeholder="填写新密码" type="password" v-model="newPassword" placeholder-align="right" text-align="right"></x-input>
    <x-input title="确认密码" placeholder="再次填写确认" type="password" v-model="newConfrimPassword" placeholder-align="right" text-align="right"></x-input>
    <x-button type="primary" @click.native="postPassword">确认提交</x-button>
    </group>
    </template>

    <template v-if="modifyingEmail&&!modifyingPassword">
    <x-header title="账户邮箱" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: false}">
    </x-header>
    <group>
    <x-input title="e-mail" placeholder="填写新邮箱" type="email" v-model="newEmail" placeholder-align="right" text-align="right"></x-input>
    <div style="margin-top:100%">
    <x-button type="primary" @click.native="postEmail">确认提交</x-button>
    </div>
    </group>
    </template>

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
        XButton,Group,GroupTitle,Cell,Picker,TransferDom,Popup,XInput} from 'vux'
  export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        GroupTitle,
        Cell,
        Picker,Popup,XInput,Group
    },
    data() {
       return{ 
        show:false,
        modifyingEmail:false,
        modifyingPassword:false,
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
        logOut(){
            this.$store.state.token=''
            this.$store.state.userType=''
            this.$router.push('/')
        },
        modifyEmail(){
            this.modifyingEmail=true
            this.newEmail=''
        },
        postEmail(){
            console.log(
                this.newEmail
            )
            this.modifyingEmail=false
        },
        modifyPassword(){
            this.modifyingPassword=true
            this.oldPassword=''
            this.newPassword=''
            this.newConfrimPassword=''
        },
        postPassword(){
            console.log(
                this.oldPassword,
                this.newPassword,
                this.newConfrimPassword
            )
            this.modifyingPassword=false
        },
    }
        
  };
</script>