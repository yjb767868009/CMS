<template>
  <div class="course" style="background:#eee">
    <x-header title="讨论课" style="background-color:#35495e;height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="onClick" style="background:0;height:0px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <group :title="课程">
      <cell title="OOAD（主）" is-link link=""></cell>
      <cell title="J2EE" is-link link=""></cell>

    </group>
    <x-button @click="doing" style="margin-top:100px;font-size:10px;left:30%" mini type="warn">正在进行讨论课</x-button>
  </div>
</template>

<script>
import {XHeader,Cell,Group,XButton} from 'vux'
export default {
    components:{
        XHeader,
        Cell,
        Group,
        XButton
    },
    data(){
        return{

        }   
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        doing:function(){

        }
    }
}
</script>
