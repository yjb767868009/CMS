<template>
  <div class="course">
    <x-header title="OOAD" style="background-color:#35495e;height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="onShow" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-close-empty" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <div title='新建讨论课' style="padding-top:20px">新建讨论课</div>


    <group label-width="5em">
        <x-input v-model="stopic" placeholder="主题 讨论课大纲"></x-input>
        <x-input v-model="pp2" placeholder="讨论课主要内容 要求 具体讨论部分"></x-input>
        <popup-picker title="讨论课次序号" :data="options1" v-model="option1" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
        <x-switch title="讨论课可见" value-map="['不可见',可见']"></x-switch>
    </group>
    <group label-width="6em">
        <datetime v-model="limitHourValue" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm" @on-change="change" title="报名开始时间"></datetime>
        <datetime v-model="limitHourValue" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm" @on-change="change" title="报名截止时间"></datetime>
        <popup-picker title="报名小组数" :data="options2" v-model="option2" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
        <x-switch title="报名顺序自定" value-map="['不自定',自定']"></x-switch>
        <popup-picker title="所属round" :data="options3" v-model="option3" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
    </group>

    <x-button @click.native="newseminar" type="primary" style="margin-top:100px;color:#fff">发布</x-button>
  </div>
</template>

<script>
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,XSwitch} from 'vux'
export default {
    components:{
        XHeader,
        XButton,
        Divider,
        Group,
        Datetime,
        XInput,
        PopupPicker,
        XSwitch
    },
    methods: {
    onChange (val) {
      console.log('change', val)
        },
    onShow () {
        console.log('on show')
    },
    onHide (type) {
        console.log('on hide', type)
    },
    change (value) {
      console.log('change', value)
    },
    newseminar(){
            console.log(this.stopic)
        }
    },
    data () {
        return {
            stopic:"",
            pp2:"",
            option1: '1',
            options1: [['1', '2', '3','4']],
            startDate: '2018-1-1',
            endDate: '2018-12-31',
            option2: '6',
            options2: [['1', '2', '3','4','5','6']],
            option3: '无',
            options3: [['无', '1' ,'2', '3','4','5','6']],
            
        }
    },
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>