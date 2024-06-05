<script setup lang="ts">
import { HomeState, VideoDTO } from '@/interface/pages'
import { Pagination } from '@/interface/utils/http'
const fileUrl = import.meta.env.VITE_API_MINIO
const router = useRouter()

const state = reactive<HomeState>({
  LatestUpdate: [],
  hot: [],
})
const { hot, LatestUpdate } = toRefs(state)

onMounted(() => {
  httpGet<VideoDTO[]>('/video/LatestUpdate').then(({ data }) => {
    state.LatestUpdate = data
  })
  httpGet<Pagination<VideoDTO[]>>('/video/hot').then(({ data }) => {
    state.hot = data.records
  })
})
</script>
<template>
  <div class="flex flex-col">
    <main class="flex-1">
      <section class="bg-[#1d1e22] py-12 md:py-16">
        <div class="container mx-auto px-4 md:px-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
            <div class="flex flex-col justify-center">
              <h1
                class="text-3xl md:text-4xl lg:text-5xl font-bold text-white mb-4"
              >
                在VTube上发现最好的视频
              </h1>
              <p class="text-gray-400 mb-6">
                探索广泛类别的大量视频，从动漫和游戏到音乐和视频
              </p>
              <div class="flex gap-4 items-center">
                <el-button
                  @click="router.push('/upload/video')"
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-10 px-4 py-2 bg-[#00a1d6] text-white hover:bg-[#0084b4]"
                >
                  我直接一个直接
                </el-button>
                <icon-uil:upload />
              </div>
            </div>
            <div class="aspect-video rounded-lg overflow-hidden">
              <span
                class="w-full h-full object-cover rounded-md bg-muted"
              ></span>
            </div>
          </div>
        </div>
      </section>
      <section class="py-12 md:py-16">
        <div class="container mx-auto px-4 md:px-6">
          <h2 class="text-2xl md:text-3xl font-bold mb-6">Trending Now</h2>
          <div
            class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6"
          >
            <div
              class="bg-[#1d1e22] rounded-lg overflow-hidden"
              v-for="item in LatestUpdate"
              :key="item.videoId"
            >
              <img
                :src="fileUrl + item.imagePath"
                width="400"
                height="225"
                :alt="item.title"
                class="w-full h-40 object-cover"
                style="aspect-ratio: 400 / 225; object-fit: cover"
              />
              <div class="p-4">
                <h3 class="text-white font-bold line-clamp-2 mb-2">
                  {{ item.title }}
                </h3>
                <div class="flex items-center gap-2 text-gray-400 text-sm">
                  <img
                    :src="fileUrl + item.avatarPath"
                    width="24"
                    height="24"
                    alt="Creator Avatar"
                    class="rounded-full"
                    style="aspect-ratio: 24 / 24; object-fit: cover"
                  />
                  <span>{{ item.nickName }}</span>
                  <span>•</span>
                  <span>{{ item.views }} views</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section class="py-12 md:py-16 bg-[#1d1e22]">
        <div class="container mx-auto px-4 md:px-6">
          <h2 class="text-2xl md:text-3xl font-bold mb-6 text-white">
            Recommended for You
          </h2>
          <div
            class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6"
          >
            <div
              class="bg-[#2b2c30] rounded-lg overflow-hidden"
              v-for="item in hot"
              :key="item.videoId"
            >
              <img
                :src="fileUrl + item.imagePath"
                :alt="item.title"
                class="w-full h-40 object-cover"
              />
              <div class="p-4">
                <h3 class="text-white font-bold line-clamp-2 mb-2">
                  {{ item.title }}
                </h3>
                <div class="flex items-center gap-2 text-gray-400 text-sm">
                  <img
                    :src="fileUrl + item.avatarPath"
                    width="24"
                    height="24"
                    alt="Creator Avatar"
                    class="rounded-full"
                    style="aspect-ratio: 24 / 24; object-fit: cover"
                  />
                  <span>{{ item.nickName }}</span>
                  <span>•</span>
                  <span>{{ item.views }} views</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>
