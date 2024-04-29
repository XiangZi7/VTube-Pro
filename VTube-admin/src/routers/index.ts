import { createRouter, createWebHashHistory } from 'vue-router'
import { staticRouter } from './modules/staticRouter'
import NProgress from '@/comfig/nprogress'

// 创建 router 实例
const router = createRouter({
  history: createWebHashHistory(),
  routes: staticRouter,
  strict: false,
})

/**
 * @description 路由跳转错误
 * */
router.onError((error) => {
  NProgress.done()
  console.warn('路由错误', error.message)
})

/**
 * @description 路由跳转结束
 * */
router.afterEach(() => {
  NProgress.done()
})
export default router
