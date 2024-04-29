<script setup lang="ts">
import VtDialog from '@/components/vt-dialog/index.vue'

const dialogVisible = defineModel<boolean>()

// tabs
const currentTabsIndex = ref<string>('sign In')
// Account
const initialModel = ref({
  email: '',
  password: '',
  username: '',
})

const validatorRules = {
  username: [{ required: true, message: 'Username is required' }],
  email: [{ required: true, message: 'Email is required' }],
  password: [{ required: true, message: 'Password is required' }],
}

const {
  model: formModel,
  errors: formErrors,
  validateForm,
} = useFormValidation(initialModel.value, validatorRules)

const handleSubmit = () => {
  validateForm()

  // eslint-disable-next-line no-restricted-syntax
  for (const key in formErrors.value) {
    if (formErrors.value[key]) {
      // 根据需要处理表单有错误的情况
      return
    }
  }

  // 提交完成后重置表单
  formModel.value = Object.keys(initialModel).reduce(
    (acc, key) => {
      acc[key] = ''
      return acc
    },
    {} as Record<string, string>
  )
}

const switchTabs = (tab: string) => {
  currentTabsIndex.value = tab
}
</script>
<template>
  <VtDialog v-model="dialogVisible" :is-header="false">
    <template #content>
      <div class="w-full max-w-md mx-auto">
        <div
          role="tablist"
          class="h-9 items-center justify-center rounded-lg bg-gray-500/20 p-1 text-muted-foreground grid grid-cols-2 gap-2"
          style="outline: none"
        >
          <button
            @click="switchTabs('sign In')"
            :class="{ 'bg-white text-black': currentTabsIndex == 'sign In' }"
            class="inline-flex items-center justify-center whitespace-nowrap rounded-md px-3 py-1 text-sm font-medium ring-offset-background transition-all focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50"
          >
            Sign In
          </button>
          <button
            @click="switchTabs('sign Up')"
            :class="{ 'bg-white text-black': currentTabsIndex == 'sign Up' }"
            class="inline-flex items-center justify-center whitespace-nowrap rounded-md px-3 py-1 text-sm font-medium ring-offset-background transition-all focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50"
          >
            Sign Up
          </button>
        </div>
        <div
          class="mt-2 ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2"
          style="animation-duration: 0s"
        >
          <div
            class="rounded-lg border bg-card text-card-foreground shadow-sm space-y-4"
          >
            <div class="flex flex-col space-y-1.5 p-6">
              <h3
                class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight"
              >
                {{ currentTabsIndex }}
              </h3>
              <p class="text-sm text-muted-foreground">
                Enter your email and password to access your account.
              </p>
            </div>
            <div class="p-6 space-y-4">
              <div class="space-y-2" v-if="currentTabsIndex == 'sign Up'">
                <label
                  class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                  for="username"
                >
                  Name
                </label>
                <input
                  class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  placeholder="UserName"
                  id="username"
                  type="text"
                  v-model="formModel.username"
                />
                <p class="text-red-500" v-if="formErrors.username">
                  {{ formErrors.username }}
                </p>
              </div>
              <div class="space-y-2">
                <label
                  class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                  for="email"
                >
                  Email
                </label>
                <input
                  class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  id="email"
                  placeholder="name@example.com"
                  type="email"
                  v-model="formModel.email"
                />
                <p class="text-red-500" v-if="formErrors.email">
                  {{ formErrors.email }}
                </p>
              </div>
              <div class="space-y-2">
                <label
                  class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                  for="password"
                >
                  Password
                </label>
                <input
                  class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  id="password"
                  placeholder="••••••••"
                  type="password"
                  v-model="formModel.password"
                />
                <p class="text-red-500" v-if="formErrors.password">
                  {{ formErrors.password }}
                </p>
              </div>
            </div>
            <div class="flex items-center p-6">
              <button
                class="bg-black text-white inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-4 py-2 w-full"
                type="submit"
                @click="handleSubmit"
              >
                Sign In
              </button>
            </div>
          </div>
        </div>
        <div
          class="mt-2 ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2"
        ></div>
      </div>
    </template>
  </VtDialog>
</template>
