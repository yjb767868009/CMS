import axios from 'axios'

//axios 配置文件以及拦截器

axios.defaults.baseURL='http://127.0.0.1:8000'
axios.defaults.headers={'Content-type':'application/x-www-form-urlencoded'}

//request interceptor
// axios.interceptors.request.use(function(config){
//     return config
// },function(error){
//     return Promise.reject(error)
// })
