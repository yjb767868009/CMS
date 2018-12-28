<template>
  <el-dialog :visible.sync="$store.state.admin.showModifyStudent" center title="修改学生信息">
      <el-form ref="form" :model="modifyStudentForm" style="width:100%">
            <el-form-item label="学生姓名">
              <el-input v-model="modifyStudentForm.name" placeholder="请输入学生姓名"></el-input>
            </el-form-item>
            <el-form-item label="学号">
              <el-input v-model="modifyStudentForm.account" placeholder="请输入学号"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱">
              <el-input v-model="modifyStudentForm.email" placeholder="请输入电子邮箱"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="postModifyStudent">提交</el-button>
            <el-button type="info" @click="$store.state.admin.showModifyStudent=false">取消</el-button>
          </span>
  </el-dialog>
</template>

<script>
import Qs from 'qs'
export default {
    data(){
        return{
        modifyStudentForm:{
          name:'',
          account:'',
          email:'',
        },
        }
    },
    methods:{
        postModifyStudent:function(){
          this.$axios.put('/admin/student/'+this.$store.state.admin.currentStudent.studentId+'/information',{
            name:this.modifyStudentForm.name,
            account:this.modifyStudentForm.account,
            email:this.modifyStudentForm.email,
          }).then((response)=>{
            if(response.data.message==='Success'){
              this.$store.state.admin.showModifyStudent=false
              this.$emit('modifySuccess')
            }
          })
            .catch(function(error){
              console.log(error)
              this.$store.state.admin.showModifyStudent=false
            })
        },
    }
}
</script>