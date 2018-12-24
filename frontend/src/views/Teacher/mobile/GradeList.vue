<template>
  <div class="course" style="background:#eee">
    <x-header title="学生成绩" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>
    <group :title="round">
      <cell
      title="第一轮"
      is-link
      :border-intent="false"
      :arrow-direction="showContent001 ? 'up' : 'down'"
      @click.native="showContent001 = !showContent001"></cell>

        <template v-if="showContent001">
                  <cell
                    title="1-1"
                    is-link
                    :border-intent="false"
                    :arrow-direction="showContent011 ? 'up' : 'down'"
                    @click.native="showContent011 = !showContent011"></cell>
                    <template v-if="showContent011">
                        <cell-box class="sub-item" is-link link="">用例分析</cell-box>
                        <cell-box class="sub-item" is-link link="">界面原型</cell-box>
                        <cell-box class="sub-item" is-link link="">总成绩</cell-box>
                    </template>

                  <cell
                    title="1-2"
                    is-link
                    :border-intent="false"
                    :arrow-direction="showContent012 ? 'up' : 'down'"
                    @click.native="showContent012 = !showContent012"></cell>
                    <template v-if="showContent012">
                        <cell-box class="sub-item" is-link link="">用例分析</cell-box>
                        <cell-box class="sub-item" is-link link="">界面原型</cell-box>
                        <cell-box class="sub-item" is-link link="">总成绩</cell-box>
                    </template>

        </template>

      <cell
      title="第二轮"
      is-link
      :border-intent="false"
      :arrow-direction="showContent002 ? 'up' : 'down'"
      @click.native="showContent002 = !showContent002"></cell>

        <template v-if="showContent002">


        </template>

    </group>
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
import {XHeader,Cell,CellBox,Group,TransferDom,Popup} from 'vux'
export default {
  directives:{
    TransferDom
  },
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,Popup
    },
    data(){
        return{
            showContent001: false,
            showContent002: false,
            showContent011: false,
            showContent012: false,
            show:false,
        }   
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        back:function(){
            this.$router.push('/mobile/teacher')
        },
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
