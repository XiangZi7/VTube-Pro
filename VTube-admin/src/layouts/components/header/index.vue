<script setup lang="ts">
import { Icon } from '@iconify/vue'
</script>

<template>
  <header class="flex items-center justify-between mx-5 px-6 py-4 bg-white/90 shadow-app rounded-b-2xl ">
    <div class="w-full md:w-1/2">
      <input
        class="flex h-10 border-input bg-background text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 px-4 py-2 border rounded-md w-full"
        placeholder="Search"
      />
    </div>
    <div class="flex items-center space-x-4">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="24"
        height="24"
        viewBox="0 0 24 24"
        fill="none"
        stroke="currentColor"
        stroke-width="2"
        stroke-linecap="round"
        stroke-linejoin="round"
        class="w-5 h-5 text-gray-600"
      >
        <path
          d="M22 17a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V9.5C2 7 4 5 6.5 5H18c2.2 0 4 1.8 4 4v8Z"
        ></path>
        <polyline points="15,9 18,9 18,11"></polyline>
        <path d="M6.5 5C9 5 11 7 11 9.5V17a2 2 0 0 1-2 2v0"></path>
        <line x1="6" x2="7" y1="10" y2="10"></line>
      </svg>
      <span
        class="relative flex h-10 w-10 shrink-0 overflow-hidden rounded-full"
      >
        <img
          class="aspect-square h-full w-full"
          alt="Profile"
          src="https://generated.vusercontent.net/placeholder.svg?height=40&amp;width=40"
        />
      </span>
    </div>
  </header>
</template>
