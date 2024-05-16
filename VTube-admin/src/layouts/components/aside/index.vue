<template>
  <aside
    class="flex-shrink-0 rounded-tr-2xl box-border bg-white/60 text-sm dark:border-black dark:bg-neutral-900/80 dark:text-neutral-200 px-4 py-8 border-r hidden text-neutral-800 shadow-app w-0 md:w-64 transition-width duration-300 ease-in-out"
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
          class="cursor-pointer hover:bg-gray-100 flex items-center w-auto gap-2 rounded-md text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
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
              class="ml-5 flex cursor-pointer hover:bg-gray-100 items-center w-auto gap-2 rounded-md text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
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
