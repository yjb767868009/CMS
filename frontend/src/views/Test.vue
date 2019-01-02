<template>
  <div class="course">
    <el-upload
  class="upload-demo"
  ref="upload"
  action="https://jsonplaceholder.typicode.com/posts/"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :on-exceed="handleExceed"
  :file-list="fileList"
  :limit="1"
  :auto-upload="false">
  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
  <el-button style="margin-left: 10px;" size="small" type="success" @click="newSubmitForm">上传到服务器</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload>
  </div>
</template>

<script>
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,XSwitch} from 'vux'
export default {
    components:{
        XHeader,
        XButton,
        Divider,
        Group,
        Datetime,
        XInput,
        PopupPicker,
        XSwitch
    },
    methods: {
        beforeUpload(file){
    let fd = new FormData();
    fd.append('file',file);//传文件
    this.$axios.post('/api/up/file',fd).then(function(res){
           
    this.$message('上传成功')
    })
        },handleRemove(file, fileList) {
        console.log(file, fileList);
      },
        handlePreview(file) {
        console.log(file);
      },
        handleExceed(files, fileList) {
        this.$message.warning(`只能选择一个文件`);
      },
        newSubmitForm(){//确定上传
    this.$refs.upload.submit();
    }
    },
    data () {
        return {
            s:'',
            fileList: [],
            students:'',
        }
    },
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>