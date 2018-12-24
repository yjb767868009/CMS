<template>
<div class="student" style="height:800px;background:#eee;">
    <x-header title="OOAD-讨论课" style="height:60px;padding-top:12px;font-size:20px" :left-options="{showBack:false}" :right-options="{showMore: true}"  @on-click-more="show=!show">
    </x-header>
    <div style="font-size:18px;background:#fff;margin-top:7px;margin-bottom:5px"><cell primary="content" value-align="left">
        <div style="text-align:center;color:#000;height:35px;"><span style="font-size:1.2em">业务流程分析</span>
        </div>
        </cell></div>
    
    <div style="font-size:18px;background:#fff"><cell primary="content" title="第一组：" value-align="left"><div style="padding-left:30px;color:#000;">1-1 业务流程.PPT</div></cell></div>
    <div style="font-size:18px;background:#eee"><cell primary="content" title="第二组：" value-align="left"><div style="padding-left:30px;color:#00DB00;">可报名</div></cell></div>
    <div style="font-size:18px;background:#fff"><cell primary="content" title="第三组：" value-align="left"><div style="padding-left:30px;color:#000;">
        <span style="font-weight:bold;color:#FF3333">1-3 未提交</span></div></cell></div>
    <div style="font-size:18px;background:#eee"><cell primary="content" title="第四组：" value-align="left"><div style="padding-left:30px;color:#00DB00;">可报名</div></cell></div>

    <div v-transfer-dom>
      <popup v-model="show" height="15%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div></cell>
          </div>
      </popup>
    </div>
</div>
</template>

<style>
.vux-header-back{
    padding:9px;
    font-size:1.1em;
  }
.left-arrow{
    margin-top:10px;
}
.vux-header-more{
        padding-top:9px;
    }
</style>


<script>
import Vue from 'vue'
import {XHeader,Cell,
        Group,TransferDom,Popup
        } from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        Group,
        XHeader,
        Cell,Popup
    },
    data() {
       return{ 
        show:false,
    }
    },
   methods:{
        toast:function(){
            Toast(this.name);
        },
        sign:function(){
            console.log("aaa");
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
   }
  }
</script>