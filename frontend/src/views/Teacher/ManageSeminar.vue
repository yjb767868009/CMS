<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD-讨论课" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="back" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button>
        <button @click="more" style="background:0;height:30px;border:0" slot="right">
            <icon src="../sassets/暂停.png" size="35"></icon>
        </button>
    </x-header>

    <cell title="业务流程分析" style="background-color:#fff">
        <div style="color:#000">
            <img slot="right" src="../assets/暂停.png" style="display:block;margin-right:10px;" width="20px" height="20px" @click="pause"/>
        </div>
    </cell>

    <div></div>





    <x-button @click="comfirm" type="primary" style="margin-top:100px;color:#fff">确认</x-button>
    <x-button @click="modify" type="primary" plain style="margin-top:10px">修改</x-button>
  </div>
</template>

<script>
import {XHeader,Cell,Group,CellBox,XButton,XTable,Icon} from 'vux'
export default {
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,
        XButton,
        XTable,
        Icon
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
        comfirm:function(){

        },
        modify:function(){

        }
    }
}
</script>
