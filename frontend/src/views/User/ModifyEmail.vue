<template>
  <div class="activation" style="background:#eee">
    <x-header title="账户邮箱" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
    </x-header>
    <div style="padding-left:10px;margin-top:10px;height:10px">当前邮箱：{{this.$store.state.email}}</div>
  <group>
    <x-input v-model="email" style="margin-top:20px;background-color:#fff" title="e-mail:" placeholder="填写修改邮箱"></x-input>  

  </group>
      <div style="text-align:left;margin-top:100px;font-size:10px">邮箱格式如：useremail@host.domainnames</div>
    <x-button type="primary" @click.native="post" style="margin-top:10px">确认提交</x-button>
  </div>
</template>

<script>
import {XHeader,XButton,XInput} from 'vux'
import axios from 'axios'

export default {
  data () {
    return {
      email:'',
      password:'',
      identifying:''
    }
  },
  mounted:function(){

  },
  components:{
    XHeader,
    XButton,
    XInput,
  },
  methods:{
    post:function(){
      this.$axios.put('/user/email',{
        email:this.email
      }).then((response)=>{
        if(this.$store.state.userType==='teacher'){
          this.$router.push('/mobile/teacher')
        }else if(this.$store.state.userType==='student'){
          this.$router.push('/mobile/student')
        }
      })
    }
  }
}
</script>
