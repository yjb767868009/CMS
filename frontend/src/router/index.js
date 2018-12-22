import Vue from 'vue'


import Router from 'vue-router'

//test
import Test from '@/views/Test'

//user
import Login from '@/views/User/Login'
import MobileLogin from '@/views/User/MobileLogin'
import FindPassword from '@/views/User/FindPassword'
import ModifyPassword from '@/views/User/ModifyPassword'
import ModifyEmail from '@/views/User/ModifyEmail'

//Admin
import AdminLogin from '@/views/Admin/AdminLogin'
import AdminStudent from '@/views/Admin/AdminStudent'
import AdminTeacher from '@/views/Admin/AdminTeacher'


//Student mobile
import SeminarDetail from '@/views/Student/mobile/SeminarDetail'
import StudentActivation from '@/views/Student/mobile/StudentActivation'
import SeminarPresentation from '@/views/Student/mobile/SeminarPresentation'
import SeminarModifyRegistration from '@/views/Student/mobile/SeminarModifyRegistration'

import SeminarRegistration from '@/views/Student/mobile/SeminarRegistration'
import SeminarRegistrationModification from '@/views/Student/mobile/SeminarRegistrationModification'
import SeminarScore from '@/views/Student/mobile/SeminarScore'
import SeminarSeqFinished from '@/views/Student/mobile/SeminarSeqFinished'
import SeminarSeqFinishedStatus from '@/views/Student/mobile/SeminarSeqFinishedStatus'
import SeminarSeqRunning from '@/views/Student/mobile/SeminarSeqRunning'
import SeminarUnstartSigned from '@/views/Student/mobile/SeminarUnstartSigned'
import SeminarSigned from '@/views/Student/mobile/SeminarSigned'
import StudentCourseSeminar from '@/views/Student/mobile/StudentCourseSeminar'
import StudentInfo from '@/views/Student/mobile/StudentInfo'
import StudentSeminarList from '@/views/Student/mobile/StudentSeminarList'
import StuSetAccount from '@/views/Student/mobile/StuSetAccount'
import MyClass from '@/views/Student/mobile/MyClass'
import MyScore from '@/views/Student/mobile/MyScore'
import StudentCourseInfo from '@/views/Student/mobile/StudentCourseInfo'
import TeamFreedom from '@/views/Student/mobile/TeamFreedom'
import NewTeam from '@/views/Student/mobile/NewTeam'
import TeamLeader from '@/views/Student/mobile/TeamLeader'
import TeamMember from '@/views/Student/mobile/TeamMember'



//Teacher
import SetAccount from '@/views/Teacher/mobile/SetAccount'
import ManageCourse from '@/views/Teacher/mobile/ManageCourse'
import SetSeminar from '@/views/Teacher/mobile/SetSeminar'
import SetRound from '@/views/Teacher/mobile/SetRound'
import CreateSeminar from '@/views/Teacher/mobile/CreateSeminar'
import CreateRound from '@/views/Teacher/mobile/CreateRound'
import TeacherSeminars from '@/views/Teacher/mobile/TeacherSeminars'
import Seminar from '@/views/Teacher/mobile/Seminar'
import Report from '@/views/Teacher/mobile/Report'
import SeminarGrade from '@/views/Teacher/mobile/SeminarGrade'
import ManageSeminar from '@/views/Teacher/mobile/ManageSeminar'
import TeacherActivation from '@/views/Teacher/mobile/TeacherActivation'
import GradeList from '@/views/Teacher/mobile/GradeList'
import TeamList from '@/views/Teacher/mobile/TeamList'
import CourseInfo from '@/views/Teacher/mobile/CourseInfo'
import ClassInfo from '@/views/Teacher/mobile/ClassInfo'
import CreateClass from '@/views/Teacher/mobile/CreateClass'
import Notify from '@/views/Teacher/mobile/Notify'
import CreateCourse from '@/views/Teacher/mobile/CreateCourse'
import Share from '@/views/Teacher/mobile/Share'
import CreateShare from '@/views/Teacher/mobile/CreateShare'


Vue.use(Router)

export default new Router({
  mode: 'hash',
  routes: [
    {
      path:'/test',
      name:'Test',
      component:Test
    },

    //login
    {
      path:'/',
      redirect:'/login'
    },
    {
      path:'/login',
      name:'Login',
      component:Login,
      meta:{
        requireAuth:false
      }
    },
    {
      path: '/mobile/login',
      name: 'MobileLogin',
      component: MobileLogin,
      meta:{
        requireAuth:false
      }
    }, 
    {
      path:'/adminLogin',
      name:'AdminLogin',
      component:AdminLogin,
      meta:{
        requireAuth:false
      }
    },

    //user
    {
      path:'/mobile/findpw',
      name:'FindPassword',
      component:FindPassword,
    },
    {
      path:'/mobile/modifypw',
      name:ModifyPassword,
      component:ModifyPassword
    },
    {
      path:'/mobile/modifyEmail',
      name:'ModifyEmail',
      component:ModifyEmail
    },
    //admin
    {
      path:'/admin',
      redirect:'/adminTeacher'
    },
    {
      path:'/adminStudent',
      name:'AdminStudent',
      component:AdminStudent,
    },
    {
      path:'/adminTeacher',
      name:'AdminTeacher',
      component:AdminTeacher,
    },

    //student mobile
    {
      path:'/mobile/student/studentInfo',
      name:'StudentInfo',
      component:StudentInfo
    },
    {
      path:'/mobile/student/activation',
      name:'StudentActivation',
      component:StudentActivation
    },
    
    {
      path:'/mobile/Student/stuSetAccount',
      name:'StuSetAccount',
      component:StuSetAccount
    },
    {
      path:'/mobile/Student/myClass',
      name:'MyClass',
      component:MyClass
    },
    {
      path:'/mobile/Student/myScore',
      name:'MyScore',
      component:MyScore
    },
    {
      path:'/mobile/Student/studentCourseInfo',
      name:'StudentCourseInfo',
      component:StudentCourseInfo
    },
    {
      path:'/mobile/Student/teamFreedom',
      name:'TeamFreedom',
      component:TeamFreedom
    },
    {
      path:'/mobile/Student/teamLeader',
      name:'TeamLeader',
      component:TeamLeader
    },
    {
      path:'/mobile/Student/teamMember',
      name:'TeamMember',
      component:TeamMember
    },
    {
      path:'/mobile/Student/newTeam',
      name:'NewTeam',
      component:NewTeam
    },
    {
      path:'/mobile/Student/studentSeminarList',//2.学生讨论课
      name:'StudentSeminarList',
      component:StudentSeminarList
    },
    {
      path:'/mobile/student/course', //2-1讨论课
      name:'StudentCourseSeminar',
      component:StudentCourseSeminar
    },
    {
      path:'/mobile/student/course/seminar',//正在 未报
      name:'SeminarDetail',
      component:SeminarDetail
    },
    {
      path:'/mobile/student/course/seminar/running/presentation',//正在 未报 -》讨论课情况
      name:'SeminarPresentation',
      component:SeminarPresentation
    },
    {
      path:'/mobile/student/course/seminar/finished',//已完 未报
      name:'SeminarSeqFinished',
      component:SeminarSeqFinished
    },
    {
      path:'/mobile/student/course/seminar/finished/status',//已完 未报-》讨论课情况
      name:'SeminarSeqFinishedStatus',
      component:SeminarSeqFinishedStatus
    },
    {
      path:'/mobile/student/course/seminar/unstartedSigned',//未开始 已报
      name:'SeminarUnstartSigned',
      component:SeminarUnstartSigned
    },
    {
      path:'/mobile/student/course/seminar/unstartedSigned/modifyRegistration',//未开始 已报-》修改报名
      name:'SeminarModifyRegistration',
      component:SeminarModifyRegistration
    },
    {
      path:'/mobile/student/course/seminar/running',//正在展示
      name:'SeminarSeqRunning',
      component:SeminarSeqRunning
    },
    {
      path:'/mobile/student/course/seminar/unsigned/registration',//未开未报
      name:'SeminarRegistration',
      component:SeminarRegistration
    },
    {
      path:'/mobile/student/course/seminar/signed',
      name:'SeminarSigned', //截止和没截止
      component:SeminarSigned
    },
    {
      path:'/mobile/student/course/seminar/seminarScore',
      name:'SeminarScore',
      component:SeminarScore
    },
    {
      path:'/mobile/student/course/seminar/seminarRegistrationModification',
      name:'SeminarRegistrationModification',
      component:SeminarRegistrationModification
    },


    //teacher
    // {
    //   path: '/mobile/teacher',
    //   name: 'MobileTeacher',
    //   component: MobileTeacher
    // },
    {
      path: '/mobile/teacher/activation',
      name: 'TeacherActivation',
      component: TeacherActivation
    },
    {
      path: '/mobile/teacher/account',
      name: 'SetAccount',
      component: SetAccount
    },
    {
      path: '/mobile/teacher/course',
      name: 'ManageCourse',
      component: ManageCourse
    },
    {
      path:'/mobile/teacher/setseminar',
      name:'SetSeminar',
      component:SetSeminar
    },
    {
      path:'/mobile/teacher/setround',
      name:'SetRound',
      component:SetRound
    },
    {
      path:'/mobile/teacher/createround',
      name:'CreateRound',
      component:CreateRound
    },
    {
      path:'/mobile/teacher/createseminar',
      name:'CreateSeminar',
      component:CreateSeminar
    },
    {
      path:'/mobile/teacher/seminars',
      name:'TeacherSeminars',
      component:TeacherSeminars
    },
    {
      path:'/mobile/teacher/seminar',
      name:'Seminar',
      component:Seminar
    },
    {
      path:'/mobile/teacher/report',
      name:'Report',
      component:Report
    },
    {
      path:'/mobile/teacher/seminargrade',
      name:'SeminarGrade',
      component:SeminarGrade
    },
    {
      path:'/mobile/teacher/manageseminar',
      name:'ManageSeminar',
      component:ManageSeminar
    },
    {
      path:'/mobile/teacher/teamlist',
      name:'TeamList',
      component:TeamList
    },
    {
      path:'/mobile/teacher/gradelist',
      name:'GradeList',
      component:GradeList
    },
    {
      path:'/mobile/teacher/courseinfo',
      name:'CourseInfo',
      component:CourseInfo
    },
    {
      path:'/mobile/teacher/classinfo',
      name:'ClassInfo',
      component:ClassInfo
    },
    {
      path:'/mobile/teacher/createclass',
      name:'CreateClass',
      component:CreateClass
    },
    {
      path:'/mobile/teacher/notify',
      name:'Notify',
      component:Notify
    },
    {
      path:'/mobile/teacher/createcourse',
      name:'CreateCourse',
      component:CreateCourse
    },
    {
      path:'/mobile/teacher/share',
      name:'Share',
      component:Share
    },
    {
      path:'/mobile/teacher/createshare',
      name:'CreateShare',
      component:CreateShare
    }
    
  ]
})
