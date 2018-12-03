<template>
  <el-container style="height:100%;margin:0 auto">
      <el-header>Admin</el-header>

  <el-container style="height:100%;border: 1px solid #eee">
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
    
    <!-- 老师列表 -->
    <el-container v-if="showTeacher">
      <el-header style="text-align:left;background-color:rgb(238, 241, 246); font-size: 12px">
        <span style="color:rgb(0,0,0)"><i class="el-icon-search"></i></span>
        <el-input style="width:30%" v-model="teacherSearchKey" placeholder="输入教工号/姓名" @keyup.enter.native="searchTeacher">
        </el-input>
        <el-dropdown>
          <i class="el-icon-plus" style="margin-right: 15px" @click="$store.state.admin.showAddTeacher=true"></i>
        </el-dropdown>
        <a style="color:rgb(0,0,0)" @click="$store.state.admin.showAddTeacher=true">新增教师</a>
      </el-header>
      <el-main>
        <el-table :data="teacherData">
          <el-table-column prop="account" label="教工号">
          </el-table-column>
          <el-table-column prop="name" label="姓名">
          </el-table-column>
          <el-table-column prop="phone" label="电话">
          </el-table-column>
          <el-table-column prop="email" label="电子邮箱">
          </el-table-column>
          <el-table-column prop="operation" label="操作">
              <template slot-scope="scope">
                      <el-button @click="editTeacher(scope.row)" type="text" size="small">
                        更改
                      </el-button>
                      <el-button @click="resetTeacher(scope.row)" type="text" size="small">
                        重置密码
                      </el-button>
                      <el-button @click="deleteTeacher(scope.row)" type="text" size="small">
                        删除
                      </el-button>
              </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>

    <!-- 学生列表 -->
    <el-container  v-else-if="showStudent">
      <el-header style="text-align: left;background-color:rgb(238, 241, 246); font-size: 12px">
        <span style="color:rgb(0,0,0)"><i class="el-icon-search"></i></span>
        <el-input style="width:30%" v-model="studentSearchKey" placeholder="输入学号/姓名" @keyup.enter.native="searchStudent">
        </el-input>
      </el-header>
      <el-main>
        <el-table :data="studentData">
          <el-table-column prop="account" label="学号">
          </el-table-column>
          <el-table-column prop="name" label="姓名">
          </el-table-column>
          <el-table-column prop="phone" label="电话">
          </el-table-column>
          <el-table-column prop="email" label="电子邮箱">
          </el-table-column>
          <el-table-column prop="operation" label="操作">
              <template slot-scope="scope">
                      <el-button @click="editStudent(scope.row)" type="text" size="small">
                        更改
                      </el-button>
                      <el-button @click="resetStudent(scope.row)" type="text" size="small">
                        重置密码
                      </el-button>
                      <el-button @click="deleteStudent(scope.row)" type="text" size="small">
                        删除
                      </el-button>
              </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>

    
    </keep-alive>

      <modify-teacher-dialog></modify-teacher-dialog>
      <modify-student-dialog></modify-student-dialog>
      <add-teacher-dialog></add-teacher-dialog>
  
  </el-container>
      <el-footer style="background-color: rgb(10, 47, 88);color:rgb(255,255,255);height:100%;">
          <div class="container">
            <div class="footer-main"><p class="footer-main-title">CMS</p>
            <a href="https://git.code.tencent.com/xmu2018/software_221" target="_blank" class="footer-main-link">Github</a>
            <a href="mailto:99296485@qq.com" class="footer-main-link">Email</a>
            <a href="https://github.com/ElemeFE/element/blob/master/.github/CONTRIBUTING.en-US.md" target="_blank"
                    class="footer-main-link">License</a>
            </div>
            <span>&nbsp;</span>
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
        /* display: inline-block; */
    }

    .footer-main .footer-main-title {
        line-height: 1;
        font-size: 22px;
        margin: 0;
    }

    .footer-main .footer-main-link {
        /* display: inline-block; */
        margin: 12px 18px 0 0;
        line-height: 1;
        font-size: 12px;
        color: #768193;
    }
</style>

<script>
import AddTeacherDialog from './AddTeacherDialog'
import ModifyStudentDialog from './ModifyStudentDialog'
import ModifyTeacherDialog from './ModifyTeacherDialog'

  export default {
    components:{
      "add-teacher-dialog":AddTeacherDialog,
      "modify-student-dialog":ModifyStudentDialog,
      "modify-teacher-dialog":ModifyTeacherDialog,
    },
    data() {
      return {
        teacherData:[1,2,3,4],
        studentData:[1,2,3,4],

        navibar:'teacher',
        showTeacher:true,
        showStudent:false,

        teacherSearchKey:'',
        studentSearchKey:'',
      }
    },
    mounted:function(){
      this.getAllTeacher()
    },
    methods:{
        handleStudent:function(){
            this.getAllStudent()
            this.navibar='student'
            this.showTeacher=false
            this.showStudent=true
        },
        handleTeacher:function(){
            this.navibar='teacher'
            this.showStudent=false
            this.showTeacher=true
        },
        editStudent:function(student){
            this.$store.state.admin.showModifyStudent=true
            this.$store.state.admin.currentStudent=student
        },
        editTeacher:function(teacher){
            this.$store.state.admin.showModifyTeacher=true
            this.$store.state.admin.currentTeacher=teacher
        },
        
        searchTeacher:function(){
          if(!this.teacherSearchKey){
            //空
            return
          }
          if(!isNaN(this.teacherSearchKey)){
            //搜教工号
            this.$axios.get('/api/admin/teachers/searchByAccount?teacherAccount='+teacherSearchKey)
              .then((response)=>{
                this.teacherData=response.data
              })
              .catch(function(error){
                console.log(error)
              })
          }else{
            //搜名字
            this.$axios.get('/api/admin/teachers/searchByName?name='+teacherSearchKey)
              .then((response)=>{
                this.teacherData=response.data
              })
              .catch(function(error){
                console.log(error)
              })
          }
        },

        searchStudent:function(){
          if(!this.studentSearchKey){
            //空
            return
          }
          if(!isNaN(this.studentSearchKey)){
            //搜学号
            this.$axios.get('/api/admin/students/searchByAccount?studentAccount'+studentSearchKey)
              .then((response)=>{
                this.teacherData=response.data
              })
              .catch(function(error){
                console.log(error)
              })
          }else{
            //搜名字
            this.$axios.get('/api/admin/students/searchByName?name='+studentSearchKey)
              .then((response)=>{
                this.teacherData=response.data
              })
              .catch(function(error){
                console.log(error)
              })
          }
        },
        
        getAllStudent:function(){
          this.$axios.get('/api/admin/students')
            .then((response)=>{
              this.studentData=response.data
            }).catch(function(error){
              console.log(error)
            })
        },
        getAllTeacher:function(){
          this.$axios.get('/api/admin/teachers')
            .then((response)=>{
              this.teacherData=response.data
            }).catch(function(error){
              console.log(error)
            })
        },

        resetStudent:function(student){
          this.$prompt('请输入新密码', '修改学生密码', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
            }).then(({ input }) => {
            this.$axios.patch('/api/admin/student/'+student.studentId,{
            password:input
          })
            .then(function(response){
              //process on response
                  this.$message({
                type: 'success',
                message: '修改后的密码为: ' + input
              });
            })
            .catch(function(error){
              console.log(error)
            })
        })
        },

        resetTeacher:function(teacher){
          this.$prompt('请输入新密码', '修改老师密码', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ input }) => {
          this.$axios.patch('/api/admin/teacher/'+teacher.teacherId)
            .then(function(response){
              //process on response
                  this.$message({
                type: 'success',
                message: '修改后的密码为: ' + input
              });
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
            this.$axios.delete('/api/admin/student/'+student.studentId)
            .then((response)=>{
              console.log(response)
              this.getAllStudent()
            })
            .catch(function(error){
              console.log(error)
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
            this.$axios.delete('/api/admin/teacher/'+teacher.teacherId)
            .then((response)=>{
              console.log(response)
              this.getAllTeacher()
            })
            .catch(function(error){
              console.log(error)
            })
          })
        },
    }
  };
</script>