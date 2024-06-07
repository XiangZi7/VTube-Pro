<script setup lang="ts">
import { HomeState, VideoDTO } from '@/interface/pages'
import { Pagination } from '@/interface/utils/http'
const fileUrl = import.meta.env.VITE_API_MINIO
const router = useRouter()

const state = reactive<HomeState>({
  LatestUpdate: [],
  hot: [],
  page: 1,
})
const { hot, LatestUpdate } = toRefs(state)

onMounted(() => {
  httpGet<VideoDTO[]>('/video/LatestUpdate').then(({ data }) => {
    state.LatestUpdate = data
  })
})

// 分页
const bottomRef: Ref<HTMLElement | null> = ref(null)
const loadMoreData = async (): Promise<void> => {
  httpGet<Pagination<VideoDTO[]>>('/video/hot', { pageNum: state.page }).then(
    ({ data }) => {
      if (state.page > data.pages) return
      state.hot = state.hot.concat(data.records)
      state.page++
    }
  )
}

useInfiniteScroll({ target: bottomRef, callback: loadMoreData })
</script>
<template>
  <div class="flex flex-col">
    <main class="flex-1">
      <section class="bg-gradient text-white py-12 md:py-16 lg:py-20">
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
                  <div class="gap-1 flex items-center">
                    <icon-uil:upload />
                    我直接一个直接
                  </div>
                </el-button>
              </div>
            </div>
            <div class="overflow-hidden">
              <el-carousel motion-blur :autoplay="false">
                <el-carousel-item v-for="item in 4" :key="item">
                  <el-image src="src/assets/bg2.png" />
                </el-carousel-item>
              </el-carousel>
            </div>
          </div>
        </div>
      </section>
      <section class="py-12 md:py-16 bg-black">
        <div class="container mx-auto px-4 md:px-6">
          <h2 class="text-2xl md:text-3xl font-bold mb-6">最新视频</h2>
          <div
            class="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-6"
          >
            <div
              class="bg-[#1d1e22] rounded-lg overflow-hidden cursor-pointer"
              v-for="item in LatestUpdate"
              :key="item.videoId"
              @click="router.push(`/video/${item.videoId}`)"
            >
              <el-image
                :src="fileUrl + item.imagePath"
                :alt="item.title"
                class="w-full h-48"
                fit="cover"
                lazy
              />
              <div class="p-4">
                <h3 class="text-white font-bold line-clamp-1 mb-2">
                  {{ item.title }}
                </h3>
                <div class="flex items-center gap-2 text-gray-400 text-sm">
                  <el-avatar size="small" :src="fileUrl + item.avatarPath" />
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
            热门推荐
          </h2>
          <div
            class="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-6"
          >
            <div
              class="bg-[#2b2c30] rounded-lg overflow-hidden cursor-pointer"
              v-for="item in hot"
              :key="item.videoId"
              @click="router.push(`/video/${item.videoId}`)"
            >
              <el-image
                :src="fileUrl + item.imagePath"
                :alt="item.title"
                class="w-full h-48"
                fit="cover"
                lazy
              />
              <div class="p-4 flex flex-col justify-around h-fit">
                <h3 class="text-white font-bold line-clamp-1 mb-2">
                  {{ item.title }}
                </h3>
                <div class="flex items-center gap-2 text-gray-400 text-sm">
                  <el-avatar size="small" :src="fileUrl + item.avatarPath" />
                  <span>{{ item.nickName }}</span>
                  <span>•</span>
                  <span>{{ item.views }} views</span>
                </div>
              </div>
            </div>
          </div>
          <div ref="bottomRef"></div>
        </div>
      </section>
    </main>
  </div>
</template>
<style lang="scss" scoped>
.bg-gradient {
  background: linear-gradient(to right, #1f2937, rgba(31, 41, 55, 0.7)),
    url('@/assets/bg2.png');
  background-size: cover; /* 确保背景图片按比例缩放，并能完整显示 */
  background-repeat: no-repeat; /* 禁止背景图片重复 */
  background-position: center; /* 将背景图片放置在容器中央 */
}
</style>
