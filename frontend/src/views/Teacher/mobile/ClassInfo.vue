<template>
  <div class="course" style="background:#eee">
    <x-header title="班级信息" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

    <template v-for="klass in klasses">
        
    </template>

    <div v-transfer-dom>
      <popup v-model="show" height="23%">
          <div>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div></cell>
              <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;" width="30px" height="30px"/><div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div></cell>
          </div>
      </popup>
    </div>
  </div>
</template>

<script>
import {XHeader,XButton,Divider,TransferDom,Popup,Cell,Masker} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        XButton,
        Divider,Popup,Cell,Masker
    },
    data(){
        return{
            show:false,
            klasses:[{
                klassId:1,
                grade:'',
                klassSerial:'',
                klassTime:'',
                klassPlace:''
            },{
                klassId:2,
                grade:'',
                klassSerial:'',
                klassTime:'',
                klassPlace:''
            }]
        }
    },
    // mounted:function(){
    //     this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/Klass').then((response)=>{
    //         this.klasses=response.data
    //     })
    // },
    methods:{
    Undo(){
            this.$router.push('/mobile/teacher/notify')
        },
    TeacherInfo(){
            this.$router.push('/mobile/teacher')
        },
    GoSeminar(){
            this.$router.push('/mobile/teacher/seminars')
        },
    }
}
</script>

<style>
.box {
  padding: 0 15px;
}
</style>
