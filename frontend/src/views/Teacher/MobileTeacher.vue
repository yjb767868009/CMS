<template>
<div class="student" style="background:#eee">
    <x-header title="我" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="showSheet" style="background:0;height:30px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <x-header align="right" style="background:#fff;margin:20px 0px 0px;height:90px;padding-top:12px border:1" :left-options="{showBack:false}">
         <div slot="right" style="font-size:1.3em;color:#000;">name&emsp;</div>
         <p slot="right" style="font-size:1.3em;color:#000">账号&emsp;</p>
    </x-header>
    <group>
      <cell is-link title="我的课程" link="/mobile/teacher/course"><img slot="icon" src="@/assets/lock.png" style="display:block;margin-right:12px;margin-left:3px" width="15px" height="20px"/></cell>
      <cell is-link title="账户与设置" link="/mobile/teacher/account"><img slot="icon" src="@/assets/package.png" style="display:block;margin-right:10px;" width="20px" height="20px"/></cell>
    </group>
    <group>
    </group>

    <mt-actionsheet
    :actions="actions"
    v-model="sheetVisible"
    ></mt-actionsheet>
</div>
</template>

<script>
import {XHeader,XButton,Group,Cell} from 'vux'
import {Actionsheet} from 'mint-ui'
  export default {
    components:{
        XHeader,
        XButton,
        Group,
        Cell
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            sheetVisible:false,
            actions:[{
                name:'待办',
                method:this.backlog
            },{
                name:'个人页',
                method:this.personalPage
            },{
                name:'讨论课',
                method:this.seminar
            }]
        }
    },
    methods:{
        showSheet:function(){
            this.sheetVisible=true
        },
        
        backlog(){
            console.log('backlog')
        },
        personalPage(){
            console.log('personal')
            this.$router.push('/mobile/teacher/account')
        },
        seminar(){
            console.log('seminar')
        }

    }
        
  };
</script>