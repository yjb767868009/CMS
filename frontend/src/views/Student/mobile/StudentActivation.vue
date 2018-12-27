<template>
  <div class="activation" style="background:#eee;height:700px;">
    <x-header title="密码设置" style="height:60px;padding-top:12px"  :left-options="{showBack:false}">
    </x-header>
    <group>
      <el-input
        type="password"
        v-model="password1"
        style="margin-top:40px;background-color:#fff"
        placeholder="输入密码"
      />
      <el-input
        type="password"
        v-model="password2"
        style="margin-top:20px;background-color:#fff"
        placeholder="确认密码"
      />
      <el-input v-model="email" style="margin-top:20px;background-color:#fff" placeholder="填写邮箱"/>
    </group>
    <div style="text-align:left;margin-top:150px;font-size:10px">可包含数字、字母、下划线，长度不少于六位</div>
    <flexbox>
      <flexbox-item>
    <x-button @click.native="post" type="primary" style="margin-top:5%">确认提交</x-button>
      </flexbox-item>
    </flexbox>
  </div>
</template>

<script>
import { XHeader, XButton,Flexbox,FlexboxItem } from "vux";
import axios from "axios";

export default {
  data() {
    return {
      password1: "",
      password2: "",
      email:"",
      identifying,
    };
  },
  components: {
    XHeader,
    XButton,Flexbox,FlexboxItem
  },
  methods: {
    post:function(){
      console.log(this.password1,this.password2,this.email)
      if(this.password1!==this.password2){
        this.$message.error('两次密码不同')
      }else{
        this.$axios.put('/student/active',{
          email:this.email,
          password:this.password1,
        }).then((response)=>{
          if(response.data.message==='Success'){//激活成功
            this.$router.push('/mobile/student/studentInfo')
          }
          else{
            this.$message.error('激活失败')
          }
        })
      }
    },
  }
};
</script>
