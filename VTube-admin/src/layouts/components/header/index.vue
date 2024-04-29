<script setup lang="ts">
import { Icon } from '@iconify/vue'
</script>

<template>
  <header
    class="flex h-14 lg:h-[60px] items-center gap-4 border-b bg-gray-100/40 px-6 dark:bg-gray-800/40"
  >
    <div class="w-full flex-1 flex items-center gap-3">
      <h1
        class="relative flex items-center text-4xl font-bold text-gray-800 dark:text-white dark:opacity-80 transition-colors cursor-pointer"
      >
        VTube
        <span class="ml-1 rounded-xl bg-current p-2 text-[0.7em] leading-none">
          <span class="text-white dark:text-black">Admin</span>
        </span>
      </h1>
    </div>
    <div class="flex items-center gap-1">
      <Icon icon="fluent:weather-hail-day-20-regular" class="text-lg" />
      <span class="text-sm text-gray-500 dark:text-gray-400"> 27° </span>
    </div>
    <Icon icon="material-symbols:bluetooth" class="text-lg" />
    <Icon icon="material-symbols:wifi" class="text-lg" />
    <Icon icon="ic:baseline-battery-charging-80" class="text-lg" />
    <button
      class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground rounded-full border border-gray-200 w-8 h-8 dark:border-gray-800"
      type="button"
      id="radix-:r2:"
      aria-haspopup="menu"
      aria-expanded="false"
      data-state="closed"
    >
      <img
        src="https://generated.vusercontent.net/placeholder.svg"
        width="32"
        height="32"
        class="rounded-full"
        alt="Avatar"
        style="aspect-ratio: 32 / 32; object-fit: cover"
      /><span class="sr-only">Toggle user menu</span>
    </button>
  </header>
</template>
