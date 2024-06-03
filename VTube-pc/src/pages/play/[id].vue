<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { PlayState, VideoDetails, Episode } from '@/interface/pages/play'
import { Pagination } from '@/interface/utils/http'
const fileUrl = import.meta.env.VITE_API_MINIO

const route = useRoute()
const userStore = useUserStore()
const state = reactive<PlayState>({
  activeNames: ['1'],
  details: {},
  episodeList: [],
  playIndex: 0,
  comText: '', // 用户的评论文本
  comments: [],
})
const { activeNames, details, episodeList, playIndex, comText, comments } =
  toRefs(state)

onMounted(() => {
  httpPost<VideoDetails>('/play/details', { videoId: route.params.id }).then(
    ({ data }) => {
      state.details = data
    }
  )
  httpPost<Episode[]>('/play/episode', { videoId: route.params.id }).then(
    ({ data }) => {
      state.episodeList = data
    }
  )
  httpGet<Pagination>('/comments', {
    videoId: route.params.id,
    targetType: 0,
  }).then(({ data }) => {
    state.comments = data.records
  })
})

function timeAgos(date: string) {
  return timeAgo(date)
}
</script>
<template>
  <div
    class="w-full p-4 mx-auto grid grid-rows-[auto_1fr_auto] gap-4 md:gap-6 pb-10"
  >
    <main class="grid md:grid-cols-6 gap-10 items-start">
      <div class="col-span-4 grid gap-4">
        <div class="grid gap-2">
          <div class="rounded-xl overflow-hidden border aspect-video">
            <vt-play
              v-if="episodeList.length > 0"
              :title="details.title"
              :url="episodeList[playIndex].videoPath"
              :cover="fileUrl + details.imagePath"
            />
          </div>
          <div class="py-2 grid gap-2">
            <div class="flex items-center h-full">
              <div class="flex gap-2 items-center h-full">
                <img
                  :src="fileUrl + details.imagePath"
                  class="rounded-xl w-auto h-[200px]"
                />
                <div class="flex flex-col justify-around h-full">
                  <div class="text-sm w-full gap-3 flex flex-col">
                    <div class="font-semibold">{{ details.title }}</div>
                    <el-rate v-model="details.make" allow-half disabled />
                    <div class="flex items-center gap-2 text-xs">
                      <icon-mdi:calendar-multiselect />
                      {{ details.releaseTime }}
                    </div>
                    <div class="text-xs text-gray-500 dark:text-gray-400">
                      <div class="flex gap-2 items-center">
                        <icon-ic:sharp-remove-red-eye />
                        {{ details.views }}
                      </div>
                    </div>
                    <p
                      :title="details.description"
                      class="text-xs line-clamp-3"
                    >
                      {{ details.description }}
                    </p>
                  </div>
                </div>
              </div>
              <div class="ml-auto flex items-center gap-2">
                <el-button class="h-9 rounded-md px-3">
                  <div class="flex gap-1 items-center">
                    <Icon
                      icon="material-symbols:favorite-outline"
                      class="text-base"
                    />
                    喜欢
                  </div>
                </el-button>
              </div>
            </div>
          </div>
        </div>
        <div class="grid gap-6">
          <h2 class="font-semibold text-xl">110 Comments</h2>
          <div class="flex items-center gap-2">
            <template v-if="userStore.userInfo.token && userStore.userInfo">
              <el-avatar :src="fileUrl + userStore.userInfo.avatarPath" />
            </template>
            <span
              v-else
              class="relative flex shrink-0 overflow-hidden rounded-full w-10 h-10 border"
            >
              <span
                class="flex h-full w-full items-center justify-center rounded-full bg-muted"
                >AC</span
              >
            </span>
            <div class="flex items-center flex-1 gap-1">
              <el-input
                class="w-auto"
                v-model="comText"
                type="textarea"
                :rows="2"
                placeholder="你也想来一发吗？"
                clearable
              />
              <el-button type="primary">评论</el-button>
            </div>
          </div>
          <div
            class="text-sm flex items-start gap-4"
            v-for="(item, index) in comments"
            :key="index"
          >
            <el-avatar :src="fileUrl + item.avatarPath" />
            <div class="grid gap-1.5">
              <div class="flex items-center gap-2">
                <div class="font-semibold">{{ item.nickName }}</div>
                <div class="text-gray-500 text-xs dark:text-gray-400">
                  {{ timeAgos(item.createTime) }}
                </div>
              </div>
              <div>
                {{ item.content }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-span-2 grid gap-4">
        <el-collapse v-model="activeNames" v-if="episodeList.length > 1">
          <el-collapse-item title="选集" name="1">
            <el-scrollbar height="400px">
              <div
                class="w-full p-2 flex items-center justify-between hover:bg-gray-400/50 rounded-lg"
                v-for="(item, index) in episodeList"
                :key="item.episodeNumber"
                @click="playIndex = index"
              >
                <div>{{ item.episodeNumber }}</div>
                <div>{{ item.title }}</div>
              </div>
            </el-scrollbar>
          </el-collapse-item>
        </el-collapse>
        <div class="flex items-start gap-4 relative">
          <a class="absolute inset-0" href="#">
            <span class="sr-only">View</span>
          </a>
          <img
            src="https://generated.vusercontent.net/placeholder.svg"
            alt="Thumbnail"
            width="168"
            height="94"
            class="aspect-video rounded-lg object-cover"
          />
          <div class="text-sm">
            <div class="font-medium line-clamp-2">
              Introducing v0: Generative UI
            </div>
            <div class="text-xs text-gray-500 line-clamp-1 dark:text-gray-400">
              Vercel
            </div>
            <div class="text-xs text-gray-500 line-clamp-1 dark:text-gray-400">
              300K views · 5 days ago
            </div>
          </div>
        </div>
        <div class="flex items-start gap-4 relative">
          <a class="absolute inset-0" href="#">
            <span class="sr-only">View</span>
          </a>
          <img
            src="https://generated.vusercontent.net/placeholder.svg"
            alt="Thumbnail"
            width="168"
            height="94"
            class="aspect-video rounded-lg object-cover"
          />
          <div class="text-sm">
            <div class="font-medium line-clamp-2">
              Introducing the frontend cloud
            </div>
            <div class="text-xs text-gray-500 line-clamp-1 dark:text-gray-400">
              Vercel
            </div>
            <div class="text-xs text-gray-500 line-clamp-1 dark:text-gray-400">
              1.2M views · 2 months ago
            </div>
          </div>
        </div>
        <div class="flex items-start gap-4 relative">
          <a class="absolute inset-0" href="#">
            <span class="sr-only">View</span>
          </a>
          <img
            src="https://generated.vusercontent.net/placeholder.svg"
            alt="Thumbnail"
            width="168"
            height="94"
            class="aspect-video rounded-lg object-cover"
          />
          <div class="text-sm">
            <div class="font-medium line-clamp-2">
              Using Vercel KV with Svelte
            </div>
            <div class="text-xs text-gray-500 line-clamp-1 dark:text-gray-400">
              Lee Robinson
            </div>
            <div class="text-xs text-gray-500 line-clamp-1 dark:text-gray-400">
              21K views · 1 week ago
            </div>
          </div>
        </div>
        <div class="flex items-start gap-4 relative">
          <a class="absolute inset-0" href="#">
            <span class="sr-only">View</span>
          </a>
          <img
            src="https://generated.vusercontent.net/placeholder.svg"
            alt="Thumbnail"
            width="168"
            height="94"
            class="aspect-video rounded-lg object-cover"
          />
          <div class="text-sm">
            <div class="font-medium line-clamp-2">
              Loading UI with Next.js 13
            </div>
            <div class="text-xs text-gray-500 line-clamp-1 dark:text-gray-400">
              Delba
            </div>
            <div class="text-xs text-gray-500 line-clamp-1 dark:text-gray-400">
              12K views · 10 days ago
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
