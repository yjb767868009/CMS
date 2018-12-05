<template>
<el-container>
    <el-header>翻转课堂平台</el-header>
    <el-main style="width:20%;margin-top:10%;margin-left:40%">
        <el-button style="width:100%" type="text">翻转课堂登陆</el-button>
        <el-input v-model="account" placeholder="账户名"></el-input>
        <el-input type="password" v-model="password" placeholder="密码" ></el-input>
        <el-button style="width:100%" @click="login(account,password)">登陆</el-button>
    </el-main>
</el-container>

</template>

<style>
  .el-header {
    background-color: rgb(10, 47, 88);
    color: rgb(255, 255, 255);
    line-height: 60px;
    font-family: PingFang SC    
  }
  
  .el-aside {
    color: #333;
  }
</style>

<script>
import Qs from 'qs'
  export default {
    data() {
      
      return {
        account:'',
        password:''
      }
    },
    mounted(){
        if(this.isMobile()){
            console.log('userAgent:',navigator.userAgent)
            this.$router.replace('/mobileLogin')
        }else{
            console.log('pc')
        }
    },
    methods:{
        isMobile:function(){
            let flag=
                navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
            return flag
        },
        login:function(account,password){
            if(!this.account){
                this.$message.error('请输入账户名')
                return
            }
            if(!this.password){
                this.$message.error('请输入密码')
            }

            this.$axios({
                method:'post',
                url:'/api/user/login?'+Qs.stringify({
                    account:this.account,
                    password:this.password
                })
            }//我们用配置axios的方式做请求,axios的详细配置见
            //src/config/axios.js

            ).then((response)=>{
                let data = response.data
                let authority = data[0].authority
                if(data==='Bad credentials'){
                    this.$message.error('登陆失败')
                }
                //activation
                if(authority==='ROLE_TEACHER'){
                    this.$router.push('/pc/teacher')
                }
                if(authority==='ROLE_STUDENT'){
                    this.$router.push('/pc/student')
                }
            })
        },
    }
  };
</script>