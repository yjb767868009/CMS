// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

//element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';

//mint-ui
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'

//vuex
import store from './store/store'

//vue-cookie
import VueCookie from 'vue-cookie'

//axios
import axios from 'axios'
import '../config/axios'
Object.defineProperty(Vue.prototype,'$axios',{value:axios})

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueCookie)
Vue.use(MintUI)


//路由拦截
// router.beforeEach((to,from,next)=>{
//   if(to.meta.requireAuth!==false){
//     if(store.state.token){
//       if(to.fullPath.indexOf(store.state.userType))
//         next();
//       else{
//         next({path:'/401'})
//       }
//     }else{
//       if(to.fullPath.match(/admin/i)){
//         next({
//           path:'/adminLogin',
//           query:{redirect:to.fullPath}
//         })
//       }
//       else if(to.fullPath.match(/teacher/i)||to.fullPath.match(/student/i)){
//         next({
//           path:'/Login',
//           query:{redirect:to.fullPath}
//         })
//       }else{
//         next({
//           path:'/401'
//         })
//       }
//     }
//   }else{
//     next()
//   }
// })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
