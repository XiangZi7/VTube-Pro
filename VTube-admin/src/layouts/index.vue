<script setup lang="ts">
import Header from './components/header/index.vue'
import Aside from './components/aside/index.vue'
import Bread from './components/bread/index.vue'
import Footer from './components/footer/index.vue'

</script>

<template>
  <div class="flex flex-col md:flex-row h-screen box-border">
    <Aside/>
    <section class="flex flex-col w-auto h-auto flex-1 box-border">
      <Header/>
      <Bread/>
      <main
          class="block flex-1 basis-auto box-border overflow-x-hidden overflow-auto px-5 py-2"
      >
        <router-view v-slot="{ Component, route }">
          <component :is="Component" :key="route.fullPath"/>
        </router-view>
      </main>
      <Footer />
    </section>
  </div>
</template>
