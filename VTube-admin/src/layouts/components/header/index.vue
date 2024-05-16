<script setup lang="ts">
import { Icon } from '@iconify/vue'
import Bread from '@/layouts/components/bread/index.vue'

const menuStore = useMenuStore()
const { isAside } = storeToRefs(menuStore)
</script>

<template>
  <header
    class="flex items-center mx-5 px-6 py-4 bg-white/60 shadow-app rounded-b-2xl"
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
        <input
          class="flex h-10 border-input bg-background text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 px-4 py-2 border rounded-2xl w-full"
          placeholder="Search"
        />
      </div>
      <div class="flex items-center space-x-4">
        <!-- Iocn的地方-->
        <span
          class="relative flex h-10 w-10 shrink-0 overflow-hidden rounded-full"
        >
          <el-dropdown>
            <el-image src="src/assets/logo.png" />
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <div class="flex items-center gap-1">
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
                  <div class="flex items-center gap-1">
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
  </header>
</template>
