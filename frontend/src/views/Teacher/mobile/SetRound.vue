<template>
  <div class="course" style="background:#eee">
    <x-header title="第一轮" style="background-color:#35495e;height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="close" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>
    <div style="height:30px;padding-top:12px;text-align:left" class="box">讨论课：
        <div style="padding:0 50px;padding-top:12px">业务流程分析</div>
        <div style="padding:0 50px;padding-top:12px">领域模型设计</div>
        <x-button @click="post1" mini type="primary" style="margin-left:50px;margin-top:12px">添加讨论课</x-button>
    </div>

    <div style="height:30px;padding-top:12px;text-align:left;margin-top:100px" class="box">
        成绩设置：
        <div>
            <group style="height:20px" label-width="5em">
            <popup-picker title="总分" :data="list1" v-model="value1" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
            </group>
        </div>
    </div>

    <div style="height:30px;padding-top:12px;text-align:left;margin-top:40px" class="box">
        本轮讨论课报名次数：
        <div>
            <group style="height:20px" label-width="5em">
            <popup-picker title="一班" :data="list2" v-model="value2" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
            <popup-picker title="二班" :data="list2" v-model="value2" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
            <popup-picker title="三班" :data="list2" v-model="value2" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
            </group>
        </div>
    </div>

    <x-button @click="post1" style="margin-top:140px;width:200px" mini type="primary">修改</x-button>
  </div>
</template>

<script>
import axios from 'axios'
import {XHeader,XButton,Group,PopupPicker,Picker} from 'vux'
export default {
    components:{
        XHeader,
        XButton,
        Group,
        PopupPicker,
        Picker
    },
    data(){
        return{
            value1: ['最高分'],
            list1: [['最高分', '平均分']],
            value2:['1'],
            list2:[['1','2']]
        }

    },
    methods:{
        onChange (val) {
            console.log('val change', val)
        },
        onShow () {
            console.log('on show')
        },
        onHide (type) {
            console.log('on hide', type)
        }
    }
}
</script>

<style scoped>
.box {
  padding: 0 15px;
}
.picker-buttons {
  margin: 0 15px;
}
</style>
