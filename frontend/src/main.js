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
Object.defineProperty(Vue.prototype,'$axios',{value:axios})

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueCookie)
Vue.use(MintUI)

axios.defaults.baseURL='http://119.23.49.112:8000'
//axios.defaults.baseURL='http://localhost:8000'
axios.defaults.withCredentials=true


axios.interceptors.request.use(
    config=>{
        if(store.state.token){
          config.headers={
              'Authorization':"Bearer "+store.state.token
          }
        }
        return config
    },
    err=>{
        return Promise.reject(err)
    }
)
// http response 拦截器
axios.interceptors.response.use(
    response => {
        console.log('response interceptor:',response)

        if(response.data ==='NoLogIn') {
          router.push({
            path: '/login',
            query: {redirect: router.currentRoute.fullPath} //从哪个页面跳转
          })
        }

        if(response.data==='AdminsNoLogIn'){
        console.log("response interceptor: intercepting")
          router.push({
              path:'/AdminLogin',
              query: {redirect: router.currentRoute.fullPath} //从哪个页面跳转
          })
        }
        
      return response;
      }
    ,
    error => {
      return Promise.reject(error.response.data)
});


/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>',
})
