<template>
  <el-dialog :visible.sync="$store.state.admin.showModifyTeacher" center title="修改老师信息">
      <el-form ref="form" :model="modifyTeacherForm" style="width:100%">
            <el-form-item label="教师姓名">
              <el-input v-model="modifyTeacherForm.name" placeholder="请输入老师姓名"></el-input>
            </el-form-item>
            <el-form-item label="教工号">
              <el-input v-model="modifyTeacherForm.account" placeholder="请输入教工号"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱">
              <el-input v-model="modifyTeacherForm.email" placeholder="请输入电子邮箱"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="postModifyTeacher">提交</el-button>
            <el-button type="info" @click="$store.state.admin.showModifyTeacher=false">取消</el-button>
          </span>
  </el-dialog>
</template>

<script>
export default {
    data(){
        return{
        modifyTeacherForm:{
          name:'',
          account:'',
          email:'',
        },
        }
    },
    methods:{
        postModifyTeacher:function(){
          this.$axios.patch('/api/admin/teacher/'+this.$store.state.admin.currentTeacher.teacherId,{
            name:this.modifyTeacherForm.name,
            account:this.modifyTeacherForm.account,
            email:this.modifyTeacherForm.email,
          }).then((response)=>{
            console.log(response)
          })
            .catch(function(error){
              console.log(error)
            })
        },
    }
}
</script>