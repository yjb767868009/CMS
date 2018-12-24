<template>
<div class="student" style="height:20px;background:#fff">
    <x-header title="我的课程" style="height:60px;padding-top:12px" @on-click-more="onClick">
    </x-header>
    <group title="我的讨论课">
      
        <cell is-link :border-intent="false" :arrow-direction="showContent002 ? 'up' : 'down'"
         @click.native="showContent002 = !showContent002" value-align="left">
        <span>OOAD 2016-1</span>
        </cell>

            <template v-if="showContent002">
                <cell-box :border-intent="false" class="sub-item" is-link link="/mobile/Student/studentCourseInfo" style="padding-left:130px">课程信息</cell-box>
                <cell-box class="sub-item" is-link style="padding-left:130px" link="/mobile/Student/myScore">我的成绩</cell-box>
                <cell-box class="sub-item" is-link style="padding-left:130px" link="/mobile/Student/teamFreedom">我的组队</cell-box>
            </template>
        
      
        <cell is-link :border-intent="false" :arrow-direction="showContent003 ? 'up' : 'down'"
         @click.native="showContent003 = !showContent003" value-align="left">
        <span>J2EE</span>
        </cell>

            <template v-if="showContent003">
                <cell-box :border-intent="false" class="sub-item" is-link link="/mobile/Student/studentCourseInfo" style="padding-left:130px">课程信息</cell-box>
                <cell-box class="sub-item" is-link style="padding-left:130px" link="/mobile/Student/myScore">我的成绩</cell-box>
                <cell-box class="sub-item" is-link style="padding-left:130px" link="/mobile/Student/teamFreedom">我的组队</cell-box>
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
            showContent001:false,
            showContent002:false,
            showContent003:false,
            show:false,
        }
    },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        onClick:function(){
            this.show=true;
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