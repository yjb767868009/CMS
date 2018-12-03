// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

//element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';

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

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
