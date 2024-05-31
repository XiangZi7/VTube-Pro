<script setup lang="ts">
import Meun from './components/meun.vue'
import { ProjectorDto } from '@/interface/pages/projector'
const fileUrl = import.meta.env.VITE_API_MINIO
const router = useRouter()
const {
  tableData,
  handleSizeChange,
  handleCurrentChange,
  getTableList,
  pageable,
  searchParam,
  reset,
  search,
} = useProjector<ProjectorDto>('/Projector/list')

onMounted(() => {
  getTableList()
})
</script>
<template>
  <section class="h-full w-full flex gap-8 px-4 md:px-6 py-8">
    <div class="h-auto w-auto">
      <div
        class="flex flex-col p-5 border border-gray-500/50 border-dashed rounded-lg min-w-[265px] shadow-app"
      >
        <h2 class="text-2xl font-bold">过滤</h2>
        <Meun :search="search" :search-param="searchParam" :reset="reset" />
      </div>
    </div>
    <div class="flex flex-col h-full">
      <div
        class="block w-full h-full flex-1 box-border overflow-x-hidden duration-300 ease-in-out dark:text-neutral-200"
      >
        <div class="grid md:grid-cols-4 lg:grid-cols-5 xl:grid-cols-6 gap-8">
          <div
            v-for="item in tableData"
            :key="item.videoId"
            class="group relative overflow-hidden rounded-lg shadow-lg hover:shadow-xl transition-transform duration-300 ease-in-out hover:-translate-y-2 dark:border dark:border-dashed dark:border-gray-500/50 cursor-pointer"
            @click="router.push(`/play/${item.videoId}`)"
          >
            <img
              :src="fileUrl + item.imagePath"
              :alt="item.title"
              class="rounded-lg h-auto object-cover w-full aspect-[3/4] group-hover:opacity-90 transition-opacity duration-300 ease-in-out"
            />
            <div class="p-4 space-y-2 text-center">
              <h3 class="text-sm text-ellipsis font-semibold line-clamp-1">
                {{ item.title }}
              </h3>
              <p
                class="text-xs line-clamp-1 h-4 text-gray-500 dark:text-gray-400"
              >
                {{ item.tags }}
              </p>
            </div>
          </div>
        </div>
      </div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        v-model:current-page="pageable.pageNum"
        v-model:page-size="pageable.pageSize"
        :page-sizes="[10, 30, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageable.total"
        class="flex justify-end items-center mt-2"
      />
    </div>
  </section>
</template>
