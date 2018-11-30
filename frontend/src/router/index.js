import Vue from 'vue'
import Router from 'vue-router'
import Admin from '@/views/Admin'
import AdminLogin from '@/views/AdminLogin'
import Login from '@/views/Login'
import CreateTeacher from '@/views/CreateTeacher'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      redirect:'/login'
    },
    {
      path:'/login',
      name:'Login',
      component:Login
    },
    {
      path: '/admin',
      name: 'Admin',
      component: Admin
    },
    {
      path:'/adminLogin',
      name:'AdminLogin',
      component:AdminLogin
    },
    {
      path:'/admin/createTeacher',
      name:'CreateTeacher',
      component:CreateTeacher
    }
  ]
})
