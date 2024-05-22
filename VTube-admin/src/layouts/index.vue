<script setup lang="ts">
import Header from './components/header/index.vue'
import Aside from './components/aside/index.vue'
import Footer from './components/footer/index.vue'
import BG from './components/bg/index.vue'
</script>

<template>
  <div
    class="flex flex-col md:flex-row h-full w-full box-border bg-gray-100 dark:bg-black"
  >
    <BG />
    <Aside class="z-10" />
    <section class="flex flex-col w-auto h-auto flex-1 box-border z-10 px-5">
      <Header />
      <main
        class="rounded-2xl block w-full h-full flex-1 box-border overflow-x-hidden overflow-autotransition-width duration-300 ease-in-out  dark:text-neutral-200"
      >
        <router-view v-slot="{ Component, route }">
          <component :is="Component" :key="route.fullPath" />
        </router-view>
      </main>
      <Footer />
    </section>
  </div>
</template>