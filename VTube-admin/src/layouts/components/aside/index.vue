<template>
  <aside
    class="flex-shrink-0 rounded-tr-2xl box-border text-sm dark:text-neutral-200 px-4 py-8 hidden text-neutral-800 w-0 md:w-64 transition-width duration-300 ease-in-out"
    :class="isAside ? 'md:block' : ''"
  >
    <div class="flex items-center w-auto justify-center">
      <span class="text-lg font-semibold">
        <!--        <img src="@/assets/logo.png" class="object-cover w-24" />-->
      </span>
    </div>
    <ul class="mt-2">
      <li
        v-for="item in SideBarData"
        :key="item.router"
        class="mb-4"
        @click="routs(item.router, $event)"
      >
        <div
          class="cursor-pointer text-[var(--el-color-primary)] hover:bg-gray-200  dark:hover:bg-gray-400/20 dark:bg-white-800/10 dark:text-white flex items-center w-auto gap-2 rounded-md text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
        >
          <Icon :icon="item.icon" class="w-5 h-5 mr-3" />
          {{ item.Title }}
        </div>
        <template v-if="item.children">
          <ul>
            <li
              v-for="children in item.children"
              :key="children.router"
              @click="routs(children.router, $event)"
              class="ml-5  text-[var(--el-color-primary)] flex cursor-pointer dark:hover:bg-gray-400/20 dark:bg-white-800/10 hover:bg-gray-200 dark:text-white items-center w-auto gap-2 rounded-md text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
            >
              <Icon :icon="children.icon" class="w-5 h-5 mr-3" />
              {{ children.Title }}
            </li>
          </ul>
        </template>
      </li>
    </ul>
  </aside>
</template>
<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { SideBarData } from './data.ts'

const router = useRouter()
const menuStore = useMenuStore()
const { isAside } = storeToRefs(menuStore)

function routs(routers: string, event: Event) {
  event.stopPropagation()
  router.push(routers)
}
</script>
