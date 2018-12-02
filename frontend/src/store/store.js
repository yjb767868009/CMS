import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import admin_store from './admin/admin_store'
export default new Vuex.Store({
    modules:{
        admin:admin_store
    },
    state:{
        token:'',
    },
    mutations:{
        set_token(state,token){
            state.token=token
            sessionStorage.token=token
        },
        del_token(state){
            state.token=''
            sessionStorage.removeItem('token')
        }
    }
})