<template>
  <div class="activation" style="background:#eee">
    <x-header title="找回密码" style="height:60px;padding-top:12px" :left-options="{showBack:false}"></x-header>
    <group>
      <x-input v-model="account" style="background-color:#fff" placeholder="学号/教工号"></x-input>
    </group>
    <x-button @click.native="next" style="margin-top:100%">下一步</x-button>
  </div>
</template>

<script>
import { XHeader, XButton, XInput,Group } from "vux";

export default {
  data() {
    return {
      account: ""
    };
  },
  components: {
    XHeader,
    XButton,
    XInput,Group
  },
  methods: {
    next: function() {
      this.$axios
        .post("/user/password",{
          account:this.account
        })
        .then(response => {
          this.$router.push("/login", {
              account: this.account
          });
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>
