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


      <!-- 未开始-->
      <template v-if="showSeminarUnstarted">
        <el-card>
          <div slot="header">
            <span>{{this.currentCourse.courseName+' '+this.currentSeminar.topic}}</span>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>主题:{{this.currentSeminar.topic}}</el-row>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>报名起止时间:{{''+this.currentSeminar.signStartTime.slice(0,10)+'~'+this.currentSeminar.signEndTime.slice(0,10)}}</el-row>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>内容:{{this.currentSeminar.introduction}}</el-row>
          </div>
          
          <el-row style="margin-bottom:18px">已经报名小组</el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >展示次序</div></el-col>
            <el-col :span="5"><div >小组序号</div></el-col>
            <el-col :span="5"><div >组长</div></el-col>
            <el-col :span="5"><div >展示材料</div></el-col>
          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >1</div></el-col>

            <template v-if="AttendancesForShow[0]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[0].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[0].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[0].reportFile">
              <el-col :span="5"><div >已上传</div></el-col>
              </template>
              <template v-if="!AttendancesForShow[0].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
              <el-col :span="4"></el-col>
            </template>

            <template v-if="AttendancesForShow[0]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
            </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >2</div></el-col>

            <template v-if="AttendancesForShow[1]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[1].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[1].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[1].reportFile">
              <el-col :span="5"><div >已上传</div></el-col>
              </template>
              <template v-if="!AttendancesForShow[1].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
              <el-col :span="4"></el-col>
            </template>

            <template v-if="AttendancesForShow[1]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
            </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >3</div></el-col>

            <template v-if="AttendancesForShow[2]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[2].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[2].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[2].reportFile">
              <el-col :span="5"><div >已上传</div></el-col>
              </template>
              <template v-if="!AttendancesForShow[2].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
              <el-col :span="4"></el-col>
            </template>

            <template v-if="AttendancesForShow[2]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
            </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >4</div></el-col>

            <template v-if="AttendancesForShow[3]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[3].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[3].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[3].reportFile">
              <el-col :span="5"><div >已上传</div></el-col>
              </template>
              <template v-if="!AttendancesForShow[3].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
              <el-col :span="4"></el-col>
            </template>

            <template v-if="AttendancesForShow[3]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
            </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >5</div></el-col>

            <template v-if="AttendancesForShow[4]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[4].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[4].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[4].reportFile">
              <el-col :span="5"><div >已上传</div></el-col>
              </template>
              <template v-if="!AttendancesForShow[4].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
              <el-col :span="4"></el-col>
            </template>

            <template v-if="AttendancesForShow[4]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
            </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >6</div></el-col>

            <template v-if="AttendancesForShow[5]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[5].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[5].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[5].reportFile">
              <el-col :span="5"><div >已上传</div></el-col>
              </template>
              <template v-if="!AttendancesForShow[5].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
              <el-col :span="4"></el-col>
            </template>

            <template v-if="AttendancesForShow[5]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
            </template>

          </el-row>

        </el-card>
      </template>

      <!-- 进行中 -->
      <template v-if="showSeminarOngoing">
        <el-card>
          <div slot="header">
            <span>{{this.currentCourse.courseName+' '+this.currentSeminar.topic}}</span>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>主题:{{this.currentSeminar.topic}}</el-row>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>报名起止时间:{{''+this.currentSeminar.signStartTime.slice(0,10)+'~'+this.currentSeminar.signEndTime.slice(0,10)}}</el-row>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>内容:{{this.currentSeminar.introduction}}</el-row>
          </div>
          
          <el-row style="margin-bottom:18px">已经报名小组</el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >展示次序</div></el-col>
            <el-col :span="5"><div >小组序号</div></el-col>
            <el-col :span="5"><div >组长</div></el-col>
            <el-col :span="5"><div >展示材料</div></el-col>
          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >1</div></el-col>

            <template v-if="AttendancesForShow[0]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[0].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[0].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[0].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[0].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[0].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[0]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >2</div></el-col>

            <template v-if="AttendancesForShow[1]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[1].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[1].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[1].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[1].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[1].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[1]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>
          
          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >3</div></el-col>

            <template v-if="AttendancesForShow[2]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[2].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[2].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[2].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[2].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[2].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[2]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >4</div></el-col>

            <template v-if="AttendancesForShow[3]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[3].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[3].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[3].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[3].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[3].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[3]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >5</div></el-col>

            <template v-if="AttendancesForShow[4]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[4].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[4].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[4].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[4].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[4].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[4]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >6</div></el-col>

            <template v-if="AttendancesForShow[5]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[5].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[5].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[5].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[5].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[5].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[5]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

        </el-card>
      </template>

      <!-- 已结束 -->
      <template v-if="showSeminarFinished">
        <el-card>
          <div slot="header">
            <span>{{this.currentCourse.courseName+' '+this.currentSeminar.topic}}</span>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>主题:{{this.currentSeminar.topic}}</el-row>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>报名起止时间:{{''+this.currentSeminar.signStartTime.slice(0,10)+'~'+this.currentSeminar.signEndTime.slice(0,10)}}</el-row>
            <div style="width:100%;height:1px; background:#E0E0E0;margin-bottom:18px"></div>
            <el-row>内容:{{this.currentSeminar.introduction}}</el-row>
          </div>
          
          <el-row style="margin-bottom:18px">已经报名小组</el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >展示次序</div></el-col>
            <el-col :span="5"><div >小组序号</div></el-col>
            <el-col :span="5"><div >组长</div></el-col>
            <el-col :span="5"><div >展示材料</div></el-col>
          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >1</div></el-col>

            <template v-if="AttendancesForShow[0]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[0].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[0].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[0].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[0].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[0].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[0]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >2</div></el-col>

            <template v-if="AttendancesForShow[1]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[1].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[1].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[1].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[1].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[1].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[1]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>
          
          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >3</div></el-col>

            <template v-if="AttendancesForShow[2]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[2].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[2].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[2].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[2].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[2].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[2]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >4</div></el-col>

            <template v-if="AttendancesForShow[3]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[3].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[3].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[3].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[3].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[3].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[3]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >5</div></el-col>

            <template v-if="AttendancesForShow[4]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[4].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[4].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[4].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[4].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[4].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[4]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

          <el-row style="margin-bottom:18px">
            <el-col :span="5"><div >6</div></el-col>

            <template v-if="AttendancesForShow[5]">
              <!-- 已经被报名 -->
              <el-col :span="5"><div >{{AttendancesForShow[5].team.teamId}}</div></el-col>
              <el-col :span="5"><div >{{AttendancesForShow[5].team.leader.name}}</div></el-col>
              <template v-if="AttendancesForShow[5].reportFile">
              <el-col :span="5">
                <el-button size="small" type="primay">
                  <a :href="'/attendence/'+AttendancesForshow[5].attendanceId+'/powerpoint'">下载</a></el-button>
                </el-col>
              </template>
              <template v-if="!AttendancesForShow[5].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>
            </template>

            <template v-if="AttendancesForShow[5]===null">
              <!-- 还没有报名 -->
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="5"><div >&emsp;</div></el-col>
              <el-col :span="4"><div >&emsp;</div></el-col>
              </template>

          </el-row>

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
          <el-select v-model="selectedOrder" @change="scoreRoundChange" placeholder="请选择">
            <el-option v-for="round in rounds" :key="round.roundId" :label="round.order" :value="round.order">
            </el-option>
          </el-select>
          <div style="width:100%;height:1px; background:#E0E0E0;margin-top:18px;margin-bottom:18px"></div>
          
          <el-table
          :data="scoreTableData[selectedOrder-1]"
          style="width:100%"
          @expand-change="expandChange"
          >
          <el-table-column type="expand">
            <template slot-scope="props">
              
                <el-table :data="props.row.seminarScore">
                  <el-table-column label="参与讨论课" prop="seminarName"></el-table-column>
                  <el-table-column label="展示分数" prop="presentationScore"></el-table-column>
                  <el-table-column label="报告分数" prop="reportScore"></el-table-column>
                  <el-table-column label="提问分数" prop="questionScore"></el-table-column>
                  <el-table-column label="该次总分" prop="totalScore"></el-table-column>
                </el-table>
                
            </template>
          </el-table-column>

            

            <el-table-column
            label="小组名称"
            prop="teamName"
            ></el-table-column>
            <el-table-column
            label="小组总分"
            prop="teamScore"
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
        ],
        klasses: [{
            "grade": 2016,
            "klassSerial": 1,
            "klassId": 12,
            "klassTime": "上午1,2节",
            "klassPlace": "海韵",
            "name": "2016-1"
          },
        ],
        Attendances: [
          {
            "attendanceId": 2,
            "team": {
              "teamId": 21,
              "leader": {
                "studentId": 141,
                "name": "吴悠"
              },
              "teamName": "晚晚鸟"
            },
            "teamOrder": 2,
            "present": false
          },
        ],
        lookingSeminar: false,
        chooseCourses: true,
        showSeminarCard: false,
        showStudentCard: false,
        showScoreCard: false,
        showSeminarOngoing: false,
        showSeminarUnstarted: false,
        showSeminarFinished: false,
        currentCourse: '',
        fileList: {},
        selectedOrder:'',
        is_signed:[false,false,false,false,false,false],
        AttendancesForShow:[null,null,null,null,null,null],
        seminarScore:[],
        scoreTableData:[[{
          score:'',
          seminarName:'',
          presentationScore:'',
          questionScore:'',
          reportScore:'',
          roundScore:'',
        },{

        },],],
      }
    },
    mounted: function () {
      this.$axios.get('/course')
        .then((response) => {
            this.courses=response.data.courses
        })
    },
    methods: {
      logOut: function () {
        this.$store.state.token = ''
        this.$router.push('/')
      },
      clickCourse: function (course) {
        this.currentCourse = course
        this.$axios.get('/course/'+this.currentCourse.courseId+'/round')
        .then((response)=>{
            this.rounds=response.data
        })
        this.chooseCourses = false
        this.showSeminarCard = true
        this.lookingSeminar = true
      },
      backToCourse: function () {
        this.chooseCourses = true
        this.lookingSeminar = false
      },
      handleSelect: function (index) {
        if (index === '1') {
          //讨论课
          this.$axios.get('/course/'+this.currentCourse.courseId+'/round')
          .then((response)=>{
              this.rounds=response.data
              this.showScoreCard=false,
              this.showSeminarOngoing= false,
              this.showSeminarUnstarted= false,
              this.showSeminarFinished= false,
              this.showStudentCard = false
              this.showScoreCard = false
              this.showSeminarCard = true
          })
          
        } else if (index === '2') {
          //导入学生名单
          this.$axios.get('/course/'+this.currentCourse.courseId+'/class')
          .then((response)=>{
              this.klasses=response.data
              this.showScoreCard=false,
              this.showSeminarOngoing= false,
              this.showSeminarUnstarted= false,
              this.showSeminarFinished= false,
              this.showStudentCard = true
              this.showScoreCard = false
              this.showSeminarCard = false
          })
          
        } else if (index === '3') {
          //导出成绩
          this.$axios.get('/course/'+this.currentCourse.courseId+'/round')
          .then((response)=>{
            this.rounds=response.data
            this.scoreTableData=[]
            for(var i=0;i<this.rounds.length;i++){
              this.scoreTableData.push([])
            }

            var promiseAll = this.rounds.map((round)=>{
              return this.$axios.get('/course/'+this.currentCourse.courseId+'/round/'+round.roundId+'/teamscore')
            })

            this.$axios.all(promiseAll).then((resAll)=>{
              resAll.forEach((res,i)=>{
                this.scoreTableData[i]=res.data.map((teamScore)=>{
                  return {
                    teamName:teamScore.team.teamName,
                    teamScore:teamScore.score,
                    teamId:teamScore.team.teamId
                  }
                })
              })
            })
            
          })

          this.showScoreCard=false,
          this.showSeminarOngoing= false,
          this.showSeminarUnstarted= false,
          this.showSeminarFinished= false,
          this.showStudentCard = false
          this.showScoreCard = true
          this.showSeminarCard = false
        }
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
      
      clickSeminar: function (seminar) {
        console.log(seminar.topic)
        console.log(seminar.klassSeminars[0].status)
        for(var i=0;i<this.is_signed.length;i++){
          this.$set(this.is_signed,i,false)
        }
        this.currentSeminar = seminar
        this.$axios.get('/klassseminar/'+this.currentSeminar.klassSeminars[0].klassSeminarId+'/attendance')
        .then((response)=>{
            this.Attendances=response.data.attendance
            this.AttendancesForShow=[null,null,null,null,null,null]
            for (var i=1;i<this.AttendancesForShow.length+1;i++){
              for(var j=0;j<this.Attendances.length;j++){
                if(this.Attendances[j].teamOrder===i){
                  this.AttendancesForShow[i-1]=this.Attendances[j]
                }
              }
            }
            if (seminar.klassSeminars[0].status === 0) {
              //未开始
              this.showSeminarCard=false
              this.showSeminarUnstarted = true
              this.showSeminarOngoing = false
              this.showSeminarFinished = false
            } else if (seminar.klassSeminars[0].status === 1) {
              //正在进行
              this.showSeminarCard=false
              this.showSeminarUnstarted = false
              this.showSeminarOngoing = true
              this.showSeminarFinished = false
            } else if (seminar.klassSeminars[0].status === 2) {
              //已结束
              this.showSeminarCard=false
              this.showSeminarUnstarted = false
              this.showSeminarOngoing = false
              this.showSeminarFinished = true
            } else if (seminar.klassSeminars[0].status === 3) {
              console.log('讨论课暂停中')
            } else {
              console.log('wrong status')
            }
        }).catch((error)=>{
          console.log(error)
        })
      },

      scoreRoundChange(value){
        console.log(value)
        console.log(this.scoreTableData[parseInt(value)-1])
      },

      expandChange(row,expandedRows){
        this.$set(row,'seminarScore',[])

        var promiseAll = this.rounds[this.selectedOrder-1].seminars.map((seminar)=>{
          return this.$axios.get('/seminar/'+seminar.seminarId+'/team/'+row.teamId+'/seminarscore')
        })

        this.$axios.all(promiseAll).then((resAll)=>{
          resAll.forEach((res,i)=>{
            this.$set(row.seminarScore,i,{
                seminarName:this.rounds[this.selectedOrder-1].seminars[i].topic,
                presentationScore:res.data.presentationScore,
                questionScore:res.data.questionScore,
                reportScore:res.data.reportScore,
            })
          })
        })
        

        console.log(row,expandedRows)
      }
      
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
