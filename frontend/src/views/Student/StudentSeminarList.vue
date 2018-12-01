<template>
<div class="student" style="height:200px;background:#eee">
    <x-header title="讨论课" style="height:60px;padding-top:12px" :right-options="{showMore: true}" @on-click-more="showMenus = true">
    </x-header>
    <group>
      <cell is-link title="OOAD 2016-1" link="/studentClass"><img slot="icon" src="../../assets/lock.png" style="display:block;margin-right:12px;" width="23px" height="23px"/></cell>
      <cell is-link title="J2EE 2016-1" link="/studentClass"><img slot="icon" src="../../assets/lock.png" style="display:block;margin-right:10px;" width="23px" height="23px"/></cell>
      <cell is-link title="软件工程 2016-2" link="/studentClass"><img slot="icon" src="../../assets/lock.png" style="display:block;margin-right:10px;" width="23px" height="23px"/></cell>
    </group>
    <div v-transfer-dom>
      <actionsheet :menus="menus" v-model="showMenus"></actionsheet>
    </div>
</div>
</template>

<style>
    .vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import axios from 'axios'
import {XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem} from 'vux'
  export default {
    components:{
        XHeader,
        XButton,
        Group,
        Cell,
        Actionsheet,
        ButtonTab,
        ButtonTabItem
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            menus: {
            menu1: '待办',
            menu2: '个人页',
            menu3: '讨论课'
      },
        showMenus:false,
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