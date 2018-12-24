<template>
<div class="student" style="height:800px;background:#fff;">
    <x-header title="OOAD" style="height:60px;padding-top:12px;font-size:20px" :left-options="{showBack:false}" :right-options="{showMore: true}"  @on-click-more="show=!show">
    </x-header>
    <cell primary="content" title="课程简介：" value-align="left"><div style="padding-left:5px;font-size:15px">balabala</div></cell>
    <cell primary="content" :border-intent="false" value-align="left"><span style="color:#000">成绩计算规则：</span>
        <div style="padding-left:98px;color:#000;">
            <p>课堂展示：50%</p>
            <p>课堂提问：20%</p>
            <p>书面报告：30%</p>
        </div>
    </cell>
    
    <div style="font-size:15px;background:#fff;margin-top:15px">
        <div style="padding-left:15px;color:#000;">小组人数：&emsp;&emsp;&emsp;&emsp;&emsp;6~8</div>
        <div style="padding-left:15px;color:#000;margin-top:15px">组队开始时间：&emsp;&emsp;&emsp;2018.10.1&emsp;12:00</div>
        <div style="padding-left:15px;color:#000;margin-top:15px">组队截止时间：&emsp;&emsp;&emsp;2018.10.7&emsp;00:00</div>
        <div style="padding-left:15px;color:#000;margin-top:15px">组员性别要求：&emsp;&emsp;&emsp;&emsp;无</div>
        <div style="padding-left:15px;color:#000;margin-top:15px">组员星座要求：&emsp;&emsp;&emsp;&emsp;无</div>
    </div>
    
    <div style="padding-left:15px;color:#000;margin-top:15px">冲突课程：
        <div style="padding-left:100px;color:#000;">
            <p>.Net(xxx老师)</p>
            <p>.Net(xxx老师)</p>
        </div>
    </div>

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
import axios from 'axios'
import {TransferDom,XHeader,Cell,Actionsheet,Popup
        } from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        Cell,
        Actionsheet,
        Popup
    },
    data() {
       return{ 
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
        }
    }
        
  };
</script>