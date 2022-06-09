import { ElMessage  } from 'element-plus'
import axios from 'axios'
// import { getToken } from '@/utils/auth'
import { useTokenStore } from '@/stores/counter'
import {onBeforeMount } from 'vue'
 
 
export  const request =(options:any)=> {
  return new Promise((resolve, reject) => {
 
    // 1.创建axios的实例
    const service = axios.create({
      // 设置基础的url配置项，这样接口处的url前面就不用写url:'http://127.0.0.1:8000/api/home'，直接写成 url:'/api/home', 就可以了
      baseURL: '/api',
      timeout: 80000 // request timeout
    })

    // 请求拦截器
    service.interceptors.request.use(
      (config:any) => {
        let token:string =sessionStorage.getItem('Token') as string//此处换成自己获取回来的token，通常存在在cookie或者store里面
   
       
        if (token) {
          // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
          config.headers['token'] = token
          config.headers.Authorization =  +token       
       
         }
        return config
      },
      error => {
        // 对请求错误执行操作
        console.log("出错啦", error) // for debug
        Promise.reject(error)
      }
    )
 
   // http response 响应拦截器
    service.interceptors.response.use(
       (response:any) => {
        return response.data
      },
      error => {
        console.log('err' + error) // for debug
        if(error.response.status == 403){
          ElMessage.error('错了')
        }else{
          ElMessage.error('服务器请求错误，请稍后再试')
        }
        return Promise.reject(error)
      }
    )
    // 请求处理
    service(options)
      .then((res) => {
        resolve(res)
      })
      .catch((error) => {
        reject(error)
      })
  })
}
 
 
export default request
