<template>
  <div class="container mx-auto py-12">
    <div class="mb-8">
      <el-input
        placeholder="Search animations..."
        v-model="query"
        @keyup.enter="fetchData"
      />
    </div>
    <div class="grid grid-cols-8 gap-8">
      <div
        class="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden relative"
        v-for="item in items"
        :key="item.id"
        @click="toplay(item.id)"
      >
        <div class="relative h-[214px]">
          <img :src="item.imageUrl" :alt="item.title" class="w-full h-full" />
          <div
            class="absolute top-2 right-2 bg-black bg-opacity-50 text-white text-xs p-1 rounded"
          >
            <p>{{ item.country }} {{ item.year }}</p>
          </div>
          <div
            class="absolute left-1/2 bottom-2 transform -translate-x-1/2 bg-black bg-opacity-50 text-white text-xs p-1 rounded"
          >
            <p>{{ item.note }}</p>
          </div>
        </div>
        <div class="p-4">
          <div class="text-base font-bold mb-2 line-clamp-1">
            {{ item.title }}
          </div>
          <p class="text-base text-gray-700">
            <strong>评分:</strong> {{ item.rating }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const router = useRouter()
// 使用ref来保存搜索结果
const items = ref([])
const query = ref('')

// 通过API获取数据
const fetchData = async () => {
  httpGet(`/search?q=${encodeURIComponent(query.value)}`).then(({ data }) => {
    items.value = data
  })
}

const toplay = (id: string) => {
  router.push(`/play?id=${id}&type=v1`)
}
</script>
