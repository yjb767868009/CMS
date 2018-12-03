import Vue from 'vue'

import Login from '@/views/Login'
import Router from 'vue-router'

//test
import Test from '@/views/Test'

//Admin
import Admin from '@/views/Admin/Admin'
import AdminLogin from '@/views/Admin/AdminLogin'

//Student
import SeminarDetail from '@/views/Student/SeminarDetail'
import SeminarPresentation from '@/views/Student/SeminarPresentation'
import SeminarPPT from '@/views/Student/SeminarPPT'
import SeminarRegistration from '@/views/Student/SeminarRegistration'
import SeminarRegistrationModification from '@/views/Student/SeminarRegistrationModification'
import SeminarScore from '@/views/Student/SeminarScore'
import SeminarSeqFinished from '@/views/Student/SeminarSeqFinished'
import SeminarSeqRunning from '@/views/Student/SeminarSeqRunning'
import SeminarSeqUnsigned from '@/views/Student/SeminarSeqUnsigned'
import SeminarSigned from '@/views/Student/SeminarSigned'
import SetAccount from '@/views/Student/SetAccount'
import StudentCourseSeminar from '@/views/Student/StudentCourseSeminar'
import StudentInfo from '@/views/Student/StudentInfo'
import StudentSeminarList from '@/views/Student/StudentSeminarList'

//Teacher

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      name:'Login',
      component:Login
    },
    {
      path:'/login',
      name:'Login',
      component:Login,
    },
    {
      path:'/test',
      name:'Test',
      component:Test
    },
    //admin
    {
      path: '/admin',
      name: 'Admin',
      component: Admin
    },
    {
      path:'/adminLogin',
      name:'AdminLogin',
      component:AdminLogin,
    },

    //student
    {
      path:'/mobile/student/studentInfo',
      name:'StudentInfo',
      component:StudentInfo
    },
    {
      path:'/mobile/Student/studentSeminarList',
      name:'StudentSeminarList',
      component:StudentSeminarList
    },
    {
      path:'/mobile/student/course',
      name:'StudentCourseSeminar',
      component:StudentCourseSeminar
    },
    {
      path:'/mobile/student/course/seminar',
      name:'SeminarDetail',
      component:SeminarDetail
    },
    {
      path:'/mobile/student/course/seminar/finished',
      name:'SeminarSeqFinished',
      component:SeminarSeqFinished
    },
    {
      path:'/mobile/student/course/seminar/finished/ppt',
      name:'SeminarPPT',
      component:SeminarPPT
    },
    {
      path:'/mobile/student/course/seminar/running',
      name:'SeminarSeqRunning',
      component:SeminarSeqRunning
    },
    {
      path:'/mobile/student/course/seminar/running/presentation',
      name:'SeminarPresentation',
      component:SeminarPresentation
    },
    {
      path:'/mobile/student/course/seminar/running/ppt',
      name:'SeminarPPT',
      component:SeminarPPT
    },
    {
      path:'/mobile/student/course/seminar/unsigned',
      name:'SeminarSeqUnsigned',
      component:SeminarSeqUnsigned
    },
    {
      path:'/mobile/student/course/seminar/unsigned/ppt',
      name:'SeminarPPT',
      component:SeminarPPT
    },
    {
      path:'/mobile/student/course/seminar/unsigned/registration',
      name:'SeminarRegistration',
      component:SeminarRegistration
    },
    {
      path:'/mobile/student/course/seminar/signed',
      name:'SeminarSigned', //截止和没截止
      component:SeminarSigned
    },
    {
      path:'/mobile/student/course/seminar/signed',
      name:'SeminarSigned',
      component:SeminarSigned
    },
  ]
})
