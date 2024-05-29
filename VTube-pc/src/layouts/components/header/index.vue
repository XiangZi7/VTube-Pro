<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { data } from './data.ts'
import LoginDialog from './login-dialog.vue'
const fileUrl = import.meta.env.VITE_API_MINIO

const isLogin = ref(false)
const userStore = useUserStore()
const { userInfo } = storeToRefs(userStore)

const router = useRouter()

const toLogin = () => {
  isLogin.value = !isLogin.value
}

const dialogVisible = ref(false)

function logout() {
  httpPost('/logout').then(() => {
    userStore.setUserInfo({})
    dialogVisible.value = false
  })
}
</script>

<template>
  <header class="bg-gray-900 text-white py-4 px-6 md:px-8 lg:px-10">
    <div class="flex items-center justify-between">
      <h1
        class="relative flex items-center text-4xl font-bold text-gray-800 dark:text-white dark:opacity-80 transition-colors cursor-pointer"
      >
        V
        <span class="ml-1 rounded-xl bg-current p-2 text-[0.7em] leading-none">
          <span class="text-white dark:text-black">Tube</span>
        </span>
      </h1>
      <nav class="hidden md:flex items-center space-x-6">
        <router-link
          :to="item.router"
          v-for="item in data"
          :key="item.title"
          class="hover:text-gray-300 flex items-center gap-1 text-sm"
          href="javascript:void(0)"
        >
          <component :is="Icon" :icon="item.icon" />
          {{ item.title }}
        </router-link>
      </nav>
      <div class="flex items-center space-x-4">
        <div v-if="userInfo && userInfo.token">
          <el-dropdown>
            <el-avatar :src="fileUrl + userInfo.avatarPath" />
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <div
                    class="flex items-center gap-1"
                    @click="router.push('/users/profile')"
                  >
                    <Icon
                      icon="material-symbols-light:account-circle"
                      class="text-xl"
                    />
                    个人信息
                  </div>
                </el-dropdown-item>
                <el-dropdown-item>
                  <div class="flex items-center gap-1">
                    <Icon icon="solar:settings-outline" class="text-xl" />
                    设置
                  </div>
                </el-dropdown-item>
                <el-dropdown-item divided>
                  <div
                    class="flex items-center gap-1"
                    @click="dialogVisible = true"
                  >
                    <Icon
                      icon="material-symbols:logout-rounded"
                      class="text-xl"
                    />
                    退出
                  </div>
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
        <button
          v-else
          @click="toLogin"
          class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2 rounded-md"
        >
          Sign In
        </button>
      </div>
    </div>
    <!-- 退出登录 -->
    <el-dialog v-model="dialogVisible" title="Tips" width="500">
      <span>确定退出登录吗？</span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="logout"> 确定 </el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 登录组件 -->
    <LoginDialog v-if="isLogin" v-model="isLogin" />
  </header>
</template>
