<template>
  <div class="activation" style="background:#eee">
    <x-header title="密码设置" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
    </x-header>
  <group>
    <el-input type="password" v-model="password1" style="margin-top:40px;" placeholder="输入密码"></el-input>
    <el-input type="password" v-model="password2" style="margin-top:40px;" placeholder="确认密码"></el-input>
  </group>
    <button @click="post">确认提交</button>
  </div>
</template>

<style>
button {
    color: #444444;
    background: #F3F3F3;
    border: 1px #DADADA solid;
    padding: 5px 10px;
    border-radius: 2px;
    font-weight: bold;
    font-size: 15pt;
    outline: none;
    margin-top:100%;
    height:50px;
    width:100%
}

</style>


<script>
import {XHeader,XButton,XInput,TransferDom,Popup} from 'vux'
import axios from 'axios'
import Toast from 'mint-ui'

export default {
  directives:{
    TransferDom
  },
  data () {
    return {
      password1:'',
      password2:'',
    }
  },
  components:{
    XHeader,
    XButton,Popup
  },
  methods:{
    post:function(){
      if(this.password1!==this.password2){
        console.log(this.password1)
        this.$message.error('两次密码不一致')
      }else{
        axios.put('/teacher/active',{
          password:this.password1
        }).then((response)=>{
          if(response.status='200'){//成功
            this.$router.push('/mobile/teacher/seminars')
          }else{
            return
          }
        })
      }
    }
  }
}
</script>
