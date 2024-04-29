<template>
  <div class="grid md:grid-cols-[240px_1fr] gap-8 px-4 md:px-6 py-8">
    <div class="flex flex-col gap-6">
      <div class="grid gap-4">
        <h2 class="text-2xl font-bold">Filters</h2>
        <vt-collapse :panels="panels">
          <template v-slot="{ panel }">
            <p>{{ panel.content }}</p>
          </template>
        </vt-collapse>
      </div>
    </div>
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
      <div
        class="bg-white rounded-lg overflow-hidden shadow-sm dark:bg-gray-950"
      >
        <img
          src="https://generated.vusercontent.net/placeholder.svg"
          alt="Product Image"
          width="400"
          height="300"
          class="w-full h-60 object-cover"
          style="aspect-ratio: 400 / 300; object-fit: cover"
        />
        <div class="p-4 space-y-2">
          <h3 class="text-lg font-semibold">Robotic Vacuum</h3>
          <p class="text-sm text-gray-500 dark:text-gray-400">
            Intelligent floor cleaning with smart navigation.
          </p>

          <div class="flex items-center justify-between">
            <div class="flex items-center gap-2">
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
                class="w-5 h-5 fill-primary"
                v-for="item in 5"
                :key="item"
              >
                <polygon
                  points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"
                ></polygon>
              </svg>
              <span class="text-sm text-gray-500 dark:text-gray-400">4.4</span>
            </div>
            <button
              class="bg-black text-white inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-5"
            >
              观看
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
const panels = ref([
  { title: 'Panel 1', open: false, content: 'Content for Panel 1' },
  { title: 'Panel 2', open: false, content: 'Content for Panel 2' },
])
</script>
