<script setup lang="ts">
import { Icon } from '@iconify/vue'

const state = reactive({
  weeklyAnime: [
    { title: '星期一', dramaSeries: [] },
    { title: '星期二', dramaSeries: [] },
    { title: '星期三', dramaSeries: [] },
    { title: '星期四', dramaSeries: [] },
    { title: '星期五', dramaSeries: [] },
    { title: '星期六', dramaSeries: [] },
    { title: '星期日', dramaSeries: [] },
  ],
  weekTabsIndex: 0,
})
const { weeklyAnime, weekTabsIndex } = toRefs(state)
</script>

<template>
  <section class="bg-gray-900 text-white py-12 md:py-16 lg:py-20">
    <div class="container mx-auto px-4 md:px-6 lg:px-8">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div class="flex flex-col justify-center">
          <h1 class="text-3xl md:text-4xl lg:text-5xl font-bold mb-4">
            Discover the Best User-Generated Content
          </h1>
          <p class="text-gray-300 mb-6">
            Streamly is the platform where creators share their passions and
            connect with audiences.
          </p>
          <div class="flex space-x-4">
            <button
              class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-6 py-3 rounded-md"
            >
              详情
            </button>
            <button
              class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-6 py-3 rounded-md"
            >
              观看
            </button>
          </div>
        </div>
        <div class="relative">
          <img
            alt="Featured Video"
            class="rounded-lg object-cover aspect-video"
            height="450"
            src="https://generated.vusercontent.net/placeholder.svg"
            width="800"
          />
          <div
            class="absolute bottom-4 left-4 bg-gray-900 bg-opacity-70 px-4 py-2 rounded-md"
          >
            <div class="flex items-center space-x-2">
              <Icon icon="material-symbols:play-circle-outline" />
              <span class="text-sm font-medium">Watch Now</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <section class="bg-gray-100 py-12 md:py-16 lg:py-20">
    <div
      class="container mx-auto px-4 md:px-6 lg:px-8 grid grid-cols-1 md:grid-cols-5 gap-8"
    >
      <div class="w-full md:col-span-4">
        <h2 class="text-2xl md:text-3xl lg:text-4xl font-bold mb-6">
          Recent Videos
        </h2>
        <div
          class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6"
        >
          <div class="bg-white rounded-lg shadow-md overflow-hidden">
            <img
              src="https://generated.vusercontent.net/placeholder.svg"
              alt="Video Thumbnail"
              width="400"
              height="225"
              class="w-full h-48 object-cover"
              style="aspect-ratio: 400 / 225; object-fit: cover"
            />
            <div class="p-4">
              <h3 class="text-lg font-medium mb-2">
                Cooking with Grandma: Homemade Pasta
              </h3>
              <div class="flex items-center space-x-2 text-gray-500 mb-2">
                <Icon icon="material-symbols:account-circle" />
                <span>Grandma's Kitchen</span>
              </div>
              <div class="flex items-center space-x-2 text-gray-500">
                <Icon icon="ph:eye-bold" />
                <span>1.2K views</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="w-full md:col-span-1">
        <h2 class="text-2xl md:text-3xl lg:text-4xl font-bold mb-6">
          Hot Videos
        </h2>
        <div
          class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-1 lg:grid-cols-1 gap-6"
        >
          <div class="overflow-hidden flex items-center gap-2">
            1
            <img
              alt="Video Thumbnail"
              class="w-12 h-12 object-cover rounded"
              height="60"
              src="https://generated.vusercontent.net/placeholder.svg"
              width="100"
              style="aspect-ratio: 100 / 60; object-fit: cover"
            />
            <div class="text-sm">境界的彼方</div>
          </div>
          <div class="overflow-hidden flex items-center gap-2">
            2
            <img
              alt="Video Thumbnail"
              class="w-12 h-12 object-cover rounded"
              height="60"
              src="https://generated.vusercontent.net/placeholder.svg"
              width="100"
              style="aspect-ratio: 100 / 60; object-fit: cover"
            />
            <div class="text-sm">刀剑神域</div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <section class="bg-gray-100 py-12 md:py-16 lg:py-20">
    <div class="container mx-auto px-4 md:px-6 lg:px-8">
      <h2 class="text-2xl md:text-3xl lg:text-4xl font-bold mb-6">
        Weekly Anime
      </h2>
      <div class="w-full">
        <div
          role="tablist"
          class="h-9 items-center justify-center rounded-lg bg-muted p-1 text-muted-foreground flex border-b border-gray-200 dark:border-gray-800"
        >
          <button
            v-for="(item, index) in weeklyAnime"
            :key="item.title"
            @click="weekTabsIndex = index"
            class="inline-flex items-center justify-center whitespace-nowrap rounded-md mx-1 px-3 py-1 text-sm font-medium ring-offset-background transition-all focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50"
            :class="{
              'bg-white text-foreground bg-background text-foreground shadow':
                weekTabsIndex == index,
              'tab-transition-enter-active': weekTabsIndex > index,
              'tab-transition-leave-active': weekTabsIndex < index,
              'tab-transition-enter': weekTabsIndex > index,
              'tab-transition-leave-to': weekTabsIndex < index,
            }"
          >
            {{ item.title }}
          </button>
        </div>
        <div
          class="mt-2 ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 py-6"
          style="animation-duration: 0s"
        >
          <div class="bg-white rounded-lg shadow-md overflow-hidden">
            <img
              src="https://generated.vusercontent.net/placeholder.svg"
              alt="Anime Thumbnail"
              width="400"
              height="225"
              class="w-full h-48 object-cover"
              style="aspect-ratio: 400 / 225; object-fit: cover"
            />
            <div class="p-4">
              <h3 class="text-lg font-medium mb-2">Chainsaw Man</h3>
              <div class="flex items-center space-x-2 text-gray-500 mb-2">
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
                  class="w-4 h-4"
                >
                  <circle cx="12" cy="12" r="10"></circle>
                  <polyline points="12 6 12 12 16 14"></polyline>
                </svg>
                <span>8:30 PM</span>
              </div>
              <div class="flex items-center space-x-2 text-gray-500">
                <Icon icon="ph:eye-bold" />
                <span>900K views</span>
              </div>
            </div>
          </div>
        </div>
        <div
          class="mt-2 ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 py-6"
        ></div>
      </div>
    </div>
  </section>
</template>
