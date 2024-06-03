<script setup lang="ts">
import { RouterMenu } from '@/interface/store/menuStore'
import MenuItem from './menu-item.vue'

import { Icon } from '@iconify/vue'
const router = useRouter()

const isCollapsed = ref(false)

defineProps({
  itemData: {
    type: Object as () => RouterMenu,
    default: () => {},
  },
})

function routs(routers: RouterMenu, event: Event) {
  event.stopPropagation()
  router.push(routers.router)
}

function toggle(router: RouterMenu, event: Event) {
  if (router.children && router.children.length) {
    isCollapsed.value = !isCollapsed.value
  } else {
    routs(router, event)
  }
}
</script>
<template>
  <li class="mb-4">
    <div
      class="cursor-pointer text-[var(--el-color-primary)] hover:bg-gray-200 dark:hover:bg-gray-400/20 dark:bg-white-800/10 dark:text-white flex items-center w-auto gap-2 rounded-md text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
      @click="toggle(itemData, $event)"
    >
      <Icon v-if="itemData.icon" :icon="itemData.icon" class="w-5 h-5 mr-3" />
      {{ itemData.title }}
      <Icon
        v-if="itemData.children && itemData.children.length"
        :icon="
          isCollapsed
            ? 'material-symbols:keyboard-arrow-down-rounded'
            : 'material-symbols:keyboard-arrow-up'
        "
        class="w-5 h-5 ml-auto"
      />
    </div>
    <transition name="collapse">
      <ul v-if="isCollapsed" class="ml-5">
        <MenuItem
          v-for="item in itemData.children"
          :key="item.router"
          :item-data="item"
        />
      </ul>
    </transition>
  </li>
</template>
<style scoped>
.collapse-enter-active,
.collapse-leave-active {
  transition: all 0.3s ease;
}
.collapse-enter, .collapse-leave-to /* .collapse-leave-active in <2.1.8 */ {
  height: 0;
  opacity: 0;
}
</style>
