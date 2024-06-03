<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { password } from '@/interface/pages/forum'
const rules = {}
const userStore = useUserStore()
const form = ref(userStore.userInfo)
const PassWord = ref<password>({
  oldPassWord: '',
  newPassWord: '',
})

function save() {
  httpPost('/user/edit', form.value).then(({ data, code }) => {
    if (!data) return
    userStore.setUserInfo(form.value)
    messagePro(code, data ? '修改成功' : '修改失败')
  })
}

function changePassWord() {
  httpPost('/user/changePwd', PassWord.value).then(
    ({ code, data, message }) => {
      messagePro(code, data as string, message)
    }
  )
}
</script>
<template>
  <main
    class="flex flex-1 h-[calc(100%-64px)] flex-col gap-4 p-4 md:gap-8 md:p-6"
  >
    <div
      class="block w-full h-full flex-1 box-border overflow-x-hidden duration-300 ease-in-out dark:text-neutral-200"
    >
      <div class="flex items-center gap-4">
        <button
          class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10"
        >
          <Icon icon="ion:md-arrow-round-back" />
          <span class="sr-only">Back</span>
        </button>
        <h1 class="font-semibold text-lg md:text-xl">个人信息</h1>
      </div>
      <div class="grid md:grid-cols-2 gap-6 mt-2">
        <div class="border border-gray-200 rounded-lg">
          <div class="flex flex-col space-y-1.5 p-6">
            <h3
              class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight"
            >
              个人信息
            </h3>
            <p class="text-sm text-muted-foreground">请填写以下信息</p>
          </div>
          <div class="p-6 space-y-4">
            <el-form :model="form" :rules="rules" label-width="auto">
              <div class="space-y-2">
                <el-form-item label="头像">
                  <vt-upload-avatar v-model="form.avatarPath" />
                </el-form-item>
              </div>
              <div class="grid grid-cols-2 gap-4">
                <div class="space-y-2">
                  <el-form-item label="用户名">
                    <el-input
                      v-model="form.userName"
                      placeholder="请输入"
                      clearable
                    />
                  </el-form-item>
                </div>
                <div class="space-y-2">
                  <el-form-item label="真实姓名">
                    <el-input
                      v-model="form.realName"
                      placeholder="请输入"
                      clearable
                    />
                  </el-form-item>
                </div>
              </div>
              <div class="grid grid-cols-2 gap-4">
                <div class="space-y-2">
                  <el-form-item label="昵称">
                    <el-input
                      v-model="form.nickName"
                      placeholder="请输入昵称"
                      clearable
                    />
                  </el-form-item>
                </div>
                <div class="space-y-2">
                  <el-form-item label="性别">
                    <vt-select v-model="form.sex" dict="gender" />
                  </el-form-item>
                </div>
              </div>
              <div class="grid grid-cols-2 gap-4">
                <div class="space-y-2">
                  <el-form-item label="电话号码">
                    <el-input
                      v-model="form.phone"
                      placeholder="请输入"
                      clearable
                    />
                  </el-form-item>
                </div>
                <div class="space-y-2">
                  <el-form-item label="电子邮箱">
                    <el-input
                      v-model="form.email"
                      placeholder="请输入"
                      clearable
                    />
                  </el-form-item>
                </div>
              </div>
            </el-form>
          </div>
          <div class="flex items-center p-6">
            <el-button
              class="text-sm font-medium hover:bg-primary/90 h-10 px-4 py-2 w-full"
              type="primary"
              @click="save"
            >
              提交
            </el-button>
          </div>
        </div>
        <div
          class="border border-gray-200 dark:bg-white-800/10 rounded-lg shadow-medium transition-transform-background"
        >
          <div class="flex flex-col space-y-1.5 p-6">
            <h3
              class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight"
            >
              账号信息
            </h3>
            <p class="text-sm text-muted-foreground">管理您的账号信息</p>
          </div>
          <div class="p-6 space-y-4">
            <div class="space-y-2">
              <el-form-item label="旧密码">
                <el-input
                  v-model="PassWord.oldPassWord"
                  placeholder="请输入旧密码"
                  clearable
                />
              </el-form-item>
            </div>
            <div class="space-y-2">
              <el-form-item label="新密码">
                <el-input
                  v-model="PassWord.newPassWord"
                  placeholder="请输入新密码"
                  type="password"
                  clearable
                />
              </el-form-item>
            </div>
          </div>
          <div class="flex items-center p-6">
            <el-button type="primary" @click="changePassWord"> 保存 </el-button>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>
