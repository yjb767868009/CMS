import Vue from 'vue'
import Router from 'vue-router'

import Element from 'element-ui'
import 'element-ui/lib/theme-default/index.css'

Vue.use(Router)
Vue.use(Element)

export default new Router({
    routes:[
        {
            path:'/Login',
            name:'Login',
            component:Login
        },
        {
            path:'/AdminLogin',
            name:'AdminLogin',
            component:AdminLogin
        },
        {
            path:'/Admin',
            name:'Admin',
            component:Admin
        },
        {
            path:'/Admin/Teachers',
            name:'TeacherManagement',
            component:TeacherManagement
        },
        {
            path:'/Admin/Students',
            name:'StudentManagement',
            component:StudentManagement
        },
        

    ]
})