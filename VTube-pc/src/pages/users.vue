<template>
  <main class="flex min-h-[calc(100vh_-_theme(spacing.16))] flex-1 flex-col gap-4 p-4 md:gap-8 md:p-10">
    <div class="max-w-6xl w-full mx-auto grid gap-2">
      <h1 class="font-semibold text-3xl">Account Settings</h1>
    </div>
    <div class="grid md:grid-cols-[180px_1fr] lg:grid-cols-[250px_1fr] items-start gap-6 max-w-6xl w-full mx-auto">
      <nav class="text-sm text-gray-500 grid gap-4 dark:text-gray-400">
        <a class="font-semibold text-gray-900 dark:text-gray-50" href="#">
          Profile
        </a>
        <a href="#">Password</a>
        <a href="#">Notifications</a>
        <a href="#">Integrations</a>
        <a href="#">Security</a>
        <a href="#">Billing</a>
      </nav>
      <router-view v-slot="{ Component, route }">
        <component :is="Component" :key="route.fullPath" />
      </router-view>
    </div>
  </main>
</template>
<script setup lang="ts">
</script>