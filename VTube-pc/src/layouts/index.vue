<script setup lang="ts">
import Header from './components/header/index.vue'
</script>

<template>
  <div
    class="flex flex-col md:flex-row h-full w-full box-border bg-gray-100 dark:bg-black"
  >
    <section class="flex flex-col w-auto h-auto flex-1 box-border z-10">
      <Header />
      <main
        class="block w-full h-full flex-1 box-border overflow-x-hidden duration-300 ease-in-out dark:text-neutral-200"
      >
        <router-view v-slot="{ Component, route }">
          <component :is="Component" :key="route.fullPath" />
        </router-view>
      </main>
    </section>
  </div>
</template>
