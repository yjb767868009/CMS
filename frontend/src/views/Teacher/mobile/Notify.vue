<template>
<div class="student" style="background:#eee">
    <x-header title="代办及通知" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
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
    <div v-transfer-dom>
      <popup v-model="show" height="23%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div></cell>
          </div>
      </popup>
    </div>

</div>
</template>

<script>
import axios from 'axios'
import {XHeader,
        XButton,Group,Cell,TransferDom,Popup} from 'vux'

  export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,Popup
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            show:false,
        }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            console.log("sss");
        },
        Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
        TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
        GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
    }
        
  };
</script>