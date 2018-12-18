<template>
  <div class="course">
    <x-header title="新增共享" style="background-color:#35495e;height:60px;padding-top:12px" :left-options="{showBack:false}">
        <button @click="close" style="background:0;height:30px;border:0" slot="left">
            <x-icon type="ios-arrow-back" size="35" style="fill:#fff"></x-icon>
        </button>
    </x-header>

    <group label-width="10em">
        <popup-picker title="共享类型" :data="options2" v-model="option2" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>
    </group>

    <group>
        共享对象
            <group label-width="10em" >
            <popup-picker style="height:30px" :data="options1" v-model="option1" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>

            </group>

        冲突课程
            <group label-width="10em">
            <popup-picker style="height:30px" :data="options1" v-model="option1" @on-show="onShow" @on-hide="onHide" @on-change="onChange"></popup-picker>

            </group>
            <x-button @click="newseminar" type="warn" mini plain style="margin-left:60%;margin-top:10px">新增对象</x-button>
    </group>

    <x-button @click="newseminar" type="primary" style="margin-top:100px;color:#fff" @click.native="showPlugin">确认共享</x-button>


    <div v-transfer-dom>
      <confirm v-model="show"
      @on-cancel="onCancel"
      @on-confirm="onConfirm"
      @on-show="onShow"
      @on-hide="onHide">
        <p style="text-align:center;">确认发送共享请求？</p>
      </confirm>
    </div>
  </div>
</template>

<script>
import {XHeader,XButton,Group,PopupPicker,Confirm} from 'vux'
export default {
    components:{
        XHeader,
        XButton,
        Group,
        PopupPicker,
        Confirm
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
        onCancel () {
      console.log('on cancel')
    },
        onConfirm (msg) {
            console.log('on confirm')
            if (msg) {
                alert(msg)
            }
        },
        showPlugin () {
            this.$vux.confirm.show({
            title: 'Title',
            content: 'Content',
            onShow () {
                console.log('plugin show')
            },
            onHide () {
                console.log('plugin hide')
            },
            onCancel () {
                console.log('plugin cancel')
            },
            onConfirm () {
                console.log('plugin confirm')
            }
            })
        }
    },
    data () {
        return {
            option1: ' ',
            options1: [['ooad','j2ee','软工']],
            startDate: '2018-1-1',
            endDate: '2018-12-31',
            option2: ' ',
            options2: [['共享分组','共享讨论课']],

        }
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
