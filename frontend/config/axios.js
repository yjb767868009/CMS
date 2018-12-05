import axios from 'axios'

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
  //response.status为401时，去跳转到login
      if((response.data ==='NoLogIn')||(response.status==='401')) {
        this.$router.push({
          path: '/login',
          query: {redirect: router.currentRoute.fullPath} //从哪个页面跳转
        })
      }
      if(response.data==='AdminNoLogIn'){
        this.$router.push({
            path:'/adminLogin',
            query:{redirect: router.currentRoute.fullPath}
        })
        }
      return response;
    }
    
    ,
    error => {
      return Promise.reject(error.response.data)
});