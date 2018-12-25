<template>
  <div class="activation" style="background:#eee">
    <x-header title="密码设置" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <!-- <button @click="back" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button> -->
    </x-header>
    <div style="padding-left:10px;margin-top:10px;height:10px">发送验证码到邮箱：{{this.$store.state.student.myemail}}</div>
  <group>
    <x-input type="password" v-model="password1" style="background-color:#fff" placeholder="输入密码"></x-input>
  </group>
  <group>
    <x-input type="password" v-model="password2" style="background-color:#fff" placeholder="确认密码"></x-input>
  </group>
    <x-button @click.native="submit" style="margin-top:100px">确认提交</x-button>
  </div>
</template>

<script>
import {XHeader,XButton,XInput,Group} from 'vux'
export default {
  data () {
    return {
      password1:'',
      password2:'',
    }
  },
  components:{
    XHeader,
    XButton,
    XInput,Group
  },
  methods:{
    submit:function(){
      if(!(this.password1===this.password2)){
        this.$message.error('两次输入密码不一致')
      }else{
        this.$axios.put('/user/password',{
          password:this.password1
        })
      }
    },
    
  }
}
</script>
