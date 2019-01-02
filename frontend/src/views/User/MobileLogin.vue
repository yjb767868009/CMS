<template>
  <div class="login" style="background:#eee">
    <x-header
      title="讨论课管理系统登录"
      style="height:60px;padding-top:12px"
      :left-options="{showBack:false}"
    ></x-header>

      <x-input v-model="account" style="margin-left:20px;margin-right:20px;margin-top:120px;background-color:#fff" placeholder="学号/教工号"></x-input>
      <x-input
        type="password"
        v-model="password"
        style="margin-top:20px;margin-left:20px;margin-right:20px;background-color:#fff"
        placeholder="登录密码"
      ></x-input>

    <x-button @click.native="login" style="margin-top:80px">登陆</x-button>
    <x-button @click.native="forget" style="margin-top:18px">忘记密码</x-button>
    
    <div style="color:green;margin-top:50%">初次登录默认密码为123456</div>

    
  <div v-transfer-dom>
      <x-dialog v-model="welcome" @click.native="welc" :dialog-style="{'max-width': '100%', width: '100%', height: '30%', 'background-color': 'transparent'}">
        <p style="color:#fff;text-align:center;" >
          <span style="font-size:30px;">欢迎</span>
        </p>
      </x-dialog>
    </div>

  </div>
</template>
<script>
import { XHeader, XButton, XInput,TransferDom,XDialog} from "vux";
import Qs from "qs";

export default {
  data() {
    return {
      account: "",
      password: "",
      welcome:false,
      data:'',
    };
  },
  mounted() {
    if (this.isMobile()) {
      console.log("mobile");
    } else {
      this.$router.replace("/login");
    }
  },
  directives:{
    TransferDom
  },
  components: {
    XHeader,
    XButton,
    XInput,XDialog
  },
  methods: {
    isMobile: function() {
      let flag = navigator.userAgent.match(
        /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
      );
      return flag;
    },
    forget: function() {
      this.$router.push("/mobile/findpw");
    },
    login: function() {
      this.$axios({
        method: "post",
        url:
          "/user/login?" +
          Qs.stringify({
            account: this.account,
            password: this.password
          })
      }).then((response) => {
        this.data = response.data;
        this.welcome=!this.welcome

      }).catch((error)=>{
        this.$message.error(error)
      });
    },
    welc:function(){
      if (this.data.role === "teacher") {
          console.log('teacher');
          this.$store.state.token = this.data.token;
          this.$store.state.userType = "teacher";
          if(this.data.active==="false"){//需要激活
            this.$router.push('/mobile/teacher/activation')
          }
          else{//不用激活
            this.$router.push('/mobile/teacher')
          }
        }

        if(this.data.role==='student'){
          console.log('student');
          this.$store.state.student.studentId=this.data.id
          this.$store.state.token=this.data.token;
          this.$store.state.userType='student';
          if(this.data.active==="false"){//需要激活
            this.$router.push('/mobile/student/activation')
          }
          else{//不用激活
            this.$router.push('/mobile/student/studentInfo')
          }
        }
    }
  }
};
</script>
