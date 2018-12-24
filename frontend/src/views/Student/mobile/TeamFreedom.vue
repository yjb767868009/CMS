<template>
<div class="student" style="height:20px;background:#fff">
    <x-header title="OOAD 2016-1" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group>
      
        <cell is-link :border-intent="false" :arrow-direction="showContent002 ? 'up' : 'down'"
         @click.native="showContent002 = !showContent002" value-align="left">
        <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><span style="color:#000">1-1 中华第一皮</span> (我的小组)
        </cell>

            <template v-if="showContent002">
                <cell :border-intent="false" value-align="left" title="组长：" style="height:20px"><span style="color:#000">&emsp;11111111 &emsp;www</span></cell>
                <cell :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000">&emsp;22222222 &emsp;zzz</span></cell>
                <cell :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:50px">&emsp;33333333 &emsp;kkk</span></cell>
            </template>
        
      

        <cell is-link :border-intent="false" :arrow-direction="showContent003 ? 'up' : 'down'"
         @click.native="showContent003 = !showContent003" value-align="left">
        
        <img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/>
        <span style="color:#000">1-2 dior-761</span> invalid
        </cell>

            <template v-if="showContent003">
                <cell :border-intent="false" value-align="left" title="组长：" style="height:20px"><span style="color:#000">&emsp;11111111 &emsp;www</span></cell>
                <cell :border-intent="false" value-align="left" title="组员：" style="height:20px"><span style="color:#000">&emsp;22222222 &emsp;zzz</span></cell>
                <cell :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:50px">&emsp;33333333 &emsp;kkk</span></cell>
            </template>

         <cell is-link :border-intent="false" :arrow-direction="showContent001 ? 'up' : 'down'"
         @click.native="showContent001 = !showContent001" value-align="left">
        
        <img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/>
        <span style="color:#000">未组队学生</span>
        </cell>

            <template v-if="showContent001">
                <cell :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;11111111 &emsp;www</span></cell>
                <cell :border-intent="false" value-align="left"  style="height:20px"><span style="color:#000;padding-left:20px">&emsp;22222222 &emsp;zzz</span></cell>
                <cell :border-intent="false" value-align="left" style="height:20px"><span style="color:#000;padding-left:20px">&emsp;33333333 &emsp;kkk</span></cell>
            </template>
        
        <flexbox style="margin-top:30px">
            <flexbox-item>
                <x-button type="primary"  @click.native="newteam">创建小组</x-button>
            </flexbox-item>
        </flexbox>
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
        ButtonTabItem, CellBox, CellFormPreview,Popup,Flexbox,FlexboxItem} from 'vux'
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
        ButtonTabItem, CellBox, CellFormPreview,Popup,Flexbox,FlexboxItem
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
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        newteam:function(){
            this.$router.push('/mobile/student/newTeam')
        }
    }
        
  };
</script>