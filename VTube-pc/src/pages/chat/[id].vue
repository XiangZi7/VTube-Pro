<script setup lang="ts">
import { Pagination } from '@/interface/utils/http'
import { State, ChatMessage, ChatSession } from '@/interface/pages/chat.ts'
const fileUrl = import.meta.env.VITE_API_MINIO
const userStore = useUserStore()
const { userInfo } = storeToRefs(userStore)

const route = useRoute()
const router = useRouter()

const toId = extractNumberFromMid(route.params.id as string)
const state = reactive<State>({
  currentSessionList: [], // 当前会话数据
  currentSessionIndex: 0, // 当前会话索引
})
const { currentSessionList, currentSessionIndex } = toRefs(state)

const { sendMessage, messages, addMessage, addMessages, clearMessage } =
  useWebSocket(`${import.meta.env.VITE_APP_WS}/ws`)

// 哨兵数据
const textarea = ref('')

function sendData() {
  if (!toId) return messagePro(300, '请选择会话!')

  let message = JSON.stringify({
    sender: userInfo.value.userId,
    recipient: toId,
    content: textarea.value,
  })

  addMessage(textarea.value)
  textarea.value = ''
  sendMessage(message)
}

// 提取聊天 ID 的函数
function extractNumberFromMid(input: string): string | null {
  const match = input.match(/\d+$/)
  return match ? match[0] : null
}

// 当前会话数据
onMounted(() => {
  console.log('重新加载了')

  httpGet<Pagination>('/chat/list').then(({ data }) => {
    if (data.records.length === 0) return

    state.currentSessionList = data.records as ChatSession[]
    if (data.records.length > 0) {
      loadChatMessages()
    }
  })
})

// 加载会话消息
function loadChatMessages(): void {
  clearMessage()
  httpGet<Pagination>('/chat/comments', { chatId: toId }).then(({ data }) => {
    const result = data.records.map((item) => ({
      senderId: item.senderId,
      recipient: toId,
      content: item.content,
      position: item.senderId == userInfo.value.userId ? 'right' : 'left',
    }))
    addMessages(result as ChatMessage[])
  })
}

// 更改会话
function changeSession(chatId: string, index: number) {
  state.currentSessionIndex = index
  router.replace(`/chat/mid${chatId}`)
  // route.params.id = chatId
  console.log("🚀 => route.params.id:", route.params.id)
  // loadChatMessages()
}
</script>
<template>
  <div
    class="h-full max-w-[1200px] mx-auto flex items-center justify-center box-border"
  >
    <div
      class="bg-white dark:bg-gray-800 flex w-full h-[80%] border box-border shadow-lg rounded-lg"
    >
      <div class="border-r hidden md:flex flex-col">
        <div class="flex items-center justify-between p-4 border-b">
          <div class="flex items-center gap-3">
            <span
              class="relative flex shrink-0 overflow-hidden rounded-full w-10 h-10"
            >
              <el-avatar :src="fileUrl + userInfo.avatarPath" />
            </span>
            <div>
              <p class="font-medium">{{ userInfo.nickName }}</p>
              <p class="text-sm text-gray-500 dark:text-gray-400">Online</p>
            </div>
          </div>
        </div>
        <div class="p-4">
          <el-input placeholder="Search chats" />
        </div>
        <div class="overflow-y-auto">
          <div
            class="grid gap-2 px-4 py-2"
            v-for="(item, index) in currentSessionList"
            :key="item.chatId"
            @click="changeSession(item.chatId, index)"
          >
            <a
              href="javascript:;"
              :class="{ 'bg-gray-200': index == currentSessionIndex }"
              class="flex items-center gap-3 rounded-lg p-2 hover:bg-gray-200 dark:hover:bg-gray-700"
            >
              <span
                class="relative flex shrink-0 overflow-hidden rounded-full w-10 h-10"
              >
                <el-avatar :src="fileUrl + item.avatarPath" />
              </span>
              <div class="flex-1">
                <p class="font-medium">{{ item.nickName }}</p>
                <p class="text-sm text-gray-500 dark:text-gray-400">
                  {{ item.lastMessage }}
                </p>
              </div>
              <div class="text-xs text-gray-500 dark:text-gray-400">
                {{ item.lastMessageTime }}
              </div>
            </a>
          </div>
        </div>
      </div>
      <div class="flex flex-col h-full bg-white flex-1 rounded-lg">
        <div class="flex items-center justify-between border-b p-4">
          <div class="flex items-center gap-3">
            <span
              class="relative flex shrink-0 overflow-hidden rounded-full w-10 h-10"
            >
              <el-avatar
                :src="
                  fileUrl + currentSessionList[currentSessionIndex]?.avatarPath
                "
              />
            </span>
            <div>
              <p class="font-medium">
                {{ currentSessionList[currentSessionIndex]?.nickName }}
              </p>
              <p class="text-sm text-gray-500 dark:text-gray-400">
                Last seen 2 hours ago
              </p>
            </div>
          </div>
          <div class="flex items-center gap-2">
            <button
              class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-xl font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
            >
              <icon-solar:phone-outline />
            </button>
            <button
              class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-xl font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
            >
              <icon-gala:video />
            </button>
          </div>
        </div>
        <div class="flex-1 overflow-y-auto p-4">
          <div class="grid gap-4">
            <template v-for="item in messages" :key="item">
              <div class="flex justify-end" v-if="item.position == 'right'">
                <div
                  class="bg-gray-900 text-gray-50 dark:bg-gray-50 dark:text-gray-900 rounded-full flex items-center px-4 py-2 max-w-[65%] text-sm"
                >
                  {{ item.content }}
                </div>
                <span
                  class="relative flex shrink-0 overflow-hidden rounded-full ml-2"
                >
                  <el-avatar :src="fileUrl + userInfo.avatarPath" />
                </span>
              </div>
              <div class="flex" v-else>
                <span
                  class="relative flex shrink-0 overflow-hidden rounded-full mr-2"
                >
                  <el-avatar
                    :src="
                      fileUrl +
                      currentSessionList[currentSessionIndex].avatarPath
                    "
                  />
                </span>
                <div
                  class="bg-gray-100 dark:bg-gray-800 rounded-full px-4 py-2 max-w-[65%] text-sm flex items-center"
                >
                  {{ item.content }}
                </div>
              </div>
            </template>
          </div>
        </div>
        <div class="border-t p-4">
          <el-form class="flex items-center gap-2">
            <el-input
              v-model="textarea"
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              clearable
              @keyup.enter="sendData"
            />
            <el-button type="primary" class="ml-3">
              <div class="items-center flex gap-1">
                <icon-mingcute:send-line />
                发送
              </div>
            </el-button>
            <icon-ri:emotion-happy-line class="text-xl" />
            <icon-material-symbols:add class="text-2xl" />
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>
