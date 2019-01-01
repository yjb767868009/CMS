import Vue from 'vue'
import vuex from 'vuex'

Vue.use(vuex)

export default new vuex.Store({
    state:{
        name:'', 
        account:'',
        email:'',
        currentCourse:'',//courseId,courseName
        Myteam:'',
        newMembers:'',
        currentSeminar:'',//seminarId,
        currentClass:'',
        currentRound:'',//roundId,order
        currentAttendance:'',//message, attendance
    },
    mutations:{
        
    }
})
