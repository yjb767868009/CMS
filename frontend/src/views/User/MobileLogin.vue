<template>
  <div class="login" style="background:#eee">
    <x-header
      title="讨论课管理系统登录"
      style="height:60px;padding-top:12px"
      :left-options="{showBack:false}"
    ></x-header>
    <group>
      <x-input v-model="account" style="margin-top:40px;background-color:#fff" placeholder="学号/教工号"></x-input>
      <x-input
        type="password"
        v-model="password"
        style="margin-top:20px;background-color:#fff"
        placeholder="登录密码"
      ></x-input>
    </group>
    <x-button @click.native="login" style="margin-top:100px">登陆</x-button>
    <button
      @click.native="forget"
      style="background:0;height:0px;border:0;margin-left:80%;margin-top:10px"
    >忘记密码</button>
    <div style="color:green;margin-top:50%">初次登录默认密码为123456</div>
  </div>
</template>

<script>
import { XHeader, XButton, XInput } from "vux";
import Qs from "qs";

export default {
  data() {
    return {
      account: "",
      password: ""
    };
  },
  mounted() {
    if (this.isMobile()) {
      console.log("mobile");
    } else {
      this.$router.replace("/login");
    }
  },
  components: {
    XHeader,
    XButton,
    XInput
  },
  methods: {
    isMobile: function() {
      let flag = navigator.userAgent.match(
        /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
      );
      return flag;
    },
    forget: function() {
      this.$router.push("/mobile/modifypw");
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
      }).then(response => {
        let data = response.data;
        if (data.role === "teacher") {
          console.log('teacher');
          this.$store.state.token = data.token;
          this.$store.state.userType = "teacher";
          if(data.is_active===0){//需要激活
            this.$router.push('/mobile/teacher/activation')
          }
          else{
            this.$router.push('')
          }
        }

        if(data.role==='student'){
          console.log('student');
          this.$store.state.token=data.token;
          this.$store.state.userType='student';
          if(data.is_active===0){//需要激活
            this.$router.push('mobile/student/activation')
          }
          else{
            this.$router.push('')
          }
        }

      });
    }
  }
};
</script>
