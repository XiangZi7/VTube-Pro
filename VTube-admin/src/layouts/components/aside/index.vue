<template>
  <aside
      class="w-full flex-shrink-0 rounded-tr-2xl box-border bg-white/90 text-sm dark:border-black dark:bg-neutral-900/80 dark:text-neutral-200 md:w-64 px-4 py-8 border-r md:block hidden text-neutral-800 shadow-app"
  >
    <div class="flex items-center space-x-3">
      <span
          class="relative flex h-10 w-10 shrink-0 overflow-hidden rounded-full"
      >
        <img
            class="aspect-square h-full w-full"
            alt="Admin"
            src="https://generated.vusercontent.net/placeholder.svg?height=40&amp;width=40"
        />
      </span>
      <span class="text-lg font-semibold">Admin</span>
    </div>
    <ul>
      <li v-for="item in SideBarData" :key="item.router" class="mb-4">
        <div
            class="flex items-center w-full gap-2 rounded-md  text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
        >
          <Icon :icon="item.icon" class="w-5 h-5 mr-3"/>
          {{ item.Title }}
        </div>
        <div v-if="item.children">
          <ul>
            <li v-for="children in item.children" :key="children.router"
                class="flex items-center w-full gap-2 rounded-md  text-sm font-medium relative tap-highlight-transparent outline-none text-foreground no-underline p-3 hover:after:opacity-100 after:content-[''] after:inset-0 after:opacity-0 after:w-full after:h-full after:rounded-xl after:transition-background after:absolute hover:after:bg-foreground/10"
            >
              <Icon :icon="children.icon" class="w-5 h-5 mr-3"/>
              {{ children.Title }}
            </li>
          </ul>
        </div>
      </li>
    </ul>
    <!--    <nav class="flex flex-col mt-8 space-y-1">-->
    <!--      <button-->
    <!--        class="inline-flex items-center hover:bg-gray-100 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2 justify-start"-->
    <!--        v-for="(item, index) in data"-->
    <!--        :key="item.title"-->
    <!--        :class="-->
    <!--          index == currentTab-->
    <!--            ? 'bg-gray-100 dark:bg-gray-800 dark:hover:text-gray-50'-->
    <!--            : 'dark:text-gray-400'-->
    <!--        "-->
    <!--      >-->
    <!--        <Icon :icon="item.icon" class="w-5 h-5 mr-3" />-->
    <!--        {{ item.title }}-->
    <!--      </button>-->
    <!--    </nav>-->
  </aside>
</template>
<script setup lang="ts">
import {Icon} from '@iconify/vue'
import {SideBarData} from './data.ts'

const currentTab = ref(0)
</script>
