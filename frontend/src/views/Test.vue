<template>
  <div>
    <a href="javascript:;" @click="console.log('fdk');console.log('22')">点击</a>
    <t-dialog></t-dialog>
  </div>
</template>

<script>
import dialog from '@/views/TestSon.vue'
export default {
  components:{
    "t-dialog":dialog
  }
}
</script>