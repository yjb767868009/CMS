import axios from 'axios'
import router from 'vue-router'

//axios 配置文件以及拦截器

axios.defaults.baseURL='http://127.0.0.1:8000'
axios.defaults.withCredentials=true


axios.interceptors.request.use(
    config=>{
        config.data=JSON.stringify(config.data)
        config.headers={
            'Content-type':'application/x-www-form-urlencoded'
        };
        return config
    },
    err=>{
        return Promise.reject(err)
    }
)

// http response 拦截器
axios.interceptors.response.use(
    response => {
      if(response.data ==='NoLogIn') {
        router.push({
          path: '/login',
          query: {redirect: router.currentRoute.fullPath} //从哪个页面跳转
        })
      }
      if(response.data==='AdminsNoLogIn'){
		console.log("AdminsNoLogIn")
        router.push({
            path:'AdminLogin',
            query:{redirect: router.currentRoute.fullPath}
        })
        }
      return response;
    }
    
    ,
    error => {
      return Promise.reject(error.response.data)
});