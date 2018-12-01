<template>
<el-container>
    <el-header>翻转课堂平台</el-header>
    <el-main style="width:20%;margin-top:10%;margin-left:40%">
        <el-button style="width:100%" type="text">翻转课堂登陆</el-button>
        <el-input v-model="account" placeholder="账户名"></el-input>
        <el-input type="password" v-model="password" placeholder="密码" ></el-input>
        <el-button style="width:100%" @click="Login(account,password)">登陆</el-button>
        <el-button style="width:100%" @click="toAdmin">test</el-button>
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
import axios from 'axios'
  export default {
    data() {
      
      return {
        account:'',
        password:''
      }
    },
    methods:{
        Login:function(account,password){
            if(!this.account){
                this.$message.error('请输入账户名')
                return
            }
            if(!this.password){
                this.$message.error('请输入密码')
            }
            axios.post('127.0.0.1:8000/api/login', {
                    params:{
                        account:this.account,
                        password:this.password
                    }
                }
            ).then(function(response){
                if(response.status==='200'){
                    if(response.identity==='teacher'){
                        this.$router.push('Teacher')
                    }
                    if(response.identity==='student'){
                        this.$router.push('Student')
                    }
                }else{
                    this.$message.error('账户名或密码错误')
                }
            }).catch(function(error){

            })
        },
        
        toAdmin:function(){
            this.$router.push('Admin')
        }
    }
  };
</script>