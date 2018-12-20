import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import admin_store from './admin/admin_store'
export default new Vuex.Store({
    modules:{
        admin:admin_store
    },
    state:{
        show:false,
        token:'',
        userType:'',
        currentEmail:'',
        currentCourse:'',
        showContent:'',
        currentSeminar:''
    },
    mutations:{
        
    }
})