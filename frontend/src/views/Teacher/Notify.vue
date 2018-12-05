<template>
<div class="student" style="background:#eee">
    <x-header title="待办及通知" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="onClick" style="background:0;height:30px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <group style="text-align:left">
      <cell is-link title="xxxxx分组邀请">
        <img slot="icon" src="@/assets/exclamation.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
      <cell is-link title="xxxx特殊组队申请">
        <img slot="icon" src="@/assets/exclamation.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
    </group>

    <group style="text-align:left">历史消息：
      <cell is-link title="xxxx特殊组队申请">
        <img slot="icon" src="@/assets/correct.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
      <cell is-link title="aaaa特殊组队申请">
        <img slot="icon" src="@/assets/wrong.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
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