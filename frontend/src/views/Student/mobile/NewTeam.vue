<template>
<div class="student" style="height:20px;background:#fff">
    <x-header :title="this.$store.state.student.currentCourse.name" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    
    
        <cell :border-intent="false" title="小组名：" value-align="left" style="padding-top:18px;height:30px;padding-right:50px"><el-input style="padding-left:20px;font-size:1.2em;"></el-input></cell>
        <group>
        <cell is-link :border-intent="false" :arrow-direction="showContent002 ? 'up' : 'down'"
         @click.native="showContent002 = !showContent002" value-align="left">
        <span style="color:#000">选择班级：&emsp;&emsp;&emsp;{{currentklass}}</span>
        </cell>
        </group>

            <template v-if="showContent002">
                <template v-for="klass in klasses">
                <cell-box :key="klass.id" :border-intent="false" class="sub-item" style="padding-left:130px" @click.native="currentklass=klass.name">{{klass.name}}</cell-box>
                </template>
            </template>
    

        <cell :border-intent="false" value-align="left" title="添加成员：" style="margin-top:15px;height:20px"><span style="color:#000;padding-left:20px"></span></cell>
        <template v-for="stu in noteam">
            <check-icon :key="stu.id" :value.sync="stu.showNoTeam" style="color:#000;padding-left:15px">  &emsp;&emsp;&emsp;{{stu.account}} &emsp;{{stu.name}}&emsp;&emsp;</check-icon>
        </template>
        <group>
        <cell :border-intent="false" value-align="left" title="搜索成员：" style="padding-top:20px;height:20px;padding-right:30px">
        <x-input style="height:20px" placeholder="请输入成员学号或姓名" v-model="searchstu"></x-input>
        </cell>
        </group>
        <template v-for="stu in noteam">
        <check-icon :key="stu.id" v-if="(searchstu===stu.account||searchstu===stu.name)" :value.sync="stu.showNoTeam" style="color:#000;padding-left:15px">  &emsp;&emsp;&emsp;{{stu.account}} &emsp;{{stu.name}}&emsp;&emsp;</check-icon>
        </template>

        <flexbox style="margin-top:30px">
            <flexbox-item>
                <x-button type="primary" @click.native="submit=!submit">确认提交</x-button>
            </flexbox-item>
        </flexbox>
    <div v-transfer-dom>
      <confirm v-model="submit"
        :show-cancel-button="false"
        title="提示"
        @on-confirm="onConfirm">
        <p style="text-align:center;">新建队伍成功</p>
      </confirm>
      
      <popup v-model="show" height="15%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div></cell>
          </div>
      </popup>
    </div>
</div>
</template>

<style>
    .vux-header-more{
        padding-top:9px;
    }
    .weui-cells{
        color:#000;
    }
</style>

<script>
import axios from 'axios'
import {TransferDom,XHeader,
        XButton,Group,Cell,Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup,CheckIcon,Flexbox,FlexboxItem,Box,Confirm,XInput} from 'vux'
  export default {
    directives:{
        TransferDom
    },
    components:{
        XHeader,
        XButton,
        Group,
        Cell,
        Actionsheet,
        ButtonTab,
        ButtonTabItem, CellBox, CellFormPreview,Popup,CheckIcon,Flexbox,FlexboxItem,Box,Confirm,XInput
    },
    data() {
        return{
            name:'aaa',
            newItem:'newItem',
            show:false,
            submit:false,
            student1:false,
            showContent002:false,
            currentklass:'',
            searchstu:"",
            stunum:1111111111,
            klasses:[
  {
    "id": 45,
    "name": "2016-1",
    "time": "周三 7、8节",
    "classroom": "海韵教学楼"
  },
  {
    "id": 48,
    "name": "2016-2",
    "time": "周二7、8节",
    "classroom": "海韵教学楼"
  }
],
            noteam:[
  {
    "id": "001",
    "account": "111111",
    "name": "张三",
    "showNoTeam":false,
  },
  {
    "id": "002",
    "account": "222222",
    "name": "张四",
    "showNoTeam":false,
  }
]
        }
    },
    // mounted:function(){
    //     this.$axios.get('/course/'+this.$store.state.student.currentCourse.id+'/noteam')
    //     .then((response)=>{
    //         this.noteam=response.data;
    //         for(var i=0;i<this.noteam.length;i++){
    //             this.noteam[i].showNoTeam=false;
    //         }
    //     });
    //     this.$axios.get('/course/'+this.$store.state.student.currentCourse.id+'/class')
    //     .then((response)=>{
    //         this.klasses=response.data;
    //     })
    // },
    methods:{
        toast:function(){
            Toast(this.name)
        },
        running:function(){
            this.$router.push('/mobile/Student/studentSeminarList')
        },
        StudentInfo:function(){
            this.$router.push('/mobile/student/studentInfo')
        },
        onCancel:function(){
            console.log('取消')
        },
        onConfirm:function(){
            console.log('确认')
        },
    }
        
  };
</script>