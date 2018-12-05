<template>
  <div class="login" style="background:#eee">
    <x-header title="讨论课管理系统登录" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
    </x-header>
  <group>
    <x-input v-model="account" style="margin-top:40px;background-color:#fff" placeholder="学号/教工号" ></x-input>
    <x-input type="password" v-model="password" style="margin-top:20px;background-color:#fff" placeholder="登录密码"></x-input>
  </group>
    <x-button @click="login" style="margin-top:100px">
      登陆
      </x-button>
    <button @click="forget" style="background:0;height:0px;border:0;margin-left:80%;margin-top:10px">
      忘记密码
    </button>
    <div style="color:green;margin-top:50%">
      初次登录默认密码为123456
    </div>
  </div>
</template>

<script>
import {XHeader,XButton,XInput} from 'vux'
import Qs from 'qs'

export default {
  data () {
    return {
      account:'',
      password:''
    }
  },
  mounted(){
    if(this.isMobile()){
      console.log('mobile')
    }else{
      this.$router.replace('/login')
    }
  },
  components:{
    XHeader,
    XButton,
    XInput
  },
  methods:{
    isMobile:function(){
      let flag=
        navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      return flag
    },
    forget:function(){
      this.$router.push('/mobile/modifypw')
    },
    login:function(){
      this.$axios.post('/api/user/login?',Qs.stringify({
        acoount:username,
        password:password
      }))
        .then((response)=>{
                let data = response.data
                let authority = data[0].authority
                if(data==='Bad credentials'){
                    this.$message.error('登陆失败')
                }
                //activation
                if(authority==='ROLE_TEACHER'){
                    this.$router.push('/mobile/teacher')
                }
                if(authority==='ROLE_STUDENT'){
                    this.$router.push('/mobile/student')
                }
            })
    }
  }
}
</script>
