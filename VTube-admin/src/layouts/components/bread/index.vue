<template>
  <el-scrollbar style="width: 500px;">
    <div class="flex items-center gap-4 shadow-default rounded-2xl">
      <nav
        class="hidden lg:flex items-center gap-3 shrink-0 text-sm font-medium"
      >
        <router-link
          v-for="item in breadcrumbList"
          :key="item.router"
          class="flex items-center gap-3 rounded-lg px-3 py-2 bg-gray-400/20 dark:bg-white-800/10 text-gray-900 transition-all hover:text-gray-900 dark:text-gray-50 dark:hover:text-gray-50 relative"
          :to="item.router"
          @mouseenter="setHover(item.router)"
          @mouseleave="clearHover"
        >
          <Icon :icon="item.icon" class="text-base" />
          <span class="text-xs">{{ item.title }}</span>
          <span
            v-if="breadcrumbList.length > 1"
            v-show="hover === item.router"
            class="absolute right-1 top-0 cursor-pointer"
            @click="menuStore.deleteBread(item)"
          >
            <Icon icon="ep:close-bold" />
          </span>
        </router-link>
      </nav>
    </div>
  </el-scrollbar>
</template>
<script setup lang="ts">
import { Icon } from '@iconify/vue'
const menuStore = useMenuStore()

const { breadcrumbList } = storeToRefs(menuStore)
const hover = ref('')
const setHover = (router) => {
  hover.value = router // 将悬停的 router 赋值给 hover 变量
}
const clearHover = () => {
  hover.value = '' // 清空 hover 变量
}
</script>
