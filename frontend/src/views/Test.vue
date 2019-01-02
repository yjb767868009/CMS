<template>
  <div class="course">
    <el-button @click="newSubmitForm()">test</el-button>
    <el-upload
  :action="doUpload"
  :before-upload="beforeUpload"
  ref="newupload"
  multiple
  :auto-upload="false">
  <em>点击上传</em>
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
            alert('成功');
    })
},
newSubmitForm(){//确定上传
    this.$refs.newupload.submit();
}
    },
    data () {
        return {
            s:'',
            students:'',
            stopic:"",
            pp2:"",
            option1: '1',
            options1: [['1', '2', '3','4']],
            startDate: '2018-1-1',
            endDate: '2018-12-31',
            option2: '6',
            options2: [['1', '2', '3','4','5','6']],
            option3: '无',
            options3: [['无', '1' ,'2', '3','4','5','6']],
            
        }
    },
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>