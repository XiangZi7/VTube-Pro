<script setup lang="ts">
const fileUrl = import.meta.env.VITE_API_MINIO
const recommended = ref([])
httpGet('/anime/hot').then(({ data }) => {
  recommended.value = data.recommended
})
</script>
<template>
  <section class="w-full py-12 md:py-24 lg:py-32">
    <div class="container grid gap-6 md:gap-8 px-4 md:px-6 max-w-6xl mx-auto">
      <div
        class="flex flex-col md:flex-row items-start md:items-center gap-4 md:gap-8"
      >
        <div class="grid gap-1">
          <h1 class="text-3xl font-bold tracking-tight">剧场动画</h1>
          <p class="text-gray-500 dark:text-gray-400">
            Discover the perfect products for your needs.
          </p>
        </div>
      </div>
      <div class="grid md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8">
        <div v-for="item in recommended" :key="item.videoId" class="group">
          <div class="relative grid overflow-hidden rounded-lg">
            <img
              :src="fileUrl + item.imagePath"
              :alt="item.title"
              width="300"
              class="rounded-lg h-auto object-cover w-full aspect-[3/4] group-hover:opacity-90 transition-opacity"
            />
          </div>
          <h3 class="text-center text-sm font-medium mt-2">{{ item.title }}</h3>
        </div>
      </div>
    </div>
  </section>
</template>
