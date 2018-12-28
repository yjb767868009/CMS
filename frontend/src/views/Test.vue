<template>
  <div class="course">
    <el-button><a href="/">test</a></el-button>
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
        clickF:function(){
            console.log('fdjklfa')
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