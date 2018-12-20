<template>
  <div class="activation" style="background:#eee">
    <x-header title="账户邮箱" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="back" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
  <group>

    <x-input v-model="account" style="margin-top:20px;background-color:#fff" title="e-mail:" placeholder="填写邮箱"></x-input>  
    <x-input class="weui-vcode" style="margin-top:20px;background-color:#fff" title="验证码:" placeholder="填写验证码">

    </x-input>
    <x-button style="margin-left:60%;margin-top:10px" type="warn" mini plain>立即验证</x-button>
  </group>
        <div style="text-align:left;margin-top:100px;font-size:10px">邮箱格式如：useremail@host.domainnames</div>
    <x-button @click="post" style="margin-top:10px">确认提交</x-button>
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
    post:function(){

    }
  }
}
</script>
