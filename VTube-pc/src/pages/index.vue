<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { indexState, weeklyAmime } from '@/interface/pages/index'

const fileUrl = import.meta.env.VITE_API_MINIO
const router = useRouter()
const state = reactive<indexState>({
  weeklyAnime: [],
  weekTabsIndex: 0,
  top10: [],
  recommended: [],
})
const { weeklyAnime, weekTabsIndex, top10, recommended } = toRefs(state)

const weekDays = [
  '星期一',
  '星期二',
  '星期三',
  '星期四',
  '星期五',
  '星期六',
  '星期日',
]

onMounted(() => {
  httpGet<weeklyAmime[][]>('/anime/weekly-anime').then(({ data }) => {
    state.weeklyAnime = data
  })
  httpGet<indexState>('/anime/hot').then(({ data }) => {
    state.recommended = data.recommended
    state.top10 = data.top10
  })
})
</script>

<template>
  <section class="bg-gradient text-white py-12 md:py-16 lg:py-20">
    <div class="container mx-auto px-4 md:px-6 lg:px-8">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div class="flex flex-col justify-center">
          <h1 class="text-3xl md:text-4xl lg:text-5xl font-bold mb-4">
            境界的彼方《约束之绊》
          </h1>
          <p class="text-gray-300 mb-6 text-sm">
            电视动画《境界的彼方》（境界の彼方）改编自由鸟居なごむ创作、鸭居知世担任插画的同名轻小说。故事描述了有特殊能力的异界士少女粟山未来与稀有存在的半妖神原秋人相遇以后发生的一系列不平凡的故事。
          </p>
          <div class="flex space-x-4">
            <el-button type="primary"> 观看 </el-button>
          </div>
        </div>
        <div class="relative">
          <video
            controls
            alt="Featured Video"
            class="rounded-lg object-cover aspect-video"
            height="450"
            src="@/assets/境界的彼方《约束之绊》官方完整PV【 1080p】.mp4"
            width="800"
          />
        </div>
      </div>
    </div>
  </section>
  <section class="py-12 md:py-16 lg:py-20">
    <div
      class="container mx-auto px-4 md:px-6 lg:px-8 grid grid-cols-1 md:grid-cols-5 gap-8"
    >
      <div class="w-full md:col-span-4">
        <h2 class="text-2xl md:text-3xl lg:text-4xl font-bold mb-6">
          热门推荐
        </h2>
        <el-scrollbar>
          <div class="flex space-x-6">
            <div
              class="rounded-lg shadow-md overflow-hidden flex-none w-52 cursor-pointer"
              v-for="item in recommended"
              :key="item.videoId"
              @click="router.push(`/play/${item.videoId}`)"
            >
              <img
                :src="fileUrl + item.imagePath"
                :alt="item.title"
                class="w-full h-auto max-h-[388px]"
              />
              <div class="p-4">
                <h3 class="text-sm font-medium mb-2 text-center line-clamp-2">
                  {{ item.title }}
                </h3>
              </div>
            </div>
          </div>
        </el-scrollbar>
      </div>
      <div class="w-full md:col-span-1">
        <h2 class="text-2xl md:text-3xl lg:text-4xl font-bold mb-6">排行榜</h2>
        <el-scrollbar height="400px">
          <div
            class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-1 lg:grid-cols-1 gap-6"
          >
            <div
              class="overflow-hidden flex items-center gap-2 cursor-pointer"
              v-for="(item, index) in top10"
              :key="item.videoId"
              @click="router.push(`/play/${item.videoId}`)"
            >
              {{ index + 1 }}
              <img
                :alt="item.title"
                class="w-12 h-12 object-cover rounded"
                :src="fileUrl + item.imagePath"
                style="aspect-ratio: 48 / 48; object-fit: cover"
              />
              <div class="flex flex-col">
                <div class="text-sm line-clamp-2">{{ item.title }}</div>
                <div class="text-sm flex items-center space-x-2 text-gray-500">
                  <Icon icon="ph:eye-bold" />
                  <span>{{ item.views }}</span>
                </div>
              </div>
            </div>
          </div>
        </el-scrollbar>
      </div>
    </div>
  </section>
  <section class="py-12 md:py-16 lg:py-20">
    <div class="container mx-auto px-4 md:px-6 lg:px-8">
      <h2 class="text-2xl md:text-3xl lg:text-4xl font-bold mb-6">周番表</h2>
      <div class="w-full">
        <div
          role="tablist"
          class="h-9 items-center justify-center rounded-lg bg-muted p-1 text-muted-foreground flex border-b border-gray-200 dark:border-gray-800"
        >
          <button
            v-for="(item, index) in weekDays"
            :key="index"
            @click="weekTabsIndex = index"
            class="inline-flex items-center justify-center whitespace-nowrap rounded-md mx-1 px-3 py-1 text-sm font-medium ring-offset-background transition-all disabled:pointer-events-none disabled:opacity-50"
            :class="{
              ' bg-white dark:bg-gray-500/50 text-foreground bg-background text-foreground shadow':
                weekTabsIndex == index,
              'tab-transition-enter-active': weekTabsIndex > index,
              'tab-transition-leave-active': weekTabsIndex < index,
              'tab-transition-enter': weekTabsIndex > index,
              'tab-transition-leave-to': weekTabsIndex < index,
            }"
          >
            {{ item }}
          </button>
        </div>
        <div
          class="mt-2 grid grid-cols-3 md:grid-cols-4 lg:grid-cols-5 xl:grid-cols-6 gap-6 py-6"
          style="animation-duration: 0s"
        >
          <div
            class=" rounded-lg shadow-md overflow-hidden cursor-pointer"
            v-for="(item, index) in weeklyAnime[weekTabsIndex]"
            :key="index"
            @click="router.push(`/play/${item.videoId}`)"
          >
            <img
              :src="fileUrl + item.imagePath"
              alt="Anime Thumbnail"
              class="w-full h-auto max-h-[388px]"
            />
            <div class="p-4">
              <h3 class="text-sm text-center font-medium mb-2 line-clamp-2">{{ item.title }}</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
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
