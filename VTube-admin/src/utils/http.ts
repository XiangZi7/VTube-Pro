import axios, {
  AxiosInstance,
  AxiosResponse,
  InternalAxiosRequestConfig,
} from 'axios'
import { StatusCode } from "@/enums/httpEnum";
import NProgress from '@/comfig/nprogress'
import { ApiResponse } from '@/interface/http'
import router from "@/routers";


export interface CustomAxiosRequestConfig extends InternalAxiosRequestConfig {
  noLoading?: boolean
}

// 创建axios实例并添加类型注解
const instance: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 10000,
  withCredentials: true,
})

// 请求拦截器
instance.interceptors.request.use(
  (config: CustomAxiosRequestConfig) => {
    const userStore = useUserStore()

    // token
    const token = userStore.userInfo.token;
    if (token != undefined && token != "") {
      config.headers.Authorization = token;
    }
    // 开启进度条
    NProgress.start()
    return config
  },
  (error) => Promise.reject(error)
)

// 响应拦截器
instance.interceptors.response.use(
  (response: AxiosResponse) => {
    const { data } = response;

    // 进度条结束
    NProgress.done()
    // 返回 data部分
    const userStore = useUserStore()
    if (data.code == StatusCode.NOT_LOGIN) {
      userStore.setUserInfo({})
      router.replace("/login")
      messagePro(data.code, "未登录");
    }
    return data
  },
  (error) => {
    // 响应错误时也结束进度条
    NProgress.done()
    // 继续 reject 错误，这样调用 http 请求的代码就能捕获到异常
    return Promise.reject(error)
  }
)

// 封装get方法
// 函数以正确地使用泛型类型
export const httpGet = <T>(
  url: string,
  params?: object
): Promise<ApiResponse<T>> => instance.get(url, { params })

// 封装post方法
export const httpPost = <T>(
  url: string,
  data?: object,
  header?: object
): Promise<ApiResponse<T>> => instance.post(url, data, header)
