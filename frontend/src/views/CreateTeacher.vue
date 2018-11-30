<template>
<el-container style="height:100%">
    <el-header>Admin</el-header>

<el-container style="height:600px;border: 1px solid #eee">
  <el-aside width="200px" style="background-color: rgb(255,255,255);height:100%">
    <el-menu :default-openeds="['1']">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>信息管理</template>
        <el-menu-item-group>
          <el-menu-item index="1-1" @click="handleTeacher">教师信息管理</el-menu-item>
          <el-menu-item index="1-2" @click="handleStudent">学生信息管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      
    </el-menu>
  </el-aside>
  

  
</el-container>
    <el-footer style="background-color: rgb(10, 47, 88);color:rgb(255,255,255);height:100%;">
        <div class="container">
          <div class="footer-main"><p class="footer-main-title">{{ footerMessage }}</p><a
                  href="https://git.code.tencent.com/xmu2018/software_221" target="_blank" class="footer-main-link">Github</a><a
                  href="mailto:99296485@qq.com" class="footer-main-link">Email</a><a
                  href="https://github.com/ElemeFE/element/blob/master/.github/CONTRIBUTING.en-US.md" target="_blank"
                  class="footer-main-link">License</a>
          </div>
        </div>
    </el-footer>
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
    font-family: PingFang SC
  }

  .footer-main {
        font-size: 0;
        padding-top: 40px;
        display: inline-block;
    }

    .footer-main .footer-main-title {
        line-height: 1;
        font-size: 22px;
        margin: 0;
    }

    .footer-main .footer-main-link {
        display: inline-block;
        margin: 12px 18px 0 0;
        line-height: 1;
        font-size: 12px;
        color: #768193;
    }
</style>

<script>
import axios from 'axios'

  export default {
    data() {
      
    },
    method:[{
        handleTeacher:function(){
            this.$router.push('/admin')
        }
    }]
  };
</script>