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

      <!-- 老师列表 -->
      <el-container>
        <el-header style="text-align:left;background-color:rgb(238, 241, 246); font-size: 12px">
          <span style="color:rgb(0,0,0)">
            <i class="el-icon-search"></i>
          </span>
          <el-input
            style="width:30%"
            v-model="teacherSearchKey"
            placeholder="输入教工号/姓名"
            @keyup.enter.native="searchTeacher"
          ></el-input>
          <el-dropdown>
            <i
              class="el-icon-plus"
              style="margin-right: 15px"
              @click="$store.state.admin.showAddTeacher=true"
            ></i>
          </el-dropdown>
          <a style="color:rgb(0,0,0)" @click="$store.state.admin.showAddTeacher=true">新增教师</a>
        </el-header>
        <el-main>
          <el-table :data="teacherDataPagination">
            <el-table-column prop="account" label="教工号"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="phone" label="电话"></el-table-column>
            <el-table-column prop="email" label="电子邮箱"></el-table-column>
            <el-table-column prop="operation" label="操作">
              <template slot-scope="scope">
                <el-button @click="editTeacher(scope.row)" type="text" size="small">更改</el-button>
                <el-button @click="resetTeacher(scope.row)" type="text" size="small">重置密码</el-button>
                <el-button @click="deleteTeacher(scope.row)" type="text" size="small">删除</el-button>
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

      <modify-teacher-dialog @modifySuccess="flash"></modify-teacher-dialog>
      <add-teacher-dialog @addFail="addFail" @addSuccess="flash"></add-teacher-dialog>
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
import ModifyTeacherDialog from "./ModifyTeacherDialog";

export default {
  components: {
    "add-teacher-dialog": AddTeacherDialog,
    "modify-teacher-dialog": ModifyTeacherDialog
  },
  data() {
    return {
      teacherData: [
        {
          account: "123",
          name: "teacher1",
          email: "e-mail",
          phone: "j123312313"
        }
      ],
      pageSize: 20,
      teacherDataPagination: "",
      teacherSearchKey: "",
      currentPage: 1,
      currentTotal:1,
    };
  },
  created: function() {
    this.getAllTeacher();
  },
  methods: {
    addFail: function() {
      this.$message.error("insert error");
    },
    handleCurrentChange: function(val) {
      console.log("change page to " + val);
      this.currentPage = val;
      this.teacherDataPagination = this.teacherData.slice(
        (this.currentPage - 1) * 20,
        this.currentPage * 20 + 1
      );
    },
    handleStudent: function() {
      this.$router.push("AdminStudent");
    },
    handleTeacher: function() {
      console.log("teacher info");
    },
    editTeacher: function(teacher) {
      this.$store.state.admin.showModifyTeacher = true;
      this.$store.state.admin.currentTeacher = teacher;
      console.log(this.$store.state.admin.currentTeacher);
    },
    searchTeacher: function() {
      if (!this.teacherSearchKey) {
        this.getAllteacher();
        return;
      }
      if (!isNaN(this.teacherSearchKey)) {
        //搜教工号
        this.$axios
          .get(
            "/teacher/searchByAccount?teacherAccount=" + this.teacherSearchKey
          )
          .then(response => {
            this.teacherData = response.data;
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        //搜名字
        this.$axios
          .get("/teacher/searchteacher" + this.teacherSearchKey)
          .then(response => {
            if (response.data[0] === null) {
              this.$message.error("找不到该老师");
              return;
            }
            this.teacherData = response.data;
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    },
    flash: function() {
      this.getAllTeacher();
    },

    getAllTeacher: function() {
      this.$axios
        .get("/teacher")
        .then(response => {
          this.teacherData = response.data;
          this.currentTotal = this.teacherData.length;
          this.teacherDataPagination = this.teacherData.slice(
            (this.currentPage - 1) * 20,
            this.currentPage * 20 + 1
          );
        })
        .catch(function(error) {
          console.log(error);
        });
    },

    resetTeacher: function(teacher) {
      this.$prompt("请输入新密码", "修改老师密码", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(({ value }) => {
        let input = value;
        let message = this.$message;
        this.$axios
          .put("/teacher/" + teacher.teacherId + "/password", {
            password: input
          })
          .then(function(response) {
            if (response.data.message == "Success") {
              message({
                type: "success",
                message: "修改成功"
              });
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      });
    },

    deleteTeacher: function(teacher) {
      this.$confirm("确定删除该教师？", "删除账号", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        //确认删除
        this.$axios
          .delete("/teacher/" + teacher.teacherId)
          .then(response => {
            console.log(response);
            this.getAllTeacher();
          })
          .catch(function(error) {
            console.log(error);
          });
      });
    }
  }
};
</script>