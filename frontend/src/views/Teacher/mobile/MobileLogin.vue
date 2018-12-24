<template>
  <div class="login" style="background:#eee">
    <x-header title="讨论课管理系统登录" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
    </x-header>
  <group>
    <x-input v-model="account" style="margin-top:40px;background-color:#fff" placeholder="学号/教工号" ></x-input>
    <x-input type="password" v-model="password" style="margin-top:20px;background-color:#fff" placeholder="登录密码"></x-input>
  </group>
    <x-button @click="login" style="margin-top:100px">登陆</x-button>
    <button @click="forget" style="background:0;height:0px;border:0;margin-left:60%;margin-top:10px">忘记密码
    </button>
    <div style="color:green;margin-top:50%">
      初次登录默认密码为123456
    </div>
  </div>
</template>

<script>
import {XHeader,XButton,XInput} from 'vux'
import axios from 'axios'

export default {
  data () {
    return {
      account:'',
      password:'',
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
    login:function(){
      axios.post('127.0.0.1:8000/login',{
        acoount:username,
        password:password
      })
        .then(function(response){
          if(response.status==='200'){
            if(response.activationstatus==='isActivation'){
                this.$router.push('/activation')
            }
            if(response.identity==='teacher'){
                this.$router.push('/teacher')
            }
            if(response.identity==='student'){
                this.$router.push('/student')
            }
          }
        })
        .catch(function(error){
            console.log(error)
        })
    }
  }
}
</script>
