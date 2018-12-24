<template>
  <div class="login" title="2016-(1)">
    <x-header title="书面报告" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <group>

    <cell style="background:#fff" title="第一组：" value-align="left">
         <div style="text-decoration:underline" @click="look">
            <span style="color: green">1.1.dox</span>
            <x-input slot="right" style="margin-left:40%;width:50px;height:30px" placeholder="成绩"></x-input>
        </div>
    </cell>
    <cell style="background:#eee" title="第二组：" value-align="left">
         <div style="text-decoration:underline" @click="look"> 
            <span style="color: green">1.2.dox</span>
            <x-input slot="right" style="margin-left:40%;width:50px;height:30px" placeholder="成绩"></x-input>
         </div>
    </cell>
    <cell style="background:#fff" title="第三组：" value-align="left">
         <div style="text-decoration:underline" @click="look">
            <span style="color: green">1.3.dox</span>
            <x-input slot="right" style="margin-left:40%;width:50px;height:30px" placeholder="成绩"></x-input>
         </div>
    </cell>
    <cell style="background:#eee" title="第四组：" value-align="left">
         <div style="text-decoration:underline" @click="look">
            <span style="color: green">1.4.dox</span>
            <x-input slot="right" style="margin-left:40%;width:50px;height:30px" placeholder="成绩"/>
         </div>

    </cell>
    </group>


    <x-button @click="download" type="primary" style="margin-top:100px;color:#fff">批量下载</x-button>
    <x-button @click="upload" type="primary" plain style="margin-top:10px">确认</x-button>
  </div>
</template>

<script>
import {XHeader,Cell,Group,CellBox,XButton,XInput} from 'vux'
export default {
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,
        XButton,
        XInput
    },
    data(){
        return{

        } 
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        back:function(){
            this.$router.push('/mobile/teacher/seminar')
        },
        more:function(){
        },
        download:function(){

        },
        upload:function(){

        }
    }
}
</script>
