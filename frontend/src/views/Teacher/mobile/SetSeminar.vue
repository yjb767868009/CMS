<template>
  <div class="login" style="background:#eee">
    <x-header title="OOAD" style="height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="back" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button>
        <button @click="more" style="background:0;height:30px;border:0" slot="right">
            <x-icon type="ios-plus-empty" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <group :title="轮次">
      <cell
      title="第一轮"
      is-link
      :border-intent="false"
      :arrow-direction="showContent001 ? 'up' : 'down'"
      @click.native="showContent001 = !showContent001"></cell>

        <template v-if="showContent001">
            <cell-box :border-intent="false" class="sub-item" is-link link="/mobile/teacher/setround">该轮轮次设置</cell-box>

            <cell
            title="业务流程分析"
            is-link
            value-align="left"
            style="height:24px"
            :border-intent="false"
            :arrow-direction="showContent001 ? 'up' : 'down'"
            @click.native="showContent002 = !showContent002"></cell>
            <template v-if="showContent002">
                <cell-box class="sub-item" is-link link="">2016-(1)</cell-box>
                <cell-box class="sub-item" is-link link="">2016-(2)</cell-box>
                <cell-box class="sub-item" is-link link="">2016-(3)</cell-box>
            </template>

            <cell
            title="领域模型设计"
            is-link
            value-align="left"
            style="height:24px"
            :border-intent="false"
            :arrow-direction="showContent001 ? 'up' : 'down'"
            @click.native="showContent003 = !showContent003"></cell>
            <template v-if="showContent003">
                <cell-box class="sub-item" is-link link="">2016-(1)</cell-box>
                <cell-box class="sub-item" is-link link="">2016-(2)</cell-box>
                <cell-box class="sub-item" is-link link="">2016-(3)</cell-box>
            </template>

        </template>
    </group>
    <x-button @click="newround" style="margin-top:100px;background-color:#35495e;color:#fff">新建轮次</x-button>
    <x-button @click="newseminar" style="margin-top:10px;background-color:#35495e;color:#fff">新建讨论课</x-button>
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
            showContent001: false,
            showContent002: false,
            showContent003: false
        } 
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        back:function(){
            this.$router.push('/mobile/teacher/course')
        },
        more:function(){

        },
        newround:function(){
            this.$router.push('/mobile/teacher/createround')
        },
        newseminar:function(){
            this.$router.push('/mobile/teacher/createseminar')
        }
    }
}
</script>
