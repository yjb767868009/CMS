<template>
  <el-container>
    <!-- 选择课程 -->
    <template v-if="chooseCourses">
      <el-header>
        个人信息综合管理平台
        <i class="el-icon-error" @click="logOut" style="position:absolute;top:30px;right:30px">退出</i>
      </el-header>
      <el-card style="margin-top:30px;margin-left:10%;margin-right:10%;width:80%" shadow="hover">
        <div slot="header" style="display:table">
          <span>选择课程</span>
        </div>
        <div v-for="course in courses" :key="course.courseId" style="margin-bottom:18px">
          <el-row>
            <el-button type="text" style="color:rgb(0,0,0);">{{course.courseName}}</el-button>
            <el-button @click="clickCourse(course)" type="text" style="float: right">进入</el-button>
          </el-row>
          <div style="width:100%;height:1px; background:#E0E0E0;"></div>
        </div>
      </el-card>
    </template>

    <!-- 查看讨论课 -->
    <template v-if="lookingSeminar">
      <el-header>
        个人信息综合管理平台
        <i class="el-icon-menu" @click="backToCourse" style="position:absolute;top:30px;right:100px">选择课程</i>
        <i class="el-icon-error" @click="logOut" style="position:absolute;top:30px;right:30px">退出</i>
      </el-header>

      <el-menu :default-active="'1'" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1">讨论课</el-menu-item>
        <el-menu-item index="2">导入学生名单</el-menu-item>
        <el-menu-item index="3">导出成绩</el-menu-item>
      </el-menu>

      <!-- 讨论课 -->
      <template v-if="showSeminarCard">
        <el-card>
          <div slot="header">
            <span>{{currentCourse.courseName+' 讨论课'}}</span>
          </div>
          <el-card v-for="round in rounds" :key="round.roundId">
            <div slot="header">
              <span>{{'第'+round.order+'轮'}}</span>
            </div>
            <div v-for="seminar in round.seminars" :key="seminar.seminarId" style="margin-bottom:18px">
              <el-row>
                <el-button type="text" style="color:rgb(0,0,0);">{{'第'+seminar.signOrder+'次讨论课————'+seminar.topic}}</el-button>
                <el-button @click="clickSeminar(seminar)" type="text" style="float: right">进入</el-button>
              </el-row>
            </div>
          </el-card>
        </el-card>
      </template>


      <!-- 导入学生名单 -->

      <template v-if="showStudentCard">
        <el-card>
          <div slot="header">
            <span>{{currentCourse.courseName+' 导入学生名单'}}</span>
          </div>
          <div v-for="klass in klasses" :key="klass.klassSerial">
            <el-row>
              <el-button type="text" style="color:rgb(0,0,0);">{{''+klass.grade+'—'+klass.klassSerial+'班'}}</el-button>
              &emsp;&emsp;
              <el-button @click="submitStudentList(klass)" type="text" style="float: right;">提交</el-button>
              <input type="file" :ref="''+klass.klassId" @change="uploadStudentList(klass,$event)" />
            </el-row>
          </div>
        </el-card>
      </template>


      <!-- 导出成绩 -->
      <template v-if="showScoreCard">
        <el-card>
          <div slot="header">
            <span>{{currentCourse.courseName+' 查看成绩'}}</span>
          </div>
          
          轮数:
          <el-select v-model="selectedOrder" placeholder="请选择">
            <el-option v-for="round in rounds" :key="round.roundId" :label="round.order" :value="round.order">
            </el-option>
          </el-select>
          <div style="width:100%;height:1px; background:#E0E0E0;margin-top:18px;margin-bottom:18px"></div>
          <el-table
          style="width:100%"
          >
          <el-table-column
          label="小组总分"
          ></el-table-column>
          <el-table-column
          label="参与讨论课"
          ></el-table-column>
          <el-table-column
          label="发言成绩"
          ></el-table-column>
          <el-table-column
          label="提问成绩"
          ></el-table-column>
          <el-table-column
          label="报告成绩"
          ></el-table-column>
          <el-table-column
          label="该次总成绩"
          ></el-table-column>

          </el-table>
          

        </el-card>
      </template>

    </template>

  </el-container>
</template>

<script>
  export default {
    data() {
      return {
        courses: [{
            "courseId": 19,
            "courseName": "SE",
            "introduction": "软件工程",
            "presentationWeight": 40,
            "reportWeight": 30,
            "questionWeight": 30,
            "teamStartTime": "2018-12-20T02:54:19.000+0000",
            "teamEndTime": "2018-12-20T02:54:21.000+0000"
          },
          {
            "courseId": 18,
            "courseName": "SE",
            "introduction": "软件工程",
            "presentationWeight": 40,
            "reportWeight": 30,
            "questionWeight": 30,
            "teamStartTime": "2018-12-20T02:54:19.000+0000",
            "teamEndTime": "2018-12-20T02:54:21.000+0000"
          }
        ],

        rounds: [{
            "roundId": 3,
            "order": 1,
            "presentationScoreType": 0,
            "reportScoreType": 0,
            "questionScoreType": 0,
            "seminars": [{
                "seminarId": 10,
                "course": {
                  "courseId": 20,
                  "courseName": ".NET"
                },
                "maxTeamNum": 6,
                "topic": "用例分析",
                "introduction": "题目为课堂管理系统的用例分析，包括分辨Actors、Use Cases划分和撰写用例。课后发言小组提交书面报告，书面报告应该是一份完整的用例设计。课后以作业方式提交设计报告，文件以组号命名，PDF格式",
                "signStartTime": "2018-09-01T16:49:42.000+0000",
                "signEndTime": "2018-09-27T05:00:00.000+0000",
                "signOrder": 1,
                "visible": true,
                "klassSeminars": [{
                    "klassSeminarId": 21,
                    "klass": {
                      "klassId": 21,
                      "grade": 2016,
                      "klassSerial": 1,
                      "name": "2016-1"
                    },
                    "seminar": {
                      "seminarId": 10,
                      "topic": "用例分析"
                    },
                    "reportDDL": "2018-09-28T05:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 22,
                    "klass": {
                      "klassId": 22,
                      "grade": 2016,
                      "klassSerial": 2,
                      "name": "2016-2"
                    },
                    "seminar": {
                      "seminarId": 10,
                      "topic": "用例分析"
                    },
                    "reportDDL": "2018-09-28T05:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 23,
                    "klass": {
                      "klassId": 23,
                      "grade": 2016,
                      "klassSerial": 3,
                      "name": "2016-3"
                    },
                    "seminar": {
                      "seminarId": 10,
                      "topic": "用例分析"
                    },
                    "reportDDL": "2018-09-28T05:00:00.000+0000",
                    "status": 0
                  }
                ]
              },
              {
                "seminarId": 16,
                "course": {
                  "courseId": 20,
                  "courseName": ".NET"
                },
                "maxTeamNum": 6,
                "topic": "业务流程分析",
                "introduction": "每个小组需要以讨论课1中的一个小组的用例设计为基础来设计业务流程。",
                "signStartTime": "2018-09-01T05:35:29.000+0000",
                "signEndTime": "2018-10-13T05:00:00.000+0000",
                "signOrder": 2,
                "visible": true,
                "klassSeminars": [{
                    "klassSeminarId": 34,
                    "klass": {
                      "klassId": 21,
                      "grade": 2016,
                      "klassSerial": 1,
                      "name": "2016-1"
                    },
                    "seminar": {
                      "seminarId": 16,
                      "topic": "业务流程分析"
                    },
                    "reportDDL": "2018-10-13T05:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 37,
                    "klass": {
                      "klassId": 22,
                      "grade": 2016,
                      "klassSerial": 2,
                      "name": "2016-2"
                    },
                    "seminar": {
                      "seminarId": 16,
                      "topic": "业务流程分析"
                    },
                    "reportDDL": "2018-10-12T05:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 38,
                    "klass": {
                      "klassId": 23,
                      "grade": 2016,
                      "klassSerial": 3,
                      "name": "2016-3"
                    },
                    "seminar": {
                      "seminarId": 16,
                      "topic": "业务流程分析"
                    },
                    "reportDDL": "2018-10-12T05:00:00.000+0000",
                    "status": 0
                  }
                ]
              }
            ]
          },
          {
            "roundId": 4,
            "order": 2,
            "presentationScoreType": 0,
            "reportScoreType": 0,
            "questionScoreType": 0,
            "seminars": [{
                "seminarId": 9,
                "course": {
                  "courseId": 20,
                  "courseName": ".NET"
                },
                "maxTeamNum": 6,
                "topic": "界面设计",
                "introduction": "界面导航图和所有界面原型设计\r\n\r\n所有小组请课前提前准备，课堂讨论每个小组15分钟\r\n\r\n课后提交书面报告，书面报告应该是一份完整界面设计说明书。课后以作业方式提交设计报告，文件以组号命名， PDF格式",
                "signStartTime": "2018-09-15T16:35:00.000+0000",
                "signEndTime": "2018-10-11T16:35:16.000+0000",
                "signOrder": 3,
                "visible": true,
                "klassSeminars": [{
                    "klassSeminarId": 26,
                    "klass": {
                      "klassId": 21,
                      "grade": 2016,
                      "klassSerial": 1,
                      "name": "2016-1"
                    },
                    "seminar": {
                      "seminarId": 9,
                      "topic": "界面设计"
                    },
                    "reportDDL": "2018-10-26T05:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 27,
                    "klass": {
                      "klassId": 22,
                      "grade": 2016,
                      "klassSerial": 2,
                      "name": "2016-2"
                    },
                    "seminar": {
                      "seminarId": 9,
                      "topic": "界面设计"
                    },
                    "reportDDL": "2018-10-26T05:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 28,
                    "klass": {
                      "klassId": 23,
                      "grade": 2016,
                      "klassSerial": 3,
                      "name": "2016-3"
                    },
                    "seminar": {
                      "seminarId": 9,
                      "topic": "界面设计"
                    },
                    "reportDDL": "2018-10-26T05:00:00.000+0000",
                    "status": 0
                  }
                ]
              },
              {
                "seminarId": 15,
                "course": {
                  "courseId": 20,
                  "courseName": ".NET"
                },
                "maxTeamNum": 6,
                "topic": "领域模型",
                "introduction": "讨论课内容为领域模型分析，包括领域模型的Package Diagrams。所有小组请课前提前准备，课堂讨论每个小组15分钟。课后提交书面报告，书面报告应该是一份完整领域模型设计。课后以作业方式提交设计报告，文件以组号命名， PDF格式。",
                "signStartTime": "2018-09-01T05:00:00.000+0000",
                "signEndTime": "2018-11-02T05:00:00.000+0000",
                "signOrder": 4,
                "visible": true,
                "klassSeminars": [{
                    "klassSeminarId": 31,
                    "klass": {
                      "klassId": 21,
                      "grade": 2016,
                      "klassSerial": 1,
                      "name": "2016-1"
                    },
                    "seminar": {
                      "seminarId": 15,
                      "topic": "领域模型"
                    },
                    "reportDDL": "2018-11-04T05:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 32,
                    "klass": {
                      "klassId": 22,
                      "grade": 2016,
                      "klassSerial": 2,
                      "name": "2016-2"
                    },
                    "seminar": {
                      "seminarId": 15,
                      "topic": "领域模型"
                    },
                    "reportDDL": "2018-11-04T05:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 33,
                    "klass": {
                      "klassId": 23,
                      "grade": 2016,
                      "klassSerial": 3,
                      "name": "2016-3"
                    },
                    "seminar": {
                      "seminarId": 15,
                      "topic": "领域模型"
                    },
                    "reportDDL": "2018-11-04T05:00:00.000+0000",
                    "status": 0
                  }
                ]
              }
            ]
          },
          {
            "roundId": 5,
            "order": 3,
            "presentationScoreType": 0,
            "reportScoreType": 0,
            "questionScoreType": 0,
            "seminars": [{
                "seminarId": 5,
                "course": {
                  "courseId": 20,
                  "courseName": ".NET"
                },
                "maxTeamNum": 7,
                "topic": "对象模型设计",
                "introduction": "对象模型设计\r\n\r\n所有小组请课前提前准备，课堂讨论每个小组15分钟\r\n\r\n课后提交书面报告，书面报告应该是一份完整对象模型和关系模型的设计说明书。课后以作业方式提交设计报告，文件以组号命名， PDF格式",
                "signStartTime": "2018-09-02T02:40:14.000+0000",
                "signEndTime": "2018-11-16T18:00:00.000+0000",
                "signOrder": 5,
                "visible": true,
                "klassSeminars": [{
                    "klassSeminarId": 9,
                    "klass": {
                      "klassId": 21,
                      "grade": 2016,
                      "klassSerial": 1,
                      "name": "2016-1"
                    },
                    "seminar": {
                      "seminarId": 5,
                      "topic": "对象模型设计"
                    },
                    "reportDDL": "2018-11-16T18:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 10,
                    "klass": {
                      "klassId": 22,
                      "grade": 2016,
                      "klassSerial": 2,
                      "name": "2016-2"
                    },
                    "seminar": {
                      "seminarId": 5,
                      "topic": "对象模型设计"
                    },
                    "reportDDL": "2018-11-16T18:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 11,
                    "klass": {
                      "klassId": 23,
                      "grade": 2016,
                      "klassSerial": 3,
                      "name": "2016-3"
                    },
                    "seminar": {
                      "seminarId": 5,
                      "topic": "对象模型设计"
                    },
                    "reportDDL": "2018-11-16T18:00:00.000+0000",
                    "status": 0
                  }
                ]
              },
              {
                "seminarId": 6,
                "course": {
                  "courseId": 20,
                  "courseName": ".NET"
                },
                "maxTeamNum": 6,
                "topic": "Controller层API设计",
                "introduction": "Controller的api的详细设计\r\n\r\n所有小组请课前提前准备，课堂讨论每个小组15分钟\r\n\r\n课后提交书面报告，书面报告应该是一份完整API设计说明书。课后以作业方式提交设计报告，文件以组号命名， PDF格式，可以提交在线链接\r\n\r\n本次讨论课供报名竞选Controller层标准组的小组报名，每个小组只能竞选一个标准组名额。\r\n\r\n不想竞选的小组请报名第五次讨论课",
                "signStartTime": "2018-09-02T02:41:36.000+0000",
                "signEndTime": "2018-11-30T18:00:00.000+0000",
                "signOrder": 6,
                "visible": true,
                "klassSeminars": [{
                    "klassSeminarId": 12,
                    "klass": {
                      "klassId": 21,
                      "grade": 2016,
                      "klassSerial": 1,
                      "name": "2016-1"
                    },
                    "seminar": {
                      "seminarId": 6,
                      "topic": "Controller层API设计"
                    },
                    "reportDDL": "2018-11-30T18:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 13,
                    "klass": {
                      "klassId": 22,
                      "grade": 2016,
                      "klassSerial": 2,
                      "name": "2016-2"
                    },
                    "seminar": {
                      "seminarId": 6,
                      "topic": "Controller层API设计"
                    },
                    "reportDDL": "2018-11-30T18:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 14,
                    "klass": {
                      "klassId": 23,
                      "grade": 2016,
                      "klassSerial": 3,
                      "name": "2016-3"
                    },
                    "seminar": {
                      "seminarId": 6,
                      "topic": "Controller层API设计"
                    },
                    "reportDDL": "2018-11-30T18:00:00.000+0000",
                    "status": 0
                  }
                ]
              }
            ]
          },
          {
            "roundId": 6,
            "order": 4,
            "presentationScoreType": 0,
            "reportScoreType": 0,
            "questionScoreType": 0,
            "seminars": [{
                "seminarId": 7,
                "course": {
                  "courseId": 20,
                  "courseName": ".NET"
                },
                "maxTeamNum": 6,
                "topic": "讨论课七",
                "introduction": "系统Controller层、Service层、DAO层和Entity的详细设计，包括Package的设计，类图，Sequence Diagram（能体现设计的部分），Deployment Diagram",
                "signStartTime": "2018-09-01T13:58:35.000+0000",
                "signEndTime": "2018-12-21T06:00:00.000+0000",
                "signOrder": 7,
                "visible": true,
                "klassSeminars": [{
                    "klassSeminarId": 15,
                    "klass": {
                      "klassId": 21,
                      "grade": 2016,
                      "klassSerial": 1,
                      "name": "2016-1"
                    },
                    "seminar": {
                      "seminarId": 7,
                      "topic": "讨论课七"
                    },
                    "reportDDL": "2018-12-21T06:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 16,
                    "klass": {
                      "klassId": 22,
                      "grade": 2016,
                      "klassSerial": 2,
                      "name": "2016-2"
                    },
                    "seminar": {
                      "seminarId": 7,
                      "topic": "讨论课七"
                    },
                    "reportDDL": "2018-12-21T06:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 17,
                    "klass": {
                      "klassId": 23,
                      "grade": 2016,
                      "klassSerial": 3,
                      "name": "2016-3"
                    },
                    "seminar": {
                      "seminarId": 7,
                      "topic": "讨论课七"
                    },
                    "reportDDL": "2018-12-21T06:00:00.000+0000",
                    "status": 0
                  }
                ]
              },
              {
                "seminarId": 8,
                "course": {
                  "courseId": 20,
                  "courseName": ".NET"
                },
                "maxTeamNum": 6,
                "topic": "详细设计",
                "introduction": "系统Controller层、Service层、DAO层和Entity的详细设计，包括Package的设计，类图，Sequence Diagram（能体现设计的部分），Deployment Diagram",
                "signStartTime": "2018-09-01T13:58:35.000+0000",
                "signEndTime": "2018-12-21T06:00:00.000+0000",
                "signOrder": 8,
                "visible": true,
                "klassSeminars": [{
                    "klassSeminarId": 18,
                    "klass": {
                      "klassId": 21,
                      "grade": 2016,
                      "klassSerial": 1,
                      "name": "2016-1"
                    },
                    "seminar": {
                      "seminarId": 8,
                      "topic": "详细设计"
                    },
                    "reportDDL": "2018-12-21T06:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 19,
                    "klass": {
                      "klassId": 22,
                      "grade": 2016,
                      "klassSerial": 2,
                      "name": "2016-2"
                    },
                    "seminar": {
                      "seminarId": 8,
                      "topic": "详细设计"
                    },
                    "reportDDL": "2018-12-21T06:00:00.000+0000",
                    "status": 0
                  },
                  {
                    "klassSeminarId": 20,
                    "klass": {
                      "klassId": 23,
                      "grade": 2016,
                      "klassSerial": 3,
                      "name": "2016-3"
                    },
                    "seminar": {
                      "seminarId": 8,
                      "topic": "详细设计"
                    },
                    "reportDDL": "2018-12-21T06:00:00.000+0000",
                    "status": 0
                  }
                ]
              }
            ]
          }
        ],
        klasses: [{
            "grade": 2016,
            "klassSerial": 1,
            "klassId": 12,
            "klassTime": "上午1,2节",
            "klassPlace": "海韵",
            "name": "2016-1"
          },
          {
            "grade": 2016,
            "klassSerial": 2,
            "klassId": 13,
            "klassTime": "上午3,4节",
            "klassPlace": "海韵",
            "name": "2016-2"
          },
          {
            "grade": 2016,
            "klassSerial": 3,
            "klassId": 14,
            "klassTime": "下午5,6节",
            "klassPlace": "海韵",
            "name": "2016-3"
          }
        ],
        lookingSeminar: false,
        chooseCourses: true,
        showSeminarCard: false,
        showStudentCard: false,
        showScoreCard: false,
        currentCourse: '',
        fileList: {},
        selectedOrder:''
      }
    },
    // mounted: function () {
    //   this.$axios.get('/course')
    //     .then((response) => {
    //         this.courses=response.data.courses
    //     })
    // },
    methods: {
      logOut: function () {
        this.$store.state.token = ''
        this.$router.push('/')
      },
      clickCourse: function (course) {
        this.currentCourse = course
        // this.$axios.get('/course/'+this.currentCourse.courseId+'/round')
        // .then((response)=>{
        //     this.rounds=response.data
        // })
        this.chooseCourses = false
        this.showSeminarCard = true
        this.lookingSeminar = true
      },
      backToCourse: function () {
        this.chooseCourses = true
        this.lookingSeminar = false
      },
      handleSelect: function (index) {
        console.log(index)
        if (index === '1') {
          //讨论课
          // this.$axios.get('/course/'+this.currentCourse.courseId+'/round')
          // .then((response)=>{
          //     this.rounds=response.data
          // })
          this.showStudentCard = false
          this.showScoreCard = false
          this.showSeminarCard = true
        } else if (index === '2') {
          //导入学生名单
          // this.$axios.get('/course/'+this.currentCourse.courseId+'/class')
          // .then((response)=>{
          //     this.klasses=response.data
          // })
          this.showStudentCard = true
          this.showScoreCard = false
          this.showSeminarCard = false
        } else if (index === '3') {
          //导出成绩
          this.showStudentCard = false
          this.showScoreCard = true
          this.showSeminarCard = false
        }
      },
      clickSeminar: function (seminar) {
        console.log(seminar.topic)
      },
      uploadStudentList: function (klass, e) {
        console.log(klass)
        console.log(e)

        this.fileList['' + klass.klassId] = e.target.files[0]

      },
      submitStudentList: function (klass) {
        console.log('uploading:', this.fileList['' + klass.klassId])
        let param = new FormData()
        param.append('file', this.fileList['' + klass.klassId])
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
        //post class/{classId}/classfile'
        this.$axios.post('/class/' + klass.klassId + '/classfile', param, config)
          .then((response) => {
            console.log(response.data)
          }).catch((error) => {
            console.log(error)
          })
      },
    }
  }

</script>

<style>
  .el-header {
    background-color: rgb(10, 47, 88);
    color: rgb(255, 255, 255);
    line-height: 60px;
    font-family: PingFang SC;
  }

  .el-aside {
    color: #333;
    font-family: PingFang SC;
  }

  .footer-main {
    font-size: 0;
    padding-top: 40px;
    /* display: inline-block; */
  }

  .footer-main .footer-main-title {
    line-height: 1;
    font-size: 22px;
    margin: 0;
  }

  .footer-main .footer-main-link {
    /* display: inline-block; */
    margin: 12px 18px 0 0;
    line-height: 1;
    font-size: 12px;
    color: #768193;
  }

</style>
