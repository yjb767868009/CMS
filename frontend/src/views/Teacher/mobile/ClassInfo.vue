<template>
  <div class="course" style="background:#eee">

    <!-- 班级信息 -->
    <template v-if="!addingKlass">
      <x-header title="班级信息" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}"
        @on-click-more="show=!show">
      </x-header>

      <template v-for="klass in klasses" style="text-decoration:none">
        <form-preview :key="klass.klassId" :header-label="klass.grade+'-'+klass.klassSerial" :body-items="[{
        label:'讨论课时间',
        value:klass.klassTime
        },
        {
        label:'讨论课地点',
        value:klass.klassPlace
        }]"
          :footer-buttons="footerButtons" :name="klass.klassId">
        </form-preview>
      </template>

      <br />
      <x-button type="primary" @click.native="newKlass">新建班级</x-button>

      <div v-transfer-dom>
        <confirm v-model="Delete"
        title="提示"
        theme="android"
        @on-confirm="sure">
        <p style="text-align:center;">确定删除该班级吗</p>
        </confirm>
        <popup v-model="show" height="23%">
          <div>
            <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;"
                width="30px" height="30px" />
              <div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div>
            </cell>
            <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;"
                width="30px" height="30px" />
              <div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div>
            </cell>
            <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;"
                width="30px" height="30px" />
              <div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div>
            </cell>
          </div>
        </popup>
      </div>

      <div v-transfer-dom>
        <x-dialog v-model="showUpload" hide-on-blur>
          <div>
          <el-upload 
          :action="'http://119.23.49.112:8000/class/' + this.currentKlassId + '/classfile'"
          :headers="this.headers"
          ref="upload"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :on-exceed="handleExceed"
          :auto-upload="false"
          :limit="1"
          :before-upload="beforeUpload"
          :file-list="fileList">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="newSubmitForm">上传到服务器</el-button>
          </el-upload>
          </div>
          
        </x-dialog>
      </div>

    </template>

    <!-- 新建班级 -->
    <template v-if="addingKlass">
      <x-header title="新建班级" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: false}">
      </x-header>
      <group>
        <x-input title="新班级年级" text-align="right" v-model="newKlassGrade"></x-input>
        <x-input title="班级号" text-align="right" v-model="newKlassSerial"></x-input>
        <x-input title="上课地点" text-align="right" v-model="newKlassPlace"></x-input>
        <x-input title="上课时间" text-align="right" v-model="newKlassTime"></x-input>
      </group>

      <x-button type="primary" @click.native="postNewKlass">保存</x-button>
    </template>

  </div>
</template>

<script>
  import {
    XHeader,
    XButton,
    Divider,
    TransferDom,
    Popup,
    Cell,
    DatetimeRange,
    XInput,
    Group,
    FormPreview,
    Confirm,XDialog 
  } from 'vux'
  export default {
    directives: {
      TransferDom
    },
    components: {
      XHeader,
      XButton,
      Divider,
      Popup,
      Cell,
      XInput,
      Group,
      FormPreview,
      DatetimeRange,
      Confirm,XDialog 
    },
    data() {
      return {
        addingKlass: false,
        fileList: [],
        show: false,
        Delete:false,
        deleteKlass:'',
        klasses: [{
          klassId: 1,
          grade: '2016',
          klassSerial: '1',
          klassTime: '1t',
          klassPlace: '1p'
        }, {
          klassId: 2,
          grade: '2016',
          klassSerial: '2',
          klassTime: '2t',
          klassPlace: '2p'
        }],
        footerButtons: [
          // {
          // style: 'primary',
          // text: '提交',
          // onButtonClick: (name) => {
          //   console.log('klassId:', name)
          //   // this.$axios.put('/class/'+name+'/classfile',this.filename)
          //   // .then((response)=>{
          //   //   console.log(response)
          //   //   this.$message('提交成功')
          //   // })
          // }}, 
          {
          style: "default",
          text: '上传学生名单',
          onButtonClick: (name) => {
            console.log('klassId:', name)
            this.showUpload=true
            this.currentKlassId=name
          }
        },
          {
          style: "default",
          text: '删除班级',
          onButtonClick: (name) => {
            this.Delete=!this.Delete
            this.deleteKlass=name
            console.log('klassId:', name)
          }
        }],
        showUpload:false,
        currentKlassId:''
      }
    },
    computed:{
      headers(){
        return{
          'Authorization':'Bearer '+this.$store.state.token
        }
      }
    },
    mounted:function(){
        this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/class').then((response)=>{
            this.klasses=response.data
        })
    },
    methods: {
      sure(){
        console.log(this.deleteKlass,'delete')
        this.$axios.delete('/class/'+this.deleteKlass)
        .then((res)=>{
          console.log(res)
          this.deleteKlass=''
          window.location.reload()
        })
      },
      Undo() {
        this.$router.push('/mobile/teacher/notify')
      },
      TeacherInfo() {
        this.$router.push('/mobile/teacher')
      },
      GoSeminar() {
        this.$router.push('/mobile/teacher/seminars')
      },
      newKlass() {
        this.newKlassGrade = '',
          this.newKlassSerial = '',
          this.newKlassTime = '',
          this.newKlassPlace = ''
        this.addingKlass = true
      },
      postNewKlass() {
        //this.$axios.post('')
        //this.$axios.get('')
        if(this.newKlassGrade!=''&&this.newKlassSerial!=''&&this.newKlassPlace!=''&&this.newKlassTime!=''){
        this.$axios.post('/course/'+this.$store.state.teacher.currentCourse.courseId+'/class',{
          grade:this.newKlassGrade,
          klassSerial:this.newKlassSerial,
          klassTime:this.newKlassTime,
          klassPlace:this.newKlassPlace
        }).then((res)=>{
          console.log(res)
          this.addingKlass=false
          this.$router.go(0)
        })
        }else{
          this.$message('请完善信息')
        }
      },
      onChange(val){
          console.log(val)
          this.newKlassTime=val[0]
      }
    //   beforeUpload(file){
    // let fd = new FormData();
    // fd.append('file',file);//传文件
    // this.$axios.post('/class/'+this.currentKlassId+'/classfile',fd).then(function(res){
    // this.$message('上传成功')
    // })
    //     }
        ,handleRemove(file, fileList) {
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
    this.showUpload=true
    this.$message('上传成功')
    }
    }
  }

</script>

<style>
  .box {
    padding: 0 15px;
  }

</style>
