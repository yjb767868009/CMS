import Vue from 'vue'


import Router from 'vue-router'

//test
import Test from '@/views/Test'

//user
import Login from '@/views/User/Login'
import MobileLogin from '@/views/User/MobileLogin'
import Activation from '@/views/User/Activation'
import FindPassword from '@/views/User/FindPassword'
import ModifyPassword from '@/views/User/ModifyPassword'
import ModifyEmail from '@/views/User/ModifyEmail'

//Admin
import AdminLogin from '@/views/Admin/AdminLogin'
import AdminStudent from '@/views/Admin/AdminStudent'
import AdminTeacher from '@/views/Admin/AdminTeacher'

//Student
import SeminarDetail from '@/views/Student/SeminarDetail'
import SeminarPresentation from '@/views/Student/SeminarPresentation'
// import SeminarPPT from '@/views/Student/SeminarPPT'
import SeminarRegistration from '@/views/Student/SeminarRegistration'
import SeminarRegistrationModification from '@/views/Student/SeminarRegistrationModification'
import SeminarScore from '@/views/Student/SeminarScore'
import SeminarSeqFinished from '@/views/Student/SeminarSeqFinished'
import SeminarSeqRunning from '@/views/Student/SeminarSeqRunning'
// import SeminarSeqUnsigned from '@/views/Student/SeminarSeqUnsigned'
import SeminarSigned from '@/views/Student/SeminarSigned'
import StudentCourseSeminar from '@/views/Student/StudentCourseSeminar'
import StudentInfo from '@/views/Student/StudentInfo'
import StudentSeminarList from '@/views/Student/StudentSeminarList'
import StuSetAccount from '@/views/Student/StuSetAccount'
import MyClass from '@/views/Student/MyClass'
import MyScore from '@/views/Student/MyScore'
import StudentCourseInfo from '@/views/Student/StudentCourseInfo'
import TeamFreedom from '@/views/Student/TeamFreedom'
import NewTeam from '@/views/Student/NewTeam'
import TeamLeader from '@/views/Student/TeamLeader'
import TeamMember from '@/views/Student/TeamMember'

//Teacher
import MobileTeacher from '@/views/Teacher/MobileTeacher'
import TeacherSetAccount from '@/views/Teacher/SetAccount'
import ManageCourse from '@/views/Teacher/ManageCourse'
import SetSeminar from '@/views/Teacher/SetSeminar'
import SetRound from '@/views/Teacher/SetRound'
import CreateSeminar from '@/views/Teacher/CreateSeminar'
import CreateRound from '@/views/Teacher/CreateRound'
import TeacherSeminars from '@/views/Teacher/TeacherSeminars'
import Seminar from '@/views/Teacher/Seminar'
import Report from '@/views/Teacher/Report'
import SeminarGrade from '@/views/Teacher/SeminarGrade'
import ManageSeminar from '@/views/Teacher/ManageSeminar'
import GradeList from '@/views/Teacher/GradeList'
import TeamList from '@/views/Teacher/TeamList'
import CourseInfo from '@/views/Teacher/CourseInfo'
import ClassInfo from '@/views/Teacher/ClassInfo'
import CreateClass from '@/views/Teacher/CreateClass'
import Notify from '@/views/Teacher/Notify'
import CreateCourse from '@/views/Teacher/CreateCourse'
import Share from '@/views/Teacher/Share'
import CreateShare from '@/views/Teacher/CreateShare'


Vue.use(Router)

export default new Router({
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
      path: '/mobileLogin',
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
      path:'/mobile/activation',
      name:'Activation',
      component:Activation
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
    // {
    //   path:'/mobile/student/course/seminar/finished/ppt',
    //   name:'SeminarPPT',
    //   component:SeminarPPT
    // },
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
    // {
    //   path:'/mobile/student/course/seminar/running/ppt',
    //   name:'SeminarPPT',
    //   component:SeminarPPT
    // },
    // {
    //   path:'/mobile/student/course/seminar/unsigned',
    //   name:'SeminarSeqUnsigned',
    //   component:SeminarSeqUnsigned
    // },
    // {
    //   path:'/mobile/student/course/seminar/unsigned/ppt',
    //   name:'SeminarPPT',
    //   component:SeminarPPT
    // },
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
    // {
    //   path:'/mobile/student/course/seminar/signed',
    //   name:'SeminarSigned',
    //   component:SeminarSigned
    // },
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

    {
      path: '/mobile/teacher', //教师个人页界面
      name: 'MobileTeacher',
      component: MobileTeacher
    },
    {
      path: '/mobile/teacher/account',//账户与安全界面
      name: 'TeacherSetAccount',
      component: TeacherSetAccount
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
