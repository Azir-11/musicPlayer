import {request} from '@/utils/request'
/** 
 * get方法，对应get请求 
 * @param {String} url [请求的url地址] 
 * @param {Object} params [请求时携带的参数] 
 */
//post 带参
export function gets(url:String,params:Object) {
    return request({
      url:url,//此处为自己请求地址
      method: 'get',
      data: params  
    })
  }
 
  /** 
 * post方法，对应post请求 
 * @param {String} url [请求的url地址] 
 * @param {Object} params [请求时携带的参数] 
 */
//post 带参
export function posts(url:String,param:Object) {
    return request({
      url:url,//此处为自己请求地址
      method: 'post',
      data:param 
    })
  }
// post无参
  export function post(url:String) {
    return request({
      url:url,//此处为自己请求地址
      method: 'post',
    })
  }
  // get无参
  export function get(url:String) {
    return request({
      url:url,//此处为自己请求地址
      method: 'get',
     headers:{
         'token':sessionStorage.getItem('Token') 
     }
    })
  }
 
 
 