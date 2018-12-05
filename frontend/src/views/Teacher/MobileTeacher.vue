<template>
<div class="student" style="background:#eee">
    <x-header title="我" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="onClick" style="background:0;height:30px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <x-header align="right" style="background:#fff;margin:20px 0px 0px;height:90px;padding-top:12px border:1" :left-options="{showBack:false}">
         <div slot="right" style="font-size:1.3em;color:#000;">name&emsp;</div>
         <p slot="right" style="font-size:1.3em;color:#000">账号&emsp;</p>
    </x-header>
    <group>
      <cell is-link title="我的课程" link="/mobile/teacher/course"><img slot="icon" src="../assets/lock.png" style="display:block;margin-right:12px;margin-left:3px" width="15px" height="20px"/></cell>
      <cell is-link title="账户与设置" link="/mobile/teacher/account"><img slot="icon" src="../assets/package.png" style="display:block;margin-right:10px;" width="20px" height="20px"/></cell>
    </group>

</div>
</template>

<script>
import axios from 'axios'
import {XHeader,
        XButton,Group,Cell} from 'vux'

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
            newItem:'newItem'
        }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            console.log("sss");
        }
    }
        
  };
</script>