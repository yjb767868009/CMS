<template>
<div class="student" style="background:#eee">
    <x-header title="代办及通知" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group style="text-align:left">
      <cell v-if="done" style="height:30px" is-link title="xxxxx分组邀请" @click.native="detail=!detail" :arrow-direction="detail?'up':'down'">
        <img slot="icon" src="@/assets/exclamation.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
      <template v-if="detail&&done">
        <cell value-align="left" style="padding-left:30px">xxxx特殊组队申请</cell>
         <img @click="choice('yes')" src="@/assets/correct.png" style="margin-left:80px" width="20px" height="20px"/>
         <img @click="choice('no')" src="@/assets/wrong.png" style="margin-left:135px" width="20px" height="20px"/>
      </template>
    </group>

    <!-- <group style="text-align:left">历史消息：
      <cell is-link title="xxxx特殊组队申请">
        <img slot="icon" src="@/assets/correct.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
      <cell is-link title="aaaa特殊组队申请">
        <img slot="icon" src="@/assets/wrong.png" style="display:block;margin-right:12px;margin-left:3px" width="20px" height="20px"/>
      </cell>
    </group> -->
    <div v-transfer-dom>

       <confirm v-model="todo"
        :title="'提示'"
        @on-confirm="sure">
        <p style="text-align:center;font-size:1.3em">请确认操作</p>
      </confirm>

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
        XButton,Group,Cell,TransferDom,Popup,Confirm} from 'vux'

  export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,Popup,Confirm
    },
    data() {
        return{
            name:'name',
            newItem:'newItem',
            show:false,
            detail:false,
            todo:false,
            YN:'',
            done:true,
        }
    },
    methods:{
        
        Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
        TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
        GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
        agree(){
            console.log('agree')
        },
        refuse(){
        },
        choice(which){
            this.todo=!this.todo
            if(which==='no'){
                this.YN='refuse'
                console.log(this.YN)
                this.YN=''
            }else if(which==='yes'){
                this.YN='agree'
                console.log(this.YN)
                this.YN=''
            }
        },
        sure:function(){
            console.log('sure')
            this.done=false
        }
    }
        
  };
</script>