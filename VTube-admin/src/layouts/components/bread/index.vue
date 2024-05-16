<template>
  <div
    class="flex items-center gap-4 border-b bg-white/60 dark:bg-gray-950 rounded-2xl shadow-app"
  >
    <a class="lg:hidden" data-id="20" href="#">
      <span class="sr-only">Home</span></a
    >
    <nav class="hidden lg:flex items-center gap-4 shrink-0 text-sm font-medium">
      <a
        class="flex items-center gap-3 rounded-lg bg-gray-100 px-3 py-2 text-gray-900 transition-all hover:text-gray-900 dark:bg-gray-800 dark:text-gray-50 dark:hover:text-gray-50"
        href="#"
      >
        <Icon icon="bx:home" class="text-base" />
        Home
      </a>
    </nav>
  </div>
</template>
<script setup lang="ts">
import { Icon } from '@iconify/vue'
</script>
