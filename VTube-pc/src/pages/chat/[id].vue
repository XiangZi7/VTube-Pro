<!-- <script setup lang="ts">
const fileUrl = import.meta.env.VITE_API_MINIO
const userStore = useUserStore()
const route = useRoute()

const userId = userStore.userInfo.userId
const token = userStore.userInfo.token
const avatarPath = userStore.userInfo.avatarPath
const nickName = userStore.userInfo.nickName
const toId = extractNumberFromMid(route.params.id as string)

let sendMessage, messages, addMessage

// 条件判断，在toId有值时才使用useWebSocket
if (toId) {
  // ;({ sendMessage, messages, addMessage } = useWebSocket(
  //   `${import.meta.env.VITE_APP_WS}/chat/${userId}_${toId}?token=${token}&userId=${userId}`
  // ))
  ;({ sendMessage, messages, addMessage } = useWebSocket(
    `${import.meta.env.VITE_APP_WS}/chat`
  ))
} else {
  messages = ref([])
}
// 哨兵数据
const textarea = ref('')

function sendData() {
  if (!toId) return messagePro(300, '请选择会话!')

  let message = JSON.stringify({
    to: toId,
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
</script>
<template>
  <div
    class="h-full max-w-[1200px] mx-auto flex items-center justify-center box-border"
  >
    <div
      class="bg-white dark:bg-gray-800 flex w-full h-[80%] border box-border shadow-lg rounded-lg"
    >
      <div class="border-r">
        <div class="flex items-center justify-between p-4 border-b">
          <div class="flex items-center gap-3">
            <span
              class="relative flex shrink-0 overflow-hidden rounded-full w-10 h-10"
            >
              <el-avatar :src="fileUrl + avatarPath" />
            </span>
            <div>
              <p class="font-medium">{{ nickName }}</p>
              <p class="text-sm text-gray-500 dark:text-gray-400">Online</p>
            </div>
          </div>
        </div>
        <div class="p-4">
          <el-input placeholder="Search chats" />
        </div>
        <div class="overflow-y-auto">
          <div class="grid gap-2 p-4">
            <a
              href="#"
              class="flex items-center gap-3 rounded-lg p-2 hover:bg-gray-200 dark:hover:bg-gray-700"
            >
              <span
                class="relative flex shrink-0 overflow-hidden rounded-full w-10 h-10"
              >
                <img
                  class="aspect-square h-full w-full"
                  alt="User Avatar"
                  src="https://generated.vusercontent.net/placeholder.svg"
                />
              </span>
              <div class="flex-1">
                <p class="font-medium">Sarah Lee</p>
                <p class="text-sm text-gray-500 dark:text-gray-400">
                  Attached the latest design files.
                </p>
              </div>
              <div class="text-xs text-gray-500 dark:text-gray-400">
                11:30 AM
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
              <img
                class="aspect-square h-full w-full"
                alt="User Avatar"
                src="https://generated.vusercontent.net/placeholder.svg"
              />
            </span>
            <div>
              <p class="font-medium">John Doe</p>
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
                  <el-avatar :src="fileUrl + avatarPath" />
                </span>
              </div>
              <div class="flex" v-else>
                <span
                  class="relative flex shrink-0 overflow-hidden rounded-full mr-2"
                >
                  <el-avatar :src="fileUrl + avatarPath" />
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
          <form class="flex items-center gap-2">
            <el-input
              v-model="textarea"
              type="textarea"
              :rows="2"
              placeholder="请输入"
              clearable
              @keyup.enter="sendData"
            />
            <button
              class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
            >
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
                class="h-5 w-5"
              >
                <path
                  d="m21.44 11.05-9.19 9.19a6 6 0 0 1-8.49-8.49l8.57-8.57A4 4 0 1 1 18 8.84l-8.59 8.57a2 2 0 0 1-2.83-2.83l8.49-8.48"
                ></path>
              </svg>
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template> -->
