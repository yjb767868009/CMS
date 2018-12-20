<template>
  <div class="activation" style="background:#eee">
    <x-header title="密码设置" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
      <button @click="back" style="background:0;height:30px;border:0" slot="left">
        <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
      </button>
    </x-header>
    <group>
      <x-input
        type="password"
        v-model="password1"
        style="margin-top:40px;background-color:#fff"
        placeholder="输入密码"
      />
      <x-input
        type="password"
        v-model="password2"
        style="margin-top:20px;background-color:#fff"
        placeholder="确认密码"
      />
      <x-input v-model="email" style="margin-top:40px;background-color:#fff" placeholder="填写邮箱"/>
    </group>
    <div style="text-align:left;margin-top:100px;font-size:10px">可包含数字、字母、下划线，长度不少于六位</div>
    <x-button @click.native="post" style="margin-top:10px">确认提交</x-button>
  </div>
</template>

<script>
import { XHeader, XButton, XInput } from "vux";
import axios from "axios";

export default {
  data() {
    return {
      password1: "321321",
      password2: "321",
      email:""
    };
  },
  components: {
    XHeader,
    XButton,
    XInput
  },
  methods: {
    post:function(){
      if(this.password1!==this.password2){
        this.$message.error('两次密码')
      }else{
        this.$axios.put('/student/active',{
          password:this.password,
          email:this.email
        })
      }
    }
  }
};
</script>
