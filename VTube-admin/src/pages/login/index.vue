<script setup lang="ts">
import type { FormInstance } from 'element-plus'
import { user } from '@/interface/store/userStore'
const router = useRouter()
const userStore = useUserStore()
const ruleFormRef = ref<FormInstance>()
const form = ref({
  userName: '',
  password: '',
})
const rules = ref({
  userName: [
    {
      required: true,
      message: '请输入用户名',
      trigger: 'change',
    },
  ],
  password: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'change',
    },
  ],
})

const login = async () => {
  await ruleFormRef.value?.validate((valid) => {
    if (!valid) return

    httpPost<user>('/login', form.value).then(({ data, code, message }) => {
      if (code !== 200) return messagePro(code, message)
      messagePro(code, data.realName as string)
      userStore.setUserInfo(data)
      router.push({
        path: '/dashboard',
      })
    })
  })
}
</script>
<template>
  <div
    class="flex min-h-screen items-center justify-center bg-gray-100 dark:bg-gray-950"
  >
    <div class="w-full max-w-md space-y-8">
      <div class="text-center">
        <h2
          class="text-3xl font-bold tracking-tight text-gray-900 dark:text-gray-50"
        >
          Sign in to your account
        </h2>
      </div>
      <div
        class="rounded-lg border bg-card text-card-foreground shadow-sm"
        data-v0-t="card"
      >
        <div class="p-6 space-y-4">
          <el-form
            ref="ruleFormRef"
            :model="form"
            :rules="rules"
            label-width="auto"
            label-position="top"
          >
            <div class="space-y-2">
              <el-form-item label="用户名" prop="userName">
                <el-input
                  v-model="form.userName"
                  placeholder="请输入"
                  @keyup.enter="login"
                  clearable
                />
              </el-form-item>
            </div>
            <div class="space-y-2">
              <el-form-item label="密码" prop="password">
                <el-input
                  v-model="form.password"
                  placeholder="请输入"
                  @keyup.enter="login"
                  type="password"
                  clearable
                />
              </el-form-item>
            </div>
          </el-form>
        </div>
        <div class="flex items-center p-6">
          <el-button
            type="primary"
            class="rounded-md text-sm font-medium hover:bg-primary/90 h-10 px-4 py-2 w-full"
            @click="login"
          >
            Sign in
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>
