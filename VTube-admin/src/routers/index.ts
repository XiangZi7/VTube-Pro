import { createRouter, createWebHashHistory } from 'vue-router'

import { staticRouter } from './modules/staticRouter'
import { initDynamicRouter } from "./modules/dynamicRouter";
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

router.beforeEach(async (to, from, next) => {
  const userStore = useUserStore();
  const menuStore = useMenuStore()

  // 3.判断是访问登陆页，有 Token 就在当前页面，没有 Token 重置路由到登陆页
  if (to.path.toLocaleLowerCase() === "/login") {
    if (userStore.userInfo.token) return next(from.fullPath);
    // resetRouter();
    return next();
  }

  // 5.判断是否有 Token，没有重定向到 login 页面
  if (!userStore.userInfo.token) return next({ path: "/login", replace: true });
  // 6.如果没有菜单列表，就重新请求菜单列表并添加动态路由
  if (!menuStore.authMenuListGet.length) {
    await initDynamicRouter();
    return next({ ...to, replace: true });
  }
  // 8.正常访问页面
  next()
  // if (to.meta && to.meta.permission) {
  //   if (userPermissions.includes(to.meta.permission)) {
  //     next();
  //   } else {
  //     next('/403'); // 没有权限，重定向到 403 页面
  //   }
  // } else {
  //   next();
  // }
});

/**
 * @description 路由跳转结束
 * */
router.afterEach(() => {
  NProgress.done()
})
export default router
