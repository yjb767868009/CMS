<template>
  <div class="activation" style="background:#eee">
    <x-header title="找回密码" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
  <group>
    <div style="text-align:left;margin-top:20px">发送验证码邮箱1111@qq.com</div>
    <x-input v-model="account" style="margin-top:20px;background-color:#fff" placeholder="学号/教工号" ></x-input>  
    <x-input class="weui-vcode" style="margin-top:20px;background-color:#fff" placeholder="">
        <x-button slot="right" type="warn" mini plain>立即验证</x-button>
    </x-input>
  </group>
    <x-button @click="post1" style="margin-top:100px">确认</x-button>
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
