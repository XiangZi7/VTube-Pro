<script setup lang="ts">
import { Icon } from '@iconify/vue'
import Bread from '@/layouts/components/bread/index.vue'
import router from '@/routers'
const fileUrl = import.meta.env.VITE_API_MINIO

const menuStore = useMenuStore()
const userStore = useUserStore()

const { isAside } = storeToRefs(menuStore)
const { userInfo } = storeToRefs(userStore)

const dialogVisible = ref(false)

const logout = () => {
  httpPost('/logout').then(({ code, data }) => {
    if (code == 200) {
      userStore.setUserInfo({})
      dialogVisible.value = false
      router.replace('/login')
    }
    messagePro(code, data as string)
  })
}
</script>

<template>
  <header
    class="flex items-center mb-3 px-6 py-4 rounded-b-2xl transition-width duration-300 ease-in-out dark:text-neutral-200"
  >
    <div class="flex items-center w-auto gap-2">
      <Icon
        :icon="isAside ? 'ep:expand' : 'ep:expand'"
        class="text-2xl cursor-pointer"
        :class="{ 'transform scale-x-[-1]': isAside }"
        @click="menuStore.setIsAside(!isAside)"
      />
      <Bread />
    </div>
    <div class="flex items-center w-80 ml-auto gap-2">
      <div class="w-full flex items-center gap-2">
        <el-input class="el-ipt" placeholder="Search">
          <template #prefix>
            <Icon icon="f7:search" />
          </template>
          <template #suffix>
            <div
              class="text-gray-900/50 transition-all dark:text-gray-50/50 text-xs"
            >
              CTRL + K
            </div>
          </template>
        </el-input>
      </div>
      <div class="flex items-center space-x-4">
        <!-- Iocn的地方-->
        <span
          class="relative flex h-10 w-10 shrink-0 overflow-hidden rounded-full"
        >
          <el-dropdown>
            <el-image :src="fileUrl + userInfo.avatarPath" />
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <div
                    class="flex items-center gap-1"
                    @click="router.push('/info')"
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
        </span>
      </div>
    </div>
    <el-dialog v-model="dialogVisible" title="Tips" width="500">
      <span>确定退出登录吗？</span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="logout"> 确定 </el-button>
        </div>
      </template>
    </el-dialog>
  </header>
</template>
