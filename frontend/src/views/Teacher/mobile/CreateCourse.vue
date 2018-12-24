<template>
  <div class="course">
    <x-header title="新建课程" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>



    <group label-width="5em">
        <x-input placeholder="课程名称"></x-input>
        <x-input placeholder="课程要求"></x-input>
    </group>

    <div style="height:30px;padding-top:12px;text-align:left" class="box">
        成绩计算规则（百分比）：
            <group style="height:20px" label-width="5em">
            <popup-picker title="展示" :data="options1" v-model="option1" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
            <popup-picker title="提问" :data="options1" v-model="option1" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
            <popup-picker title="报告" :data="options1" v-model="option1" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
            </group>
    </div>

    <group label-width="6em" style="margin-top:140px;padding-top:12px;text-align:left">
        <div style="height:40px;padding-top:12px;text-align:left;margin-top:20px" class="box">小组人数：
            <x-input  style="margin-left:20%;width:50px;height:30px" placeholder="">上限</x-input>
            <x-input  style="margin-left:5%;width:50px;height:30px" placeholder="">下限</x-input>
        </div>
        <datetime v-model="limitHourValue" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm" @on-change="change" title="组队开始时间"></datetime>
        <datetime v-model="limitHourValue" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm" @on-change="change" title="组队截止时间"></datetime>
    </group>

    <x-button @click="newseminar" type="primary" style="margin-top:100px;color:#fff">发布</x-button>
  </div>
</template>

<script>
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker} from 'vux'
export default {
    components:{
        XHeader,
        XButton,
        Divider,
        Group,
        Datetime,
        XInput,
        PopupPicker
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
    }
    },
    data () {
        return {
            option1: ' ',
            options1: [['10','15', '20','25', '30','35', '40','45','50','55','60','65','70','75','80','85','90','95','100']],
            startDate: '2018-1-1',
            endDate: '2018-12-31',
        }
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
