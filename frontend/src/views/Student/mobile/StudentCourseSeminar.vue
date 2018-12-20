<template>
  <div class="student" style="height:20px;background:#fff">
    <x-header
      v-bind:title="this.$store.state.currentCourse.name"
      style="height:60px;padding-top:12px"
      :right-options="{showMore: true}"
      @on-click-more="show=!show"
    ></x-header>

    <group>
      <cell
        v-for="round in rounds"
        :key="round.id"
        is-link
        :border-intent="false"
        :arrow-direction="showContent[''+round.id]?'down':'up'"
        @click.native="click(round.id)"
        value-align="left"
      >
        <span>第{{round.order}}轮</span>
      </cell>
      <template v-if="showContent">

      </template>

    </group>

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
.vux-header-more {
  padding-top: 9px;
}
.weui-cells {
  color: #000;
}
</style>

<script>
import axios from "axios";
import { XHeader, Cell, CellBox, TransferDom, Popup, Group } from "vux";
export default {
  directives: {
    TransferDom
  },
  components: {
    XHeader,
    Cell,
    CellBox,
    Popup,
    Group
  },
  data() {
    return {
      rounds: [
        {
          id: 1,
          order: 2
        },
        {
          id: 2,
          order: 3
        }
      ],
      seminars: [
        {
          id: 45,
          topic: "业务流程分析",
          order: 2
        }
      ],
      show: false,
      showContent:{},
      showContent1:false,
    };
  },
  mounted: function() {
    // this.$axios
    //   .get("/course/" + this.$store.state.currentCourse.id + "/round")
    //   .then(response => {
    //     this.rounds = respnose.data;
    //   });
    // for (var i = 0; i < this.rounds.length; i++) {
    //     this.$axios.get("/round/"+this.rounds[i].id+'/seminar').then(response=>{
    //         this.rounds[i].seminars=response.data
    //     })
    // }
    for(var i=0;i<this.rounds.length;i++){
        this.rounds[i].seminar=this.seminars
    }
    console.log(this.rounds[0].id)
    this.showContent[''+this.rounds[0].id]=false
    this.showContent[''+this.rounds[1].id]=false
    console.log(this.showContent)
  },
  methods: {
    click: function(value) {
      console.log(value);
      this.showContent[''+value]=!this.showContent[''+value]
      console.log(this.showContent)
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
