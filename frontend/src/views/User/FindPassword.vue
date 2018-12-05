<template>
  <div class="activation" style="background:#eee">
    <x-header title="找回密码" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
    </x-header>
  <group>
    <x-input v-model="account" style="margin-top:20px;background-color:#fff" placeholder="学号/教工号" ></x-input>  
    <x-input v-model="checkCode" class="weui-vcode" style="margin-top:20px;background-color:#fff" placeholder="">
        <x-button @click.native="check" slot="right" type="warn" mini plain>立即验证</x-button>
    </x-input>
  </group>
    <x-button @click.native="next" style="margin-top:100px">下一步</x-button>
  </div>
  
</template>

<script>
import {XHeader,XButton,XInput,Toast} from 'vux'
import Qs from 'qs'

export default {
  data () {
    return {
      account:'',
      checkCode:'',
      serverCode,
      showPositionValue:false,
      position:'center',
      toastText:''
    }
  },
  components:{
    XHeader,
    XButton,
    XInput,
    Toast
  },
  methods:{
    check:function(){
      this.$axios({
        methods:'post',
        url:'/api/user/findPassword?'+Qs.stringify({
          account:this.account
        }).then((response)=>{
          if(response.message.data==='Success'){
            console.log('already send email')
          }
        })
      })
    },

    next:function(){
      this.$axios({
        method:'post',
        url:'/api/user/checkCode?'+Qs.stringify({
          account:this.account,
          checkCode:this.checkcode
        })
      }).then((response)=>{
        if(response.data.message==='Success'){
          this.$router.push('/mobile/modifypw')
        }else{
          this.$message.error('验证码错误')
        }
      })
    },

  }
}
</script>
