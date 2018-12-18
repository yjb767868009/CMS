import Vue from 'vue'
import Router from 'vue-router'
import MobileLogin from '@/components/MobileLogin'
import MobileTeacher from '@/components/MobileTeacher'
import SetAccount from '@/components/SetAccount'
import ManageCourse from '@/components/ManageCourse'
import SetSeminar from '@/components/SetSeminar'
import SetRound from '@/components/SetRound'
import CreateSeminar from '@/components/CreateSeminar'
import CreateRound from '@/components/CreateRound'
import TeacherSeminars from '@/components/TeacherSeminars'
import Seminar from '@/components/Seminar'
import Report from '@/components/Report'
import SeminarGrade from '@/components/SeminarGrade'
import ManageSeminar from '@/components/ManageSeminar'
import Activation from '@/components/Activation'
import FindPassword from '@/components/FindPassword'
import ModifyPassword from '@/components/ModifyPassword'
import ModifyEmail from '@/components/ModifyEmail'
import GradeList from '@/components/GradeList'
import TeamList from '@/components/TeamList'
import CourseInfo from '@/components/CourseInfo'
import ClassInfo from '@/components/ClassInfo'
import CreateClass from '@/components/CreateClass'
import Notify from '@/components/Notify'
import CreateCourse from '@/components/CreateCourse'
import Share from '@/components/Share'
import CreateShare from '@/components/CreateShare'
Vue.use(Router)

export default new Router({
  routes: [

    {
      path: '/mobile/login',
      name: 'MobileLogin',
      component: MobileLogin
    }, 
    {
      path:'/mobile/activation',
      name:'Activation',
      component:Activation
    },
    {
      path:'/mobile/findpw',
      name:'FindPassword',
      component:FindPassword
    },
    {
      path:'/mobile/modifypw',
      name:ModifyPassword,
      component:ModifyPassword
    },
    {
      path:'/mobile/modifyemail',
      name:'ModifyEmail',
      component:ModifyEmail
    },
    {
      path: '/mobile/teacher',
      name: 'MobileTeacher',
      component: MobileTeacher
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
