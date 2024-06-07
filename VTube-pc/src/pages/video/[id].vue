<script setup lang="ts">
import { Icon } from '@iconify/vue'
import {
  PlayState,
  VideoDetails,
  Episode,
  Comment,
} from '@/interface/pages/play'
import { Pagination } from '@/interface/utils/http'
const fileUrl = import.meta.env.VITE_API_MINIO
const router = useRouter()
const route = useRoute()
const userStore = useUserStore()
const state = reactive<PlayState>({
  activeNames: ['1'],
  details: {},
  episodeList: [],
  playIndex: 0,
  comText: '', // 用户的评论文本
  comText2: '', // 回复用户的评论文本
  comments: {},
})
const {
  activeNames,
  details,
  episodeList,
  playIndex,
  comText,
  comText2,
  comments,
} = toRefs(state)

onMounted(() => {
  httpPost<VideoDetails>('/video/details', { videoId: route.params.id }).then(
    ({ data }) => {
      state.details = data
    }
  )
  httpPost<Episode[]>('/play/episode', { videoId: route.params.id }).then(
    ({ data }) => {
      state.episodeList = data
    }
  )
  httpGet<Pagination<Comment[]>>('/comments', {
    videoId: route.params.id,
    targetType: 0,
  }).then(({ data }) => {
    state.comments = data
  })
})

function timeAgos(date: string) {
  return timeAgo(date)
}

// 评论
function send(targetId?: number, commentId?: number) {
  httpPost('/send', {
    videoId: state.details.videoId,
    content: targetId && commentId ? state.comText2 : state.comText,
    targetType: 0,
    targetId,
    parentId: commentId,
  }).then(({ code }) => {
    if (code != 200) return
    state.comText = ''
    state.comText2 = ''
    httpGet<Pagination>('/comments', {
      videoId: route.params.id,
      targetType: 0,
    }).then(({ data }) => {
      state.comments = data
    })
  })
}
</script>
<template>
  <div
    class="w-full p-4 mx-auto grid grid-rows-[auto_1fr_auto] gap-4 md:gap-6 pb-10"
  >
    <!-- 页面主内容区域 -->
    <main class="grid md:grid-cols-6 gap-10 items-start">
      <!-- 视频播放及信息部分 -->
      <div class="col-span-4 grid gap-4">
        <div class="grid gap-2">
          <!-- 视频播放区域 -->
          <div class="rounded-xl overflow-hidden border aspect-video">
            <vt-play
              v-if="episodeList.length > 0"
              :title="details.title"
              :url="episodeList[playIndex].videoPath"
              :cover="fileUrl + details.videoPath"
            />
          </div>
          <!-- 视频标题及用户信息 -->
          <div class="py-2 grid gap-2">
            <h1 class="text-xl font-semibold line-clamp-2">
              {{ details.title }}
            </h1>
            <div class="flex gap-2 items-center">
              <!-- 用户头像 -->
              <div class="flex gap-2 items-center">
                <el-avatar :src="fileUrl + details.avatarPath" />
                <div class="text-sm">
                  <div class="font-semibold">
                    {{ details.nickName }}
                  </div>
                  <div
                    class="text-xs line-clamp-1 text-gray-500 dark:text-gray-400"
                  >
                    {{ details.signature }}
                  </div>
                  <!-- 关注按钮 -->
                  <div class="text-xs text-gray-500 dark:text-gray-400"></div>
                </div>
              </div>
              <!-- 订阅和查看剧集按钮 -->
              <div class="ml-auto flex items-center">
                <el-button
                  type="primary"
                  @click="router.push(`/chat/${details.userId}`)"
                >
                  <div class="flex gap-1 items-center">
                    <icon-solar:letter-outline />
                    私信
                  </div>
                </el-button>
                <el-button type="primary">
                  <div class="flex gap-1 items-center">
                    <icon-material-symbols:add />
                    关注 {{ 50 }}
                  </div>
                </el-button>
                <el-button type="primary">
                  <div class="flex gap-1 items-center">
                    <icon-icon-park-outline:like />
                    Subscribe
                  </div>
                </el-button>
                <el-button type="primary">
                  <div class="flex gap-1 items-center">
                    <icon-ci:more-grid-big />
                    Episodes
                  </div>
                </el-button>
              </div>
            </div>
          </div>
          <!-- 视频描述及统计 -->
          <div class="grid gap-2 text-base leading-relaxed">
            <p>
              {{ details.description }}
            </p>
            <div class="flex items-center gap-2 text-xs">
              <icon-mdi:calendar-multiselect />
              {{ details.createTime }}
              •
              <icon-ic:sharp-remove-red-eye />
              {{ details.views }} •
              <Icon
                icon="material-symbols:favorite-outline"
                class="text-base"
              />{{ details.likes }}
            </div>
          </div>
        </div>
        <div class="grid gap-6">
          <h2 class="font-semibold text-xl" v-if="comments">
            {{ comments.total }} 评论
          </h2>
          <div class="flex items-center gap-2">
            <!-- 登录用户信息 -->
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
              <!-- 评论输入框 -->
              <el-input
                class="w-auto"
                v-model="comText"
                type="textarea"
                :rows="2"
                placeholder="你也想来一发吗？"
                clearable
                @keyup.enter="send"
              />
              <el-button type="primary" @click="() => send()">评论</el-button>
            </div>
          </div>
          <!-- 评论内容  begin-->
          <!-- 评论列表 -->
          <template v-if="comments">
            <div
              class="text-sm flex items-start gap-4"
              v-for="(item, index) in comments.records"
              :key="index"
            >
              <el-avatar :src="fileUrl + item.avatarPath" />
              <div class="grid gap-2">
                <div class="flex items-center gap-2">
                  <div class="font-semibold">{{ item.nickName }}</div>
                  <div class="text-gray-500 text-xs dark:text-gray-400">
                    {{ timeAgos(item.createTime) }}
                  </div>
                </div>
                {{ item.content }}
                <el-popover
                  class="!rounded-lg"
                  placement="bottom-start"
                  title="评论"
                  :width="300"
                  trigger="click"
                >
                  <template #reference>
                    <a class="" href="javascript:;"> 回复 </a>
                  </template>
                  <div class="flex items-center">
                    <el-input
                      type="textarea"
                      v-model="comText2"
                      placeholder="请输入"
                      clearable
                    />
                    <el-button
                      type="primary"
                      @click="send(item.userId, item.commentId)"
                      >评论</el-button
                    >
                  </div>
                </el-popover>

                <div class="grid gap-6">
                  <!-- 子评论列表  begin -->
                  <div
                    class="text-sm flex items-start gap-4"
                    v-for="(item2, index2) in item.children"
                    :key="index2"
                  >
                    <el-avatar :src="fileUrl + item2.avatarPath" />
                    <div class="grid gap-2">
                      <div class="flex items-center gap-2">
                        <div class="font-semibold">{{ item2.nickName }}</div>
                        <div class="text-gray-500 text-xs dark:text-gray-400">
                          {{ timeAgos(item2.createTime) }}
                        </div>
                      </div>
                      <div>{{ item2.content }}</div>
                    </div>
                  </div>
                </div>
                <!-- 子评论列表  end -->
              </div>
            </div>
          </template>
          <!-- 评论内容  end-->
        </div>
      </div>
      <!-- 右侧选集及推荐内容 -->
      <div class="col-span-2 grid gap-4">
        <!-- 选集区域 -->
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
        <!-- 推荐内容区域 -->
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
      </div>
    </main>
    <!-- 页面主内容区域 结束 -->
  </div>
</template>
