<script setup lang="ts">
import Header from './components/header/index.vue'
import Footer from './components/footer.vue'
</script>

<template>
  <div class="flex flex-col min-h-screen">
    <Header />
    <div class="min-h-screen">
      <router-view v-slot="{ Component, route }">
        <component :is="Component" :key="route.fullPath" />
      </router-view>
    </div>
    <Footer />
  </div>
</template>
