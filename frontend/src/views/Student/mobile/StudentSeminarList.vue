<template>
<div class="student" style="height:200px;background:#eee">
    <x-header title="讨论课" style="height:60px;padding-top:12px" :right-options="{showMore: true}" @on-click-more="showMenus = true">
    </x-header>
    <group>
      <cell is-link title="OOAD 2016-1" link="/mobile/student/course"><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:12px;" width="23px" height="23px"/></cell>
      <cell is-link title="J2EE 2016-1" link="/mobile/student/course"><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="23px" height="23px"/></cell>
      <cell is-link title="软件工程 2016-2" link="/mobile/student/course"><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="23px" height="23px"/></cell>
    </group>
</div>
</template>

<style>
    .vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import axios from 'axios'
import {TransferDom,XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem} from 'vux'
  export default {
    directives:{
        TransferDom
    },
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