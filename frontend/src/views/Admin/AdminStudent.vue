<template>
  <el-container style="height:100%;margin:0 auto">
    <el-header>Admin</el-header>

    <el-container style="height:100%;border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(255,255,255);height:100%">
        <el-menu :default-openeds="['1']">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>信息管理
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="handleTeacher">教师信息管理</el-menu-item>
              <el-menu-item index="1-2" @click="handleStudent">学生信息管理</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <!-- 学生列表 -->
      <el-container>
        <el-header style="text-align: left;background-color:rgb(238, 241, 246); font-size: 12px">
          <span style="color:rgb(0,0,0)">
            <i class="el-icon-search"></i>
          </span>
          <el-input
            style="width:30%"
            v-model="studentSearchKey"
            placeholder="输入学号/姓名"
            @keyup.enter.native="searchStudent"
          ></el-input>
        </el-header>

        <el-main>
          <el-table :data="studentDataPagination">
            <el-table-column prop="account" label="学号"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="phone" label="电话"></el-table-column>
            <el-table-column prop="email" label="电子邮箱"></el-table-column>
            <el-table-column prop="operation" label="操作">
              <template slot-scope="scope">
                <el-button @click="editStudent(scope.row)" type="text" size="small">更改</el-button>
                <el-button @click="resetStudent(scope.row)" type="text" size="small">重置密码</el-button>
                <el-button @click="deleteStudent(scope.row)" type="text" size="small">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            background
            layout="->,prev, pager, next"
            :page-size="pageSize"
            :total="currentTotal"
            @current-change="handleCurrentChange"
          ></el-pagination>
        </el-main>
      </el-container>

      <modify-student-dialog @modifySuccess="flash"></modify-student-dialog>
      <add-teacher-dialog @addSuccess="flash"></add-teacher-dialog>
    </el-container>
    <el-footer style="background-color: rgb(10, 47, 88);color:rgb(255,255,255);height:100%;">
      <div class="container">
        <div class="footer-main">
          <p class="footer-main-title">CMS</p>
          <a
            href="https://git.code.tencent.com/xmu2018/software_221"
            target="_blank"
            class="footer-main-link"
          >Github</a>
          <a href="mailto:99296485@qq.com" class="footer-main-link">Email</a>
          <a
            href="https://github.com/ElemeFE/element/blob/master/.github/CONTRIBUTING.en-US.md"
            target="_blank"
            class="footer-main-link"
          >License</a>
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
  font-family: PingFang SC;
}

.el-aside {
  color: #333;
  font-family: PingFang SC;
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
import AddTeacherDialog from "./AddTeacherDialog";
import ModifyStudentDialog from "./ModifyStudentDialog";
import md5 from "md5";

export default {
  components: {
    "add-teacher-dialog": AddTeacherDialog,
    "modify-student-dialog": ModifyStudentDialog
  },
  data() {
    return {
      studentData: [
        {
          account: "123",
          name: "student1",
          email: "e-mail",
          phone: "j123312313"
        }
      ],
      pageSize: 20,
      studentDataPagination: "",
      studentSearchKey: "",
      currentPage: 1,
      currentTotal: 1,
    };
  },
  mounted: function() {
    this.getAllStudent();
  },
  methods: {
    handleCurrentChange: function(val) {
      console.log("change page to " + val);
      this.currentPage = val;
      this.studentDataPagination = this.studentData.slice(
        (currentPage - 1) * 20,
        currentPage * 20 + 1
      );
    },
    handleStudent: function() {
      console.log("student info");
    },
    handleTeacher: function() {
      this.$router.push("AdminTeacher");
    },
    editStudent: function(student) {
      this.$store.state.admin.showModifyStudent = true;
      this.$store.state.admin.currentStudent = student;
      console.log(this.$store.state.admin.currentStudent);
    },

    searchStudent: function() {
      if (!this.studentSearchKey) {
        this.getAllStudent();
        return;
      }
      if (!isNaN(this.studentSearchKey)) {
        //搜学号
        this.$axios
          .get("/admin/student/searchByAccount/" + this.studentSearchKey)
          .then(response => {
            this.studentData = response.data;
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        //搜名字
        this.$axios
          .get("/admin/student/searchByName/" + this.studentSearchKey)
          .then(response => {
            if (response.data[0] === null) {
              this.$message.error("找不到该学生");
              return;
            }
            this.studentData = response.data;
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    },
    flash: function() {
      this.getAllStudent();
    },

    getAllStudent: function() {
      this.$axios
        .get("/admin/student")
        .then(response => {
          this.studentData = response.data;
          this.currentTotal = this.studentData.length;
          this.studentDataPagination = this.studentData.slice(
            (this.currentPage - 1) * 20,
            this.currentPage * 20 + 1
          );
        })
        .catch(function(error) {
          console.log(error);
        });
    },

    resetStudent: function(student) {
      this.$prompt("请输入新密码", "修改学生密码", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(({ value }) => {
        let input = value;
        this.$axios
          .put("/admin/student/" + student.studentId + "/password", {
            password: input
          })
          .then(response => {
            if (response.data.message == "Success") {
              this.$message({
                type: "success",
                message: "修改成功"
              });
            } else {
              this.$message({
                type: "error",
                message: "修改失败"
              });
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      });
    },

    deleteStudent: function(student) {
      this.$confirm("确定删除该学生？", "删除账号", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        //确认删除
        this.$axios
          .delete("/admin/student/" + student.studentId)
          .then(response => {
            console.log(response);
            this.getAllStudent();
          })
          .catch(function(error) {
            console.log(error);
          });
      });
    }
  }
};
</script>