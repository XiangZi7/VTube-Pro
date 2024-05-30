import { createRouter, createWebHistory } from 'vue-router/auto'

// 创建 router 实例
const router = createRouter({
  history: createWebHistory(),
})
/**
 *  目录结构即路由。
 * src/pages/users.vue => /
 * src/pages/about.vue => /about
 * src/pages/users/users.vue => /users
 * src/pages/users/profile.vue => /users/profile
 * src/pages/users/[id].vue => /users/:id
 * src/pages/users/settings.vue => /:users/settings
 * src/pages/[...notFound].vue => 404 路由
 */
export default router
