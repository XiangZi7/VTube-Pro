<template>
  <div class="flex items-center gap-4 shadow-default rounded-2xl">
    <a class="lg:hidden" href="#">
      <span class="sr-only">Home</span></a
    >
    <nav class="hidden lg:flex items-center gap-3 shrink-0 text-sm font-medium">
      <router-link
        class="flex items-center gap-3 rounded-lg px-3 py-2 bg-gray-400/20 dark:bg-white-800/10 text-gray-900 transition-all hover:text-gray-900 dark:text-gray-50 dark:hover:text-gray-50"
        to="#"
      >
        <Icon icon="bx:home" class="text-base" />
        Home
      </router-link>
      <router-link
        class="flex items-center gap-3 rounded-lg px-3 py-2 bg-gray-400/20 dark:bg-white-800/10 text-gray-900 transition-all hover:text-gray-900 dark:text-gray-50 dark:hover:text-gray-50"
        to="#"
      >
        <Icon icon="bx:home" class="text-base" />
        Home
      </router-link>
    </nav>
  </div>
</template>
<script setup lang="ts">
import { Icon } from '@iconify/vue'
</script>
