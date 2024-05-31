<script setup lang="ts">
const { isDark, primary, language } = storeToRefs(useThemeStore())
const { switchDark, changePrimary } = useTheme()
const ThemeStore = useThemeStore()

function changeSetDark(val: string | number | boolean) {
  ThemeStore.setDark(val)
  switchDark()
}

// 更改主题
const changePrimarys = (val: string | null) => {
  if (val) {
    const resultHex = rgbaToHex(val as string)
    changePrimary(resultHex)
  } 
}
</script>
<template>
  <div class="flex flex-col w-full h-full rounded-xl">
    <div class="flex-1 py-8">
      <div class="container mx-auto px-4">
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          <div
            class="bg-gray-400/20 dark:bg-white-800/10 rounded-lg shadow-medium transition-transform-background"
          >
            <div class="p-6">
              <h2 class="text-lg font-bold mb-4">通知设置</h2>
              <div class="space-y-4">
                <div class="flex items-center justify-between">
                  <div>
                    <p class="text-gray-900 dark:text-gray-100 font-medium">
                      消息通知
                    </p>
                    <p class="text-gray-500 dark:text-gray-400 text-sm">
                      接收新消息的通知
                    </p>
                  </div>
                  <button
                    type="button"
                    role="switch"
                    class="peer inline-flex h-[24px] w-[44px] shrink-0 cursor-pointer items-center rounded-full border-2 border-transparent transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 focus-visible:ring-offset-background disabled:cursor-not-allowed disabled:opacity-50 data-[state=checked]:bg-primary data-[state=unchecked]:bg-input"
                  >
                    <span
                      data-state="unchecked"
                      class="pointer-events-none block h-5 w-5 rounded-full bg-background shadow-lg ring-0 transition-transform data-[state=checked]:translate-x-5 data-[state=unchecked]:translate-x-0"
                    ></span>
                  </button>
                </div>
                <div class="flex items-center justify-between">
                  <div>
                    <p class="text-gray-900 dark:text-gray-100 font-medium">
                      电子邮件订阅
                    </p>
                    <p class="text-gray-500 dark:text-gray-400 text-sm">
                      管理你的邮件订阅
                    </p>
                  </div>
                  <button
                    class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3"
                  >
                    管理
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div
            class="bg-gray-400/20 dark:bg-white-800/10 rounded-lg shadow-medium transition-transform-background"
          >
            <div class="p-6">
              <h2 class="text-lg font-bold mb-4">深色模式 & 主题颜色</h2>
              <div class="space-y-4">
                <div class="flex items-center justify-between">
                  <div>
                    <p class="text-gray-900 dark:text-gray-100 font-medium">
                      暗黑模式
                    </p>
                    <p class="text-gray-500 dark:text-gray-400 text-sm">
                      黑黑的也不错
                    </p>
                  </div>
                  <el-switch
                    v-model="isDark"
                    @change="changeSetDark"
                    style="--el-switch-on-color: #13ce66"
                  />
                </div>
                <div class="flex items-center justify-between">
                  <div>
                    <p class="text-gray-900 dark:text-gray-100 font-medium">
                      主题颜色
                    </p>
                    <p class="text-gray-500 dark:text-gray-400 text-sm">
                      根据自己爱好自定义主题颜色
                    </p>
                  </div>
                  <el-color-picker
                    v-model="primary"
                    show-alpha
                    @change="changePrimarys"
                  />
                </div>
              </div>
            </div>
          </div>
          <div
            class="bg-gray-400/20 dark:bg-white-800/10 rounded-lg shadow-medium transition-transform-background"
          >
            <div class="p-6">
              <h2 class="text-lg font-bold mb-4">私隐及安全</h2>
              <div class="space-y-4">
                <div class="flex items-center justify-between">
                  <div>
                    <p class="text-gray-900 dark:text-gray-100 font-medium">
                      账户安全
                    </p>
                    <p class="text-gray-500 dark:text-gray-400 text-sm">
                      管理您的帐户安全设置
                    </p>
                  </div>
                  <button
                    class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3"
                  >
                    管理
                  </button>
                </div>
                <div class="flex items-center justify-between">
                  <div>
                    <p class="text-gray-900 dark:text-gray-100 font-medium">
                      隐私政策
                    </p>
                    <p class="text-gray-500 dark:text-gray-400 text-sm">
                      私我们的私隐政策
                    </p>
                  </div>
                  <button
                    class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3"
                  >
                    View
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div
            class="bg-gray-400/20 dark:bg-white-800/10 rounded-lg shadow-medium transition-transform-background"
          >
            <div class="p-6">
              <h2 class="text-lg font-bold mb-4">其他设置</h2>
              <div class="space-y-4">
                <div class="flex items-center justify-between">
                  <div>
                    <p class="text-gray-900 dark:text-gray-100 font-medium">
                      语言
                    </p>
                    <p class="text-gray-500 dark:text-gray-400 text-sm">
                      更改应用程序的语言
                    </p>
                  </div>
                  <el-select v-model="language" placeholder="请选择语言">
                    <el-option label="English" value="English" />
                    <el-option label="中文简体" value="Chinese" />
                  </el-select>
                </div>
                <div class="flex items-center justify-between">
                  <div>
                    <p class="text-gray-900 dark:text-gray-100 font-medium">
                      Logout
                    </p>
                    <p class="text-gray-500 dark:text-gray-400 text-sm">
                      Sign out of your account
                    </p>
                  </div>
                  <button
                    class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3"
                  >
                    Logout
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
