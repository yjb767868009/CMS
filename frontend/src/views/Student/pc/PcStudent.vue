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
        <el-menu-item index="2">查看成绩</el-menu-item>
      </el-menu>

      <!-- 讨论课 -->
      <template v-if="showSeminarCard&&!showSeminarFinished&&!showSeminarOngoing&&!showSeminarUnstarted">
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

      <!-- 未开始 报名 -->
      <template v-if="showSeminarCard&&showSeminarUnstarted">
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
              <template v-if="!is_signed[0]">
                <el-col :span="5">&emsp;</el-col>
                <el-col :span="4"><el-button size="small" @click="signSeminar(0)">报名</el-button></el-col>
              </template>
              <template v-if="is_signed[0]">
                <el-col :span="5">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[0].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">上传</el-button>
                  </el-upload>
                </el-col>
                <el-col :span="4"><el-button size="small" type="info" @click="cancelSignSeminar(0)">取消报名</el-button></el-col>
              </template>
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
              <template v-if="!is_signed[1]">
                <el-col :span="5">&emsp;</el-col>
                <el-col :span="4"><el-button size="small" @click="signSeminar(1)">报名</el-button></el-col>
              </template>
              <template v-if="is_signed[1]">
                <el-col :span="5">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[1].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">上传</el-button>
                  </el-upload>
                </el-col>
                <el-col :span="4"><el-button size="small" type="info" @click="cancelSignSeminar(1)">取消报名</el-button></el-col>
              </template>
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
              <template v-if="!is_signed[2]">
                <el-col :span="5">&emsp;</el-col>
                <el-col :span="4"><el-button size="small" @click="signSeminar(2)">报名</el-button></el-col>
              </template>
              <template v-if="is_signed[2]">
                <el-col :span="5">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[2].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">上传</el-button>
                  </el-upload>
                </el-col>
                <el-col :span="4"><el-button size="small" type="info" @click="cancelSignSeminar(2)">取消报名</el-button></el-col>
              </template>
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
              <template v-if="!is_signed[3]">
                <el-col :span="5">&emsp;</el-col>
                <el-col :span="4"><el-button size="small" @click="signSeminar(3)">报名</el-button></el-col>
              </template>
              <template v-if="is_signed[3]">
                <el-col :span="5">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[3].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">上传</el-button>
                  </el-upload>
                </el-col>
                <el-col :span="4"><el-button size="small" type="info" @click="cancelSignSeminar(3)">取消报名</el-button></el-col>
              </template>
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
              <template v-if="!is_signed[4]">
                <el-col :span="5">&emsp;</el-col>
                <el-col :span="4"><el-button size="small" @click="signSeminar(4)">报名</el-button></el-col>
              </template>
              <template v-if="is_signed[4]">
                <el-col :span="5">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[4].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">上传</el-button>
                  </el-upload>
                </el-col>
                <el-col :span="4"><el-button size="small" type="info" @click="cancelSignSeminar(4)">取消报名</el-button></el-col>
              </template>
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
              <template v-if="!is_signed[5]">
                <el-col :span="5">&emsp;</el-col>
                <el-col :span="4"><el-button size="small" @click="signSeminar(5)">报名</el-button></el-col>
              </template>
              <template v-if="is_signed[5]">
                <el-col :span="5">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[5].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">上传</el-button>
                  </el-upload>
                </el-col>
                <el-col :span="4"><el-button size="small" type="info" @click="cancelSignSeminar(5)">取消报名</el-button></el-col>
              </template>
            </template>

          </el-row>

        </el-card>
      </template>

      <!-- 进行中 -->
      <template v-if="showSeminarCard&&showSeminarOngoing">
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
              <el-col :span="4">
                <template v-if="is_signed[0]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[0].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[0]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>
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
              <el-col :span="4">
                <template v-if="is_signed[1]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[1].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[1]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>
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
              <el-col :span="4">
                <template v-if="is_signed[2]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[2].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[2]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>
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
              <el-col :span="4">
                <template v-if="is_signed[3]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[3].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[3]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>
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
              <el-col :span="4">
                <template v-if="is_signed[4]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[4].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[4]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>
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
              <el-col :span="4">
                <template v-if="is_signed[5]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[5].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[5]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>
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
      <template v-if="showSeminarCard&&showSeminarFinished">
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
              <el-col :span="5">{{AttendancesForShow[0].reportFile}}</el-col>
              </template>

              <template v-if="!AttendancesForShow[0].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>

              <el-col :span="4">
                <template v-if="is_signed[0]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[0].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[0]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>

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
              <el-col :span="5">{{AttendancesForShow[1].reportFile}}</el-col>
              </template>

              <template v-if="!AttendancesForShow[1].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>

              <el-col :span="4">
                <template v-if="is_signed[1]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[1].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[1]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>

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
              <el-col :span="5">{{AttendancesForShow[2].reportFile}}</el-col>
              </template>

              <template v-if="!AttendancesForShow[2].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>

              <el-col :span="4">
                <template v-if="is_signed[2]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[2].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[2]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>

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
              <el-col :span="5">{{AttendancesForShow[3].reportFile}}</el-col>
              </template>

              <template v-if="!AttendancesForShow[3].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>

              <el-col :span="4">
                <template v-if="is_signed[3]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[3].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[3]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>

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
              <el-col :span="5">{{AttendancesForShow[4].reportFile}}</el-col>
              </template>

              <template v-if="!AttendancesForShow[4].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>

              <el-col :span="4">
                <template v-if="is_signed[4]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[4].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[4]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>

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
              <el-col :span="5">{{AttendancesForShow[5].reportFile}}</el-col>
              </template>

              <template v-if="!AttendancesForShow[5].reportFile">
              <el-col :span="5"><div >未上传</div></el-col>
              </template>

              <el-col :span="4">
                <template v-if="is_signed[5]">
                  <el-upload 
                  :action="uploadFileURL+'attendance/'+AttendancesForShow[5].attendanceId+'/powerpoint'"
                  :headers="{'Authorization':'Bearer '+this.$store.state.token}"
                  :with-credentials="true">
                  <el-button size="small" type="primary">重新上传</el-button>
                  </el-upload>
                </template>
                <template v-if="!is_signed[5]">
                  <el-col :span="4"><div>&emsp;</div></el-col>
                </template>
              </el-col>

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


      <!-- 导出成绩 -->
      <template v-if="showScoreCard">
        <el-card>
          <div slot="header">
            <span>{{currentCourse.courseName+' 查看成绩'}}</span>
          </div>

          <el-table style="width:100%">
            <el-table-column label="轮数"></el-table-column>
            <el-table-column label="参与讨论课"></el-table-column>
            <el-table-column label="展示成绩"></el-table-column>
            <el-table-column label="提问成绩"></el-table-column>
            <el-table-column label="报告成绩"></el-table-column>
            <el-table-column label="总成绩"></el-table-column>

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
            "courseId": 16,
            "courseName": "OOAD"
          },
          {
            "courseId": 17,
            "courseName": "J2EE"
          },
          {
            "courseId": 18,
            "courseName": "SE"
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
                  "klassSeminarId": 22,
                  "klass": {
                    "klassId": 28,
                    "grade": 2016,
                    "klassSerial": 1,
                    "name": "2016-1"
                  },
                  "seminar": {
                    "seminarId": 10,
                    "topic": "用例分析"
                  },
                  "reportDDL": "2018-09-28T05:00:00.000+0000",
                  "status": 1
                }]
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
                  "klassSeminarId": 37,
                  "klass": {
                    "klassId": 28,
                    "grade": 2016,
                    "klassSerial": 1,
                    "name": "2016-1"
                  },
                  "seminar": {
                    "seminarId": 16,
                    "topic": "业务流程分析"
                  },
                  "reportDDL": "2018-10-12T05:00:00.000+0000",
                  "status": 0
                }]
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
                  "klassSeminarId": 27,
                  "klass": {
                    "klassId": 28,
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
                }]
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
                  "klassSeminarId": 32,
                  "klass": {
                    "klassId": 28,
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
                }]
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
                  "klassSeminarId": 10,
                  "klass": {
                    "klassId": 28,
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
                }]
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
                  "klassSeminarId": 13,
                  "klass": {
                    "klassId": 28,
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
                }]
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
                  "klassSeminarId": 16,
                  "klass": {
                    "klassId": 28,
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
                }]
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
                  "klassSeminarId": 19,
                  "klass": {
                    "klassId": 28,
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
                }]
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
        AttendancesForShow:[null,null,null,null,null,null],
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
          {
            "attendanceId": 3,
            "team": {
              "teamId": 20,
              "leader": {
                "studentId": 129,
                "name": "邱学良"
              },
              "teamName": "超励志小组"
            },
            "teamOrder": 3,
            "present": false
          },
          {
            "attendanceId": 4,
            "team": {
              "teamId": 7,
              "leader": {
                "studentId": 156,
                "name": "朱演演"
              },
              "teamName": "FLAG"
            },
            "teamOrder": 4,
            "present": false
          },
          {
            "attendanceId": 5,
            "team": {
              "teamId": 6,
              "leader": {
                "studentId": 122,
                "name": "林晓明"
              },
              "teamName": "Intellij"
            },
            "teamOrder": 5,
            "present": false
          },
          {
            "attendanceId": 6,
            "team": {
              "teamId": 14,
              "leader": {
                "studentId": 126,
                "name": "马子晋"
              },
              "teamName": "Flyway"
            },
            "teamOrder": 6,
            "present": false
          }
        ],
        lookingSeminar: false,
        chooseCourses: true,
        showSeminarCard: false,
        showScoreCard: false,
        showSeminarOngoing: false,
        showSeminarUnstarted: false,
        showSeminarFinished: false,
        is_signed:[false,false,false,false,false,false],
        is_join:true,
        currentCourse: '',
        currentSeminar: '',
        uploadFileURL:'http://localhost:8000/'
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
        if (index === '1') {
          //讨论课
          // this.$axios.get('/course/'+this.currentCourse.courseId+'/round')
          // .then((response)=>{
          //     this.rounds=response.data
          // })
          this.showScoreCard = false
          this.showSeminarCard = true
          this.showSeminarOngoing = false
          this.showSeminarUnstarted = false
          this.showSeminarFinished = false
        } else if (index === '2') {
          //查看成绩
          this.showScoreCard = true
          this.showSeminarCard = false
          this.showSeminarOngoing = false
          this.showSeminarUnstarted = false
          this.showSeminarFinished = false
        }
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
            if(response.data.message==='1'||response.data.message==='2'||response.data.message==='3'||response.data.message==='4'||response.data.message==='5'||response.data.message==='6'){
              //当前用户已经参加了该讨论课
              this.is_join=response.data.
              this.$set(this.is_signed,parseInt(this.is_join),true)
            }else if(response.data.message==='other'){
              //当前用户还没有参与讨论课
              this.is_join='0'
            }
        })

        //TODO:用户已报名的情况
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
          this.showSeminarUnstarted = true
          this.showSeminarOngoing = false
          this.showSeminarFinished = false
        } else if (seminar.klassSeminars[0].status === 1) {
          //正在进行
          this.showSeminarUnstarted = false
          this.showSeminarOngoing = true
          this.showSeminarFinished = false
        } else if (seminar.klassSeminars[0].status === 2) {
          //已结束
          this.showSeminarUnstarted = false
          this.showSeminarOngoing = false
          this.showSeminarFinished = true
        } else if (seminar.klassSeminars[0].status === 3) {
          console.log('讨论课暂停中')
        } else {
          console.log('wrong status')
        }
      },
      signSeminar:function(index){
        console.log('sign:',index)
        this.$set(this.is_signed,index,true)
      },
      cancelSignSeminar:function(index){
        console.log('cancel:',index)
        this.$set(this.is_signed,index,false)
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
