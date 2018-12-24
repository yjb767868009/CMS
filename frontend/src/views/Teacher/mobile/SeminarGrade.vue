<template>
  <div class="login" title="2016-(1)">
    <x-header title="OOAD-书面报告" style="height:60px;padding-top:12px" :left-options="{showBack:false}" :right-options="{showMore: true}" @on-click-more="show=!show">
    </x-header>

      <x-table :cell-bordered="false" style="background-color:#fff;">
        <thead>
          <tr>
            <th></th>
            <th>展示</th>
            <th>提问</th>
            <th>书面报告</th>
            <th style="color:green">总分</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1-1</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
          </tr>
          <tr>
            <td>1-2</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
            <td>5.0</td>
          </tr>
        </tbody>
      </x-table>



    <x-button @click="comfirm" type="primary" style="margin-top:100px;color:#fff">确认</x-button>
    <x-button @click="modify" type="primary" plain style="margin-top:10px">修改</x-button>
  </div>
</template>

<script>
import {XHeader,Cell,Group,CellBox,XButton,XTable} from 'vux'
export default {
    components:{
        XHeader,
        Cell,
        CellBox,
        Group,
        XButton,
        XTable
    },
    data(){
        return{

        } 
    },
    methods: {
        onClick () {
            console.log('on click')
        },
        back:function(){
            this.$router.push('/mobile/teacher/seminar')
        },
        more:function(){
        },
        comfirm:function(){

        },
        modify:function(){

        }
    }
}
</script>
