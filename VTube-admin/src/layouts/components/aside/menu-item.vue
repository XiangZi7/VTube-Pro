<script setup lang="ts">
import { RouterMenu, bread } from '@/interface/store/menuStore'
import MenuItem from './menu-item.vue'

import { Icon } from '@iconify/vue'
const router = useRouter()
const menuStore = useMenuStore()
defineProps({
  itemData: {
    type: Object,
    default: () => {},
  },
})

function routs(routers: RouterMenu, event: Event) {
  event.stopPropagation()
  let routerObj: bread = {
    icon: routers.meta.icon as string,
    router: routers.path,
    title: routers.meta.title,
  }
  menuStore.setBreadcrumbList(routerObj)
  router.push(routers.path)
}
</script>
<template>
  <li class="mb-4" @click="routs(itemData, $event)">
    <div
      class="cursor-pointer text-[var(--el-color-primary)] hover:bg-gray-200 dark:hover:bg-gray-400/20 dark:bg-white-800/10 dark:text-white flex items-center w-auto gap-2 rounded-md text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
    >
      <Icon
        v-if="itemData.meta.icon"
        :icon="itemData.meta.icon"
        class="w-5 h-5 mr-3"
      />
      {{ itemData.meta.title }}
    </div>
    <template v-if="itemData.children && itemData.children.length">
      <ul class="ml-5">
        <menu-item
          v-for="item in itemData.children"
          :key="item.meta.menuId"
          :item-data="item"
        />
        <!-- <li
          v-for="children in itemData.children"
          :key="children.meta.menuId"
          @click="routs(children, $event)"
          class="ml-5 text-[var(--el-color-primary)] flex cursor-pointer dark:hover:bg-gray-400/20 dark:bg-white-800/10 hover:bg-gray-200 dark:text-white items-center w-auto gap-2 rounded-md text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
        >
          <Icon
            v-if="children.meta.icon"
            :icon="children.meta.icon"
            class="w-5 h-5 mr-3"
          />
          {{ children.meta.title }}
        </li> -->
      </ul>
    </template>
  </li>
</template>
<style lang="scss" scoped></style>
