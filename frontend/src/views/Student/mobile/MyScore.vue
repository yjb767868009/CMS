<template>
<div class="student" style="height:20px;background:#fff">
    <x-header title="OOAD 2016-1" style="height:60px;padding-top:12px" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group>
      <cell is-link :border-intent="false" :arrow-direction="showContent001 ? 'up' : 'down'"
      @click.native="showContent001 = !showContent001" value-align="left">
      <span>第一轮</span>
      </cell>

        <template v-if="showContent001">
      
        <cell is-link :border-intent="false" :arrow-direction="showContent002 ? 'up' : 'down'"
         @click.native="showContent002 = !showContent002" value-align="left" style="padding-left:130px">
        <span>业务流程分析</span>
        </cell>

            <template v-if="showContent002">
                <cell primary="content" :border-intent="false" value-align="left">
                    <div style="padding-left:10px;color:#000;font-size:0.8em">
                    展示：&emsp;&emsp;&emsp;提问：&emsp;&emsp;&emsp;书面报告：
                    <p>本次总成绩：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;本轮总成绩：</p>
                    </div>
                </cell>
            </template>
        
      
        <cell is-link :border-intent="false" :arrow-direction="showContent003 ? 'up' : 'down'"
         @click.native="showContent003 = !showContent003" value-align="left" style="padding-left:130px">
        <span>领域模型</span>
        </cell>

            <template v-if="showContent003">
                <cell primary="content" :border-intent="false" value-align="left">
                    <div style="padding-left:10px;color:#000;font-size:0.8em">
                    展示：&emsp;&emsp;&emsp;提问：&emsp;&emsp;&emsp;书面报告：
                    <p>本次总成绩：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;本轮总成绩：</p>
                    </div>
                </cell>
            </template>
        </template>
        
        <cell is-link :border-intent="false" :arrow-direction="showContent004 ? 'up' : 'down'"
      @click.native="showContent004 = !showContent004" value-align="left" style="margin-top:10px">
      <span>第二轮</span>
      </cell>

      <template v-if="showContent004">
      
        <cell is-link :border-intent="false" :arrow-direction="showContent005 ? 'up' : 'down'"
         @click.native="showContent005 = !showContent005" value-align="left" style="padding-left:130px">
        <span>代码检查</span>
        </cell>

            <template v-if="showContent005">
                <cell primary="content" :border-intent="false" value-align="left">
                    <div style="padding-left:10px;color:#000;font-size:0.8em">
                    展示：&emsp;&emsp;&emsp;提问：&emsp;&emsp;&emsp;书面报告：
                    <p>本次总成绩：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;本轮总成绩：</p>
                    </div>
                </cell>
            </template>
      
        <cell is-link :border-intent="false" :arrow-direction="showContent006 ? 'up' : 'down'"
         @click.native="showContent006 = !showContent006" value-align="left" style="padding-left:130px;">
        <span>对象模型设计</span>
        </cell>

            <template v-if="showContent006">
                <cell primary="content" :border-intent="false" value-align="left">
                    <div style="padding-left:10px;color:#000;font-size:0.8em">
                    展示：&emsp;&emsp;&emsp;提问：&emsp;&emsp;&emsp;书面报告：
                    <p>本次总成绩：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;本轮总成绩：</p>
                    </div>
                </cell>
            </template>
      </template>
    </group>


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
    .vux-header-more{
        padding-top:9px;
    }
    .weui-cells{
        color:#000;
    }
</style>

<script>
import axios from 'axios'
import {TransferDom,XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup} from 'vux'
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
        ButtonTabItem, CellBox, CellFormPreview,Popup
    },
    data() {
        return{
        name:'name',
        newItem:'newItem',
        show:false,
        showContent001: false,
        showContent002:false,
        showContent003:false,
        showContent004: false,
        showContent005:false,
        showContent006:false,
        }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            console.log("sss");
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