<template>
<div class="student" style="height:650px;background:#eee;">
    <x-header title="OOAD-讨论课" style="height:60px;padding-top:12px;font-size:20px"  :right-options="{showMore: true}"  @on-click-more="show=!show">
    </x-header>
    <div style="font-size:18px;background:#fff"><cell primary="content" title="轮次：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;第二轮</div></cell></div>
    <div style="font-size:18px;background:#eee"><cell primary="content" title="主题：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;业务流程分析</div></cell></div>
    <div style="font-size:18px;background:#fff"><cell primary="content" title="课次序号：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;第二次</div></cell></div>
    
    
    <cell primary="content" title="要求：" value-align="left"><div style="padding-left:30px">界面导航图和所有界面原型设计课堂讨论每个小组15分钟</div></cell>
    <div style="font-size:18px;background:#fff">
        <cell primary="content" title="课程情况：" value-align="left">
        <div style="padding-left:65px;color:#000;">未开始</div>
        </cell>
    </div>

    <div style="font-size:15px;background:#fff;margin-top:20px">
        <cell primary="content" title="开始时间：" value-align="left" style="height:20px">
        <div style="padding-left:65px;color:#FF3333;">2018.10.1&emsp;12:00</div></cell>
        <cell primary="content" title="截止时间：" value-align="left" style="height:20px">
        <div style="padding-left:65px;color:#FF3333;">2018.10.7&emsp;00:00</div></cell>
    </div>

    <flexbox style="margin-top:30px">
                <x-button type="primary" @click.native="registration=!registration">报名</x-button>
    </flexbox>

    <div v-transfer-dom>
        <confirm v-model="registration"
        title="提示"
        @on-cancel="onCancel"
        @on-confirm="onConfirm"
        >
        <p style="text-align:center;">确认报名吗</p>
      </confirm>
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
import axios from 'axios'
import {XHeader,
        XButton,Cell,Popup,TransferDom,Flexbox,Confirm} from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        XButton,
        Cell,Popup,Flexbox,Confirm
    },
    data() {
       return{ 
        show:false,
        registration:false,
    }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            console.log("sss");
        },
        edit:function(){
            this.$router.push('/seminarSeqFinished')
        },
        sign:function(){
            this.$router.push('/seminarSeqUnsigned')
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        onCancel:function(){
            console.log('取消')
        },
        onConfirm:function(){
            console.log('确认')
        },
    }
        
  };
</script>