<template>
  <div class="course" style="background:#eee">

    <!-- 班级信息 -->
    <template v-if="!addingKlass">
      <x-header title="班级信息" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}"
        @on-click-more="show=!show">
      </x-header>

      <template v-for="klass in klasses" style="text-decoration:none">
        <form-preview :key="klass.klassId" :header-label="klass.grade+'-'+klass.klassSerial" :body-items="[{
        label:'讨论课时间',
        value:klass.klassTime
        },
        {
        label:'讨论课地点',
        value:klass.klassPlace
        },{
        label:'班级学生名单',
        value:'等一个is_uploaded的api'
      }]"
          :footer-buttons="footerButtons" :name="klass.klassId">
        </form-preview>
      </template>

      <br />
      <x-button type="primary" @click.native="newKlass">新建班级</x-button>

      <div v-transfer-dom>
        <popup v-model="show" height="23%">
          <div>
            <cell value-align="left" title=""><img slot="icon" src="@/assets/message.png" style="display:block;margin-right:10px;"
                width="30px" height="30px" />
              <div style="padding-left:110px;font-size:1.3em;color:#000" @click="Undo">代办</div>
            </cell>
            <cell value-align="left" title=""><img slot="icon" src="@/assets/man.png" style="display:block;margin-right:10px;"
                width="30px" height="30px" />
              <div style="padding-left:110px;font-size:1.3em;color:#000" @click="TeacherInfo">个人页</div>
            </cell>
            <cell value-align="left" title=""><img slot="icon" src="@/assets/book.png" style="display:block;margin-right:10px;"
                width="30px" height="30px" />
              <div style="padding-left:110px;font-size:1.3em;color:#000" @click="GoSeminar">讨论课</div>
            </cell>
          </div>
        </popup>
      </div>
    </template>

    <!-- 新建班级 -->
    <template v-if="addingKlass">
      <x-header title="新建班级" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: false}">
      </x-header>
      <group>
        <x-input title="新班级年级" text-align="right" v-model="newKlassGrade"></x-input>
        <x-input title="班级号" text-align="right" v-model="newKlassSerial"></x-input>
        <datetime-range title="上课时间:" v-model="dateValue" start-date="2018-12-25" end-date="2019-06-08" format="YYYY年MM月DD日" @on-change="onChange"></datetime-range>
      </group>

      <x-button type="primary" @click.native="postNewKlass">保存</x-button>
    </template>

  </div>
</template>

<script>
  import {
    XHeader,
    XButton,
    Divider,
    TransferDom,
    Popup,
    Cell,
    DatetimeRange,
    XInput,
    Group,
    FormPreview
  } from 'vux'
  export default {
    directives: {
      TransferDom
    },
    components: {
      XHeader,
      XButton,
      Divider,
      Popup,
      Cell,
      XInput,
      Group,
      FormPreview,
      DatetimeRange
    },
    data() {
      return {
        addingKlass: false,
        show: false,
        dateValue: ['2017-01-15', '03', '05'],
        klasses: [{
          klassId: 1,
          grade: '2016',
          klassSerial: '1',
          klassTime: '1t',
          klassPlace: '1p'
        }, {
          klassId: 2,
          grade: '2016',
          klassSerial: '2',
          klassTime: '2t',
          klassPlace: '2p'
        }],
        footerButtons: [{
          style: 'primary',
          text: '提交',
          onButtonClick: (name) => {
            console.log('klassId:', name)
          }
        }, {
          style: "default",
          text: '删除班级',
          onButtonClick: (name) => {
            console.log('klassId:', name)
          }
        }, {
          style: "default",
          text: '上传学生名单',
          onButtonClick: (name) => {
            console.log('klassId:', name)
          }
        }]
      }
    },
    // mounted:function(){
    //     this.$axios.get('/course/'+this.$store.state.teacher.currentCourse.courseId+'/Klass').then((response)=>{
    //         this.klasses=response.data
    //     })
    // },
    methods: {
      Undo() {
        this.$router.push('/mobile/teacher/notify')
      },
      TeacherInfo() {
        this.$router.push('/mobile/teacher')
      },
      GoSeminar() {
        this.$router.push('/mobile/teacher/seminars')
      },
      newKlass() {
        this.newKlassGrade = '',
          this.newKlassSerial = '',
          this.newKlassTime = '',
          this.newKlassPlace = ''
        this.addingKlass = true
        console.log('newKlass...')
      },
      postNewKlass() {
        //this.$axios.post('')
        //this.$axios.get('')
        console.log(this.newKlassGrade, this.newKlassSerial)
        this.addingKlass = false
      },
      onChange(val){
          console.log(val)
          this.newKlassTime=val[0]
      }
    }
  }

</script>

<style>
  .box {
    padding: 0 15px;
  }

</style>
