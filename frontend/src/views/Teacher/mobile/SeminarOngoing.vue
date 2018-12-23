<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD-讨论课" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="more" style="background:0;height:30px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>

    <x-header style="background:#fff;margin:20px 0px 0px;height:60px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:6px;">主题：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;业务流程分析</div>
    </x-header>
    <x-header style="background:#eee;height:60px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:6px;">课次序号：&emsp;&emsp;&emsp;1</div>
    </x-header>
    <x-header style="background:#fff;height:85px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:18px;">要求：&emsp;&emsp;界面导航图和所有界面原型设计</div>
    </x-header>
    <x-header style="background:#eee;height:85px;padding-top:12px" :left-options="{showBack:false}">
         <div slot="left" style="font-size:1.3em;color:#000;padding-top:18px;">课程情况：&emsp;&emsp;正在进行</div>
         <p slot="right" @click="edit" style="text-decoration:underline;color:#1AAD19;font-size:1.1em">查看信息</p>
    </x-header>


    <x-button @click="presentation" type="primary" style="margin-top:100px;color:#fff">进入讨论课</x-button>
  </div>
</template>

<script>
import {XHeader,Cell,Group,CellBox,XButton} from 'vux'
export default {
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,
        XButton
    },
    data(){
        return{

        } 
    },
    methods: {
        back:function(){
            this.$router.push('/mobile/teacher/seminars')
        },
        more:function(){
        },
        presentation:function(){
            this.$router.push('')
        }
    }
}
</script>
