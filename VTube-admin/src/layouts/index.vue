<script setup lang="ts">
import Header from './components/header/index.vue'
import Aside from './components/aside/index.vue'
import Bread from './components/bread/index.vue'
</script>

<template>
  <div class="flex flex-col h-screen bg-gray-100 dark:bg-black">
    <Header />
    <section class="flex flex-1 overflow-hidden z-10">
      <Aside class="w-64 overflow-auto p-4 md:flex hidden" />
      <main class="flex flex-1 flex-col md:pt-4 md:px-4 overflow-hidden">
        <Bread />
        <div class="flex-1 overflow-auto pt-2">
          <router-view v-slot="{ Component, route }">
            <component :is="Component" :key="route.fullPath" />
          </router-view>
        </div>
      </main>
    </section>
  </div>
</template>
