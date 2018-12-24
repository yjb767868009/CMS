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
  </div>
</template>

<script>
import {XHeader,Cell,CellBox,Group} from 'vux'
export default {
    components:{
        XHeader,
        Cell,
        CellBox,
        Group
    },
    data(){
        return{
            showContent001: false,
            showContent002: false,
            showContent011: false,
            showContent012: false
        }   
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        back:function(){
            this.$router.push('/mobile/teacher')
        }
    }
}
</script>
