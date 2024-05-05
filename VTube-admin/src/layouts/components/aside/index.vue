<template>
  <div
    class="hidden border-r bg-gray-100/40 lg:block dark:bg-gray-800/40 overflow-hidden"
  >
    <div class="flex h-full max-h-screen flex-col gap-2">
      <div class="flex h-[60px] items-center border-b px-6">
        <a class="flex items-center gap-2 font-semibold" href="#">
          <Icon icon="skill-icons:apple-light" class="text-2xl" />
          <span class="">Apps</span>
        </a>
        <button
          class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground ml-auto h-8 w-8"
        >
          <Icon icon="ph:bell" />
          <span class="sr-only">Toggle notifications</span>
        </button>
      </div>
      <div class="flex-1 overflow-auto py-2">
        <nav class="grid items-start px-4 text-sm font-medium">
          <router-link
            v-for="item in data"
            :key="item.title"
            :to="item.route"
            class="flex items-center gap-3 rounded-lg bg-gray-100 px-3 py-2 text-gray-900 transition-all hover:text-gray-900 dark:bg-gray-800 dark:text-gray-50 dark:hover:text-gray-50"
            href="#"
          >
            {{ item.title }}
          </router-link>
        </nav>
      </div>
      <div class="mt-auto p-4">
        <div
          class="rounded-lg border bg-card text-card-foreground shadow-sm"
          data-v0-t="card"
        >
          <div class="flex flex-col space-y-1.5 p-6 pb-4">
            <h3 class="text-2xl font-semibold leading-none tracking-tight">
              welcome to Pro
            </h3>
            <p class="text-sm text-muted-foreground">
              Unlock all features and get unlimited access to our support team
            </p>
          </div>
          <div class="p-6">
            <button
              class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3 w-full"
            >
              Upgrade
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { data } from './data.ts'
</script>
