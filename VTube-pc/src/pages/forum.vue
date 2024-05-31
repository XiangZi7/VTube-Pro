<script lang="ts" setup>
import { Icon } from '@iconify/vue'
import { data } from '@/pages/forum/data'
</script>
<template>
  <div class="flex h-full w-full">
    <div
      class="hidden md:flex md:w-72 md:flex-col md:border-r md:border-gray-200 dark:border-dashed dark:md:border-gray-500/50"
    >
      <div
        class="flex items-center justify-center h-16 border-b border-gray-200 dark:border-dashed dark:md:border-gray-500/50"
      >
        <a class="flex items-center gap-2" href="#">
          <icon-ph:twitter-logo class="text-2xl" />
          <span class="font-bold text-xl">VTubetter</span>
        </a>
      </div>
      <nav class="flex flex-col gap-2 p-4">
        <router-link
          v-for="item in data"
          :key="item.router"
          :to="item.router"
          class="flex items-center gap-2 p-2 rounded-lg hover:bg-gray-200 hover:dark:bg-gray-500/50"
        >
          <Icon :icon="item.icon" class="text-lg" />
          <span>{{ item.title }}</span>
        </router-link>
      </nav>
    </div>
    <RouterView />
  </div>
</template>
