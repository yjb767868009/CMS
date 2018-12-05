<template>
<div class="student" style="height:800px;background:#eee;">
    <x-header title="OOAD-讨论课" style="height:60px;padding-top:12px;font-size:20px"  :right-options="{showMore: true}"  @on-click-more="showMenus = true">
    </x-header>
    <div style="font-size:18px;background:#fff"><cell primary="content" title="轮次：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;&emsp;第二轮</div></cell></div>
    <div style="font-size:18px;background:#eee"><cell primary="content" title="主题：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;&emsp;业务流程分析</div></cell></div>
    <div style="font-size:18px;background:#fff"><cell primary="content" title="课次序号：" value-align="left"><div style="padding-left:30px;color:#000;">&emsp;&emsp;第二次</div></cell></div>
    
    
    <cell primary="content" title="要求：" value-align="left"><div style="padding-left:30px">界面导航图和所有界面原型设计课堂讨论每个小组15分钟</div></cell>
    <div style="font-size:18px;background:#fff">
        <cell primary="content" title="报名情况：" value-align="left">
        <div style="padding-left:65px;color:#000;">已报名</div>
        </cell>
    </div>

    
    <div style="font-size:18px;background:#fff">
        <cell primary="content" title="PPT：" value-align="left">
        <div style="padding-left:105px;color:#000;">5.0</div>
        </cell>
    </div>
    <div style="font-size:18px;background:#fff">
        <cell primary="content" title="书面报告：" value-align="left">
        <div style="padding-left:65px;color:#000;">5.0</div>
        </cell>
    </div>
    <div style="font-size:18px;background:#fff">
        <cell primary="content" title="提问：" value-align="left">
        <div style="padding-left:100px;color:#000;">5.0</div>
        </cell>
    </div>
    <div style="font-size:18px;background:#fff">
        <cell primary="content" title="总成绩：" value-align="left">
        <div style="padding-left:80px;color:#000;">5.0</div>
        </cell>
    </div>

    <div v-transfer-dom>
      <actionsheet :menus="menus" v-model="showMenus"></actionsheet>
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
        XButton,GroupTitle,Cell,Picker,Actionsheet,
        ButtonTab,
        ButtonTabItem} from 'vux'
  export default {
    components:{
        XHeader,
        XButton,
        GroupTitle,
        Cell,
        Picker,
        Actionsheet,
        ButtonTab,
        ButtonTabItem
    },
    data() {
       return{ 
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
        },
        edit:function(){
            this.$router.push('/seminarSeqFinished')
        },
        sign:function(){
            this.$router.push('/seminarRegistrationModification')
        }
    }
        
  };
</script>