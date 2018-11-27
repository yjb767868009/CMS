import Vue from 'vue'
import Router from 'vue-router'
import Admin from '@/views/Admin'
import AdminLogin from '@/views/AdminLogin'
import Login from '@/views/Login'

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
    }
  ]
})
