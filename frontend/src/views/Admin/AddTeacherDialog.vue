<template>
  <el-dialog :visible.sync="$store.state.admin.showAddTeacher" center>
      <el-form ref="form" :model="addTeacherForm" style="width:100%">
            <el-form-item label="教师姓名">
              <el-input v-model="addTeacherForm.name" placeholder="请输入老师姓名"></el-input>
            </el-form-item>
            <el-form-item label="教工号">
              <el-input v-model="addTeacherForm.account" placeholder="请输入教工号"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱">
              <el-input v-model="addTeacherForm.email" placeholder="请输入电子邮箱"></el-input>
            </el-form-item>
            <el-form-item label="初始密码">
              <el-input v-model="addTeacherForm.password" placeholder="请输入初始密码"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="postAddTeacher">提交</el-button>
            <el-button type="info" @click="$store.state.admin.showAddTeacher=false">取消</el-button>
          </span>
  </el-dialog>
</template>

<script>
export default {
    data(){
        return{
        addTeacherForm:{
          name:'',
          account:'',
          email:'',
          password:''
        },
        }
    },
    methods:{
        postAddTeacher:function(){
          if(this.addTeacherForm.name===''||
             this.addTeacherForm.account===''||
             this.addTeacherForm.email===''||
             this.addTeacherForm.password==='')
             this.$message.error('请完整填写老师信息')

          this.$axios.post('/admin/teacher',{
            name:this.addTeacherForm.name,
            account:this.addTeacherForm.account,
            email:this.addTeacherForm.email,
            password:this.addTeacherForm.password
          }).then((response)=>{
            if(response.data.message==='Success'){
              this.$store.state.admin.showAddTeacher=false
              this.$emit('addSuccess')
            }
            if(response.data.message==='Insert error'){
              this.$store.state.admim.showAddTeacher=false
              this.$emit('addFail')
            }
          })
            .catch(function(error){
              console.log(error)
            })
        },
    }
}
</script>