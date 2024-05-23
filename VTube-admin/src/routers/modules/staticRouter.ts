import { RouteRecordRaw } from 'vue-router'

/**
 * staticRouter (静态路由)
 */
export const staticRouter: RouteRecordRaw[] = [
  {
    path: '/',
    redirect: '/dashboard',
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/pages/login/index.vue'),
    meta: {
      title: '登录',
    },
  },
  {
    path: '/layout',
    name: 'layout',
    component: () => import('@/layouts/index.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: '/dashboard',
        name: 'dashboard',
        component: () => import('@/pages/dashboard/index.vue'),
      },
      {
        path: '/dict',
        name: 'dict',
        component: () => import('@/pages/dict/index.vue'),
      },
      {
        path: '/video',
        name: 'video',
        component: () => import('@/pages/video/index.vue'),
      },
      {
        path: '/setting',
        name: 'setting',
        component: () => import('@/pages/setting/index.vue'),
      },
    ],
  },
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/pages/[...notFound].vue'),
  },
]
