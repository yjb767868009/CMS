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
  
  <keep-alive>

    <el-container v-if="show_teacher">
      <el-header style="text-align: right;background-color:rgb(238, 241, 246); font-size: 12px">
        <span style="color:rgb(0,0,0)"><i class="el-icon-search"></i></span>
        <el-input style="width:30%" v-model="teacherSearchKey" placeholder="输入教工号/姓名" @keyup.enter.native="searchTeacher">
        </el-input>
        <el-dropdown>
          <i class="el-icon-plus" style="margin-right: 15px" @click="addTeacher"></i>
        </el-dropdown>
        <span style="color:rgb(0,0,0)">新增教师</span>
      </el-header>
      <el-main>
        <el-table :data="teacherData">
          <el-table-column prop="account" label="教工号">
          </el-table-column>
          <el-table-column prop="name" label="姓名">
          </el-table-column>
          <!-- <el-table-column prop="sex" label="性别">
          </el-table-column> -->
          <el-table-column prop="phone" label="电话">
          </el-table-column>
          <el-table-column prop="email" label="电子邮箱">
          </el-table-column>
          <el-table-column prop="operation" label="操作">
              <template scope="scope">
                      <el-button @click="editTeacher(scope.row)" type="text" size="small">更改</el-button>
                      <el-button @click="resetTeacher(scope.row)" type="text" size="small">重置密码</el-button>
                      <el-button @click="deleteTeacher(scope.row)" type="text" size="small">删除</el-button>
              </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>

    <el-container v-else>
      <el-header style="text-align: right;background-color:rgb(238, 241, 246); font-size: 12px">
        <span style="color:rgb(0,0,0)"><i class="el-icon-search"></i></span>
        <el-input style="width:30%" v-model="studentSearchKey" placeholder="输入学号/姓名" @keyup.enter.native="searchStudent">
        </el-input>
          <!-- <el-dropdown>
          <i class="el-icon-plus" style="margin-right: 15px" @click="addStudent"></i>
        </el-dropdown>
        <span style="color:rgb(0,0,0)">新增学生</span> -->
      </el-header>
      <el-main>
        <el-table :data="studentData">
          <el-table-column prop="account" label="学号">
          </el-table-column>
          <el-table-column prop="name" label="姓名">
          </el-table-column>
          <!-- <el-table-column prop="sex" label="性别">
          </el-table-column> -->
          <el-table-column prop="phone" label="电话">
          </el-table-column>
          <el-table-column prop="email" label="电子邮箱">
          </el-table-column>
          <el-table-column prop="operation" label="操作">
              <template slot-scope="scope">
                      <el-button @click="editStudent(scope.row)" type="text" size="small">更改</el-button>
                      <el-button @click="resetStudent(scope.row)" type="text" size="small">重置密码</el-button>
                      <el-button @click="deleteStudent(scope.row)" type="text" size="small">删除</el-button>
              </template>
          </el-table-column>
        </el-table>
        <db-modal :dialogFormVisible="dialogFormVisible" :form="addTeacherForm" v-on:canclemodal="dialogVisible"></db-modal>
      </el-main>
    </el-container>

  </keep-alive>
  
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
import DbModal from '@/components/DbModal'
  export default {
    data() {
      const teacher = {
        id:'tid',
        account:'taccount',
        password:'tpwd',
        name: 'tname',
        phone:'tphone',
        email:'example@email.com',
        messageInterval:1,
        activation:true
      };
      const student = {
        id:'sid',
        account:'saccount',
        password:'spwd',
        name: 'sname',
        phone:'sphone',
        email:'example@stu.com',
        messageInterval:1,
        activation:true
      };
      return {
        teacherData: Array(20).fill(teacher),
        studentData: Array(20).fill(student),
        show_teacher:true,
        footerMessage:'CMS',
        teacherSearchKey:'',
        studentSearchKey:'',
        addTeacherForm:''
      }
    },
    mounted:function(){
      this.getAllStudent()
      this.getAllTeacher()
    },
    methods:{
        handleStudent:function(){
            this.show_teacher=false
        },
        handleTeacher:function(){
            this.show_teacher=true
        },

        dialogVisible: function () {
            this.dialogFormVisible = false;
        },


        searchTeacher:function(){
          if(!this.teacherSearchKey){
            //空
            return
          }
          if(!isNaN(this.teacherSearchKey)){
            //搜教工号
          }else{
            //搜名字
          }
        },
        searchStudent:function(){
          if(!this.studentSearchKey){
            //空
            return
          }
          if(!isNaN(this.studentSearchKey)){
            //搜教工号
          }else{
            //搜名字
          }
        },
        editStudent:function(student){
            this.$prompt('')
        },
        editTeacher:function(teacher){
            console.log(teacher.id)
        },
        addTeacher:function(){
            this.dialogFormVisible = true;
        },
        getAllStudent:function(){
          axios.get('127.0.0.1:8000/api/admin/students')
            .then(function(response){
              this.studentData=response.students
            }).catch(function(error){
              console.log(error)
            })
        },
        getAllTeacher:function(){
          axios.get('127.0.0.1:8000/api/admin/teachers')
            .then(function(response){
              this.teacherData=response.teachers
            }).catch(function(error){
              console.log(error)
            })
        },
        resetStudent:function(student){
          this.$confirm('确定重置该学生密码？','重置密码',{
            confirmButtonText:'确定',
            cancelButtonText:'取消',
            type:'warning'
          }).then(()=>{
            //确认重置
            axios.patch('127.0.0.1:8000/api/admin/students/'+student.id,{
              params:{
                password:'123456'
              }
            })
            .catch(function(error){
              console.log(error)
            })
          })
        },
        resetTeacher:function(teacher){
          this.$confirm('确定重置该学生密码？','重置密码',{
            confirmButtonText:'确定',
            cancelButtonText:'取消',
            type:'warning'
          }).then(()=>{
            //确认重置
            axios.patch('127.0.0.1:8000/api/admin/teachers/'+teacher.id,{
              params:{
                password:'123456'
              }
            })
            .catch(function(error){
              console.log(error)
            })
          })
        },
        deleteStudent:function(student){
          this.$confirm('确定删除该学生？','删除账号',{
            confirmButtonText:'确定',
            cancelButtonText:'取消',
            type:'warning'
          }).then(()=>{
            //确认删除
            axios.delete('127.0.0.1:8000/api/admin/students/'+student.id)
            .catch(function(error){
              console.log(error)
            })
          }).catch(()=>{
            this.$message({
              type:'info',
              message:'取消删除'
            })
          })
        },
        deleteTeacher:function(teacher){
          this.$confirm('确定删除该教师？','删除账号',{
            confirmButtonText:'确定',
            cancelButtonText:'取消',
            type:'warning'
          }).then(()=>{
            //确认删除
            axios.delete('127.0.0.1:8000/api/admin/teachers/'+teacher.id)
            .catch(function(error){
              console.log(error)
            })
          }).catch(()=>{
            this.$message({
              type:'info',
              message:'取消删除'
            })
          })
        }
    }
  };
</script>