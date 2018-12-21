<template>
  <div class="student" style="height:800px;background:#eee;">
    <x-header
      title="讨论课PPT"
      style="height:60px;padding-top:12px;font-size:20px"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>

    <div
      v-for="(item,index) in group"
      :class="{'even':index%2!==1,'odd':index%2===1}"
      :key="item.name"
    >
      <cell primary="content" :title="item.name" value-align="left">
        <div style="padding-left:40px;color:#005AB5;">&emsp;&emsp;&emsp;{{item.order}}</div>
      </cell>
    </div>

    <div v-transfer-dom>
      <popup v-model="show" height="15%">
        <div>
          <cell value-align="left" title>
            <img
              slot="icon"
              src="@/assets/man.png"
              style="display:block;margin-right:10px;"
              width="30px"
              height="30px"
            >
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="StudentInfo">个人页</div>
          </cell>
          <cell value-align="left" title>
            <img
              slot="icon"
              src="@/assets/book.png"
              style="display:block;margin-right:10px;"
              width="30px"
              height="30px"
            >
            <div style="padding-left:110px;font-size:1.3em;color:#000" @click="running">讨论课</div>
          </cell>
        </div>
      </popup>
    </div>
  </div>
</template>

<style>
.vux-header-back {
  padding: 9px;
  font-size: 1.1em;
}
.left-arrow {
  margin-top: 10px;
}
.vux-header-more {
  padding-top: 9px;
}

div[class*="odd"] {
  font-size: 18px;
  background: #fff;
}
div[class*="even"] {
  font-size: 18px;
  background: #eee;
}
</style>


<script>
import axios from "axios";
import { XHeader, XButton, Group, Cell, TransferDom, Popup } from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XHeader,
    XButton,
    Group,
    Cell,
    Popup
  },
  data() {
    return {
      show: false,
      group: [
        {
          name: "第一组",
          order: "1-1"
        },
        {
          name: "第二组",
          order: "1-2"
        },{
          name: "第一组",
          order: "1-1"
        },
        {
          name: "第二组",
          order: "1-2"
        },{
          name: "第一组",
          order: "1-1"
        },
        {
          name: "第二组",
          order: "1-2"
        },
      ]
    };
  },
  methods: {
    edit: function() {
      this.$router.push("/StudentInfo");
    },
    running: function() {
      this.$router.push("/mobile/Student/studentSeminarList");
    },
    StudentInfo: function() {
      this.$router.push("/mobile/student/studentInfo");
    }
  }
};
</script>