<template>
  <div class="course">
    <x-header title="OOAD" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <div style="margin-left:10px">
    <div title='新建讨论课' style="text-align:center;margin-top:20px">新建讨论课</div>


    <group label-width="5em">
        <x-input v-model="topic" placeholder="主题 讨论课大纲"></x-input>
        <x-textarea v-model="intro" placeholder="讨论课主要内容 要求 具体讨论部分"></x-textarea>
        <popup-picker title="讨论课次序号" :data="seminarOrders" v-model="seminarOrder"></popup-picker>
        <x-switch v-model="visible" title="讨论课可见" :value-map="[false,true]"></x-switch>
    </group>
    <group label-width="6em">
        <datetime v-model="signupStartTime" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm"  title="报名开始时间"></datetime>
        <datetime v-model="signupEndTime" :start-date="startDate" :end-date="endDate" format="YYYY-MM-DD HH:mm" title="报名截止时间"></datetime>
        <popup-picker title="报名小组数" :data="teamNums" v-model="teamNum" ></popup-picker>
        <!-- <x-switch title="报名顺序自定" :value-map="['不自定','自定']"></x-switch> -->
        <popup-picker title="所属round" :data="roundOrders" v-model="roundOrder"></popup-picker>
    </group>
    </div>

    <x-button @click.native="submit" type="primary" style="margin-top:100px;color:#fff">发布</x-button>
  </div>
</template>

<script>
import {XHeader,XButton,Divider,Group,Datetime,XInput,PopupPicker,XSwitch,XTextarea} from 'vux'
export default {
    components:{
        XHeader,
        XButton,
        Divider,
        Group,
        Datetime,
        XInput,
        PopupPicker,
        XSwitch,
        XTextarea
    },
    methods: {
    change (value) {
      console.log('change', value)
    }
    },
    data () {
        return {
            seminarOrder: '1',
            seminarOrders: [['1', '2', '3','4','5']],
            startDate: '2018-01-01',
            endDate: '2018-12-31',
            teamNum: '6',
            teamNums: [['1', '2', '3','4','5','6']],
            roundOrder: '无',
            roundOrders: [['无', '1' ,'2', '3','4','5','6']],
            topic:'',
            intro:'',
            visible:false,
            signupStartTime:'',
            signupEndTime:'',
        }
    },
    methods:{
        submit:function(){
            console.log({
                topic:this.topic,
                intro:this.intro,
                order:this.seminarOrder,
                roundOrder:this.roundOrder,
                visible:this.visible,
                teamNumLimit:this.teamNum,
                signupStartTime:this.signupStartTime.slice(0,4)+'/'+this.signupStartTime.slice(5,7)+'/'+this.signupStartTime.slice(8,10),
                signupEndTime:this.signupEndTime.slice(0,4)+'/'+this.signupEndTime.slice(5,7)+'/'+this.signupEndTime.slice(8,10),
                courseId:this.$store.state.teacher.currentCourse.courseId
            })
            // this.$axios.post('/seminar',{
            //     topic:this.topic,
            //     intro:this.intro,
            //     order:this.seminarOrder,
            //     roundOrder:'',
            //     visible:'',
            //     teamNumLimit:'',
            //     signupStartTime:'',
            //     signupEndTime:'',
            //     courseId:''
            // })
        },
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
