import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import admin_store from './admin/admin_store'
import teacher_store from './teacher/teacher_store'
import student_store from './student/student_store'
export default new Vuex.Store({
    modules:{
        admin:admin_store,
        teacher:teacher_store,
        student:student_store,
    },
    state:{//this.$store.state.teacher
        show:false,
        token:'',
        userType:'',
        currentEmail:'',
        currentCourse:'',
        showContent:'',
        currentSeminar:'',
        currentClass:''
    },
    mutations:{
        
    }
})