import Vue from 'vue'
import vuex from 'vuex'

Vue.use(vuex)

export default new vuex.Store({
    state:{
        showTeacher:true,
        showStudent:false,
        showAddTeacher:false,
        showModifyTeacher:false,
        showModifyStudent:false,
    },
    mutations:{
        
    }
})
