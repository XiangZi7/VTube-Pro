<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { data } from './data.ts'
import LoginDialog from './login-dialog.vue'

const isLogin = ref(false)
</script>

<template>
  <header class="bg-gray-900 text-white py-4 px-6 md:px-8 lg:px-10">
    <div class="flex items-center justify-between">
      <h1
        class="relative flex items-center text-4xl font-bold text-gray-800 dark:text-white dark:opacity-80 transition-colors cursor-pointer"
      >
        V
        <span class="ml-1 rounded-xl bg-current p-2 text-[0.7em] leading-none">
          <span class="text-white dark:text-black">Tube</span>
        </span>
      </h1>
      <nav class="hidden md:flex items-center space-x-6">
        <router-link
          :to="item.router"
          v-for="item in data"
          :key="item.title"
          class="hover:text-gray-300 flex items-center gap-1 text-sm"
          href="javascript:void(0)"
        >
          <component :is="Icon" :icon="item.icon" />
          {{ item.title }}
        </router-link>
      </nav>
      <div class="flex items-center space-x-4">
        <button
          @click="isLogin = !isLogin"
          class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2 rounded-md"
        >
          Sign In
        </button>
      </div>
    </div>
    <!-- 登录组件 -->
    <LoginDialog v-if="isLogin" v-model="isLogin" />
  </header>
</template>
