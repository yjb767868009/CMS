<template>
    <ul>
    <li class="el-menu-item is-active" @click="ShowTeacher" style="">{{msg1}}</li>
    <li class="el-menu-item" @click="ShowStudent" style="">{{msg2}}</li>
    </ul>
</template>

<script>

    export default {
        name: 'cms-sidebar',
        data() {
            return {
                msg1: '教师信息管理',
                msg2: '学生信息管理'
            }
        },
        methods:{
            ShowTeacher:function(){
                this.$emit('ShowTeacher')
            },
            ShowStudent:function(){
                this.$emit('ShowStudnet')
            }
        }
    }

</script>

<style>
    li {
        padding-left: 20px;
    }
</style>
