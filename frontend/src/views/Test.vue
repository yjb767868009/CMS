<template>
  <el-button @click="test">button</el-button>
</template>

<script>
export default {
  data() {
    return {
      addTeacherForm: {
        name: "",
        account: "",
        email: "",
        password: ""
      }
    };
  },
  methods: {
    test: function(){
      console.log("click");
      this.$router.push('/mobile/teacher/activation')
    }
  }
};
</script>