<template>
<el-container>
    <el-header>翻转课堂管理平台</el-header>
    <el-main style="width:20%;margin-top:10%;margin-left:40%">
        <el-button style="width:100%" type="text">翻转课堂管理员登陆</el-button>
        <el-input v-model="account" placeholder="账户名"></el-input>
        <el-input type="password" v-model="password" placeholder="密码" ></el-input>
        <el-button style="width:100%" @click="adminLogin(account,password)">登陆</el-button>
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
import md5 from 'md5'
  export default {
    data() {
      
      return {
        account:'',
        password:''
      }
    },
    methods:{
        adminLogin:function(account,password){
            if(!this.account){
                this.$message.error('请输入账户名')
                return
            }
            if(!this.password){
                this.$message.error('请输入密码')
            }

            this.$axios.post(
                    '/admin/login?'+Qs.stringify({
                    account:this.account,
                    password:this.password
                })
            ).then((response)=>{
				console.log(response)
                let data = response.data

                if(data.role=='admin'){
                    console.log('admin')
                    this.$store.state.token=data.token
                    this.$store.state.userType='admin'
                    this.$router.push('AdminTeacher')
                }
                else{
                    this.$message.error('登陆失败')
                    return
                }
            }).catch((error)=>{
                console.log(error)
                this.$message.error('登陆失败')
            })
			

                
        },
        
    }
  };
</script>
