<template>
  <div class="activation" style="background:#eee">
    <x-header title="密码设置" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="back" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
  <group>
    <x-input v-model="account" style="margin-top:40px;background-color:#fff" placeholder="输入密码" ></x-input>
    <x-input type="password" v-model="password" style="margin-top:20px;background-color:#fff" placeholder="确认密码"></x-input>
    <div style="text-align:left;margin-top:20px">发送验证码邮箱1111@qq.com</div>
    <x-input class="weui-vcode" style="margin-top:20px;background-color:#fff" placeholder="验证码">
        <x-button slot="right" type="warn" mini plain>发送验证码</x-button>
    </x-input>
  </group>
    <div style="text-align:left;margin-top:100px;font-size:10px">可包含数字、字母、下划线，长度不少于六位</div>
    <x-button @click="post1" style="margin-top:10px">确认提交</x-button>
  </div>
</template>

<script>
import {XHeader,XButton,XInput} from 'vux'
import axios from 'axios'

export default {
  data () {
    return {
      account:'',
      password:''
    }
  },
  components:{
    XHeader,
    XButton,
    XInput
  },
  methods:{
    close:function(){

    },
    post1:function(){

    }
  }
}
</script>
