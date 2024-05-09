<script setup lang="ts">
import { VideoVO } from '@/interface/pages/video.ts'
import { DialogState } from '@/interface/pages/dialog'

const dialogDisplay = ref(false)

const acceptParam = ref<DialogState<Partial<VideoVO>>>()

function openDialog(params: DialogState) {
  dialogDisplay.value = true
  acceptParam.value = params
}

defineExpose({ dialogDisplay, openDialog })
</script>
<template>
  <vt-dialog v-model="dialogDisplay" width="900">
    <template #header>
      {{ acceptParam?.title }}
    </template>
    <div class="p-4">
      <form class="w-full" action="javascript:;">
        <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
          <div class="mb-4">
            <label for="title" class="block text-sm font-medium text-gray-700"
              >标题</label
            >
            <input
              v-model="acceptParam!.model.title"
              :disabled="acceptParam?.disabled"
              required
              id="title"
              name="title"
              class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-primary focus:border-primary sm:text-sm"
              placeholder="Enter your title"
            />
          </div>
          <div class="mb-4">
            <label
              for="description"
              class="block text-sm font-medium text-gray-700"
              >描述</label
            >
            <input
              v-model="acceptParam!.model.description"
              :disabled="acceptParam?.disabled"
              required
              id="description"
              name="description"
              class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-primary focus:border-primary sm:text-sm"
              placeholder="Enter your description"
            />
          </div>
          <div class="mb-4">
            <label for="views" class="block text-sm font-medium text-gray-700"
              >关注度</label
            >
            <input
              v-model="acceptParam!.model.views"
              :disabled="acceptParam?.disabled"
              id="views"
              required
              name="views"
              class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-primary focus:border-primary sm:text-sm"
              placeholder="Enter your views"
            />
          </div>
          <div class="mb-4">
            <label for="likes" class="block text-sm font-medium text-gray-700"
              >点赞</label
            >
            <input
              v-model="acceptParam!.model.likes"
              :disabled="acceptParam?.disabled"
              id="likes"
              required
              name="likes"
              class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-primary focus:border-primary sm:text-sm"
              placeholder="Enter your likes"
            />
          </div>
          <div class="mb-4">
            <label for="userId" class="block text-sm font-medium text-gray-700"
              >上传者</label
            >
            <input
              v-model="acceptParam!.model.userId"
              :disabled="acceptParam?.disabled"
              id="userId"
              required
              name="userId"
              class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-primary focus:border-primary sm:text-sm"
              placeholder="Enter your userId"
            />
          </div>
          <div class="mb-4">
            <label
              for="createTime"
              class="block text-sm font-medium text-gray-700"
              >创建时间</label
            >
            <input
              v-model="acceptParam!.model.createTime"
              :disabled="acceptParam?.disabled"
              type="datetime-local"
              id="createTime"
              required
              name="createTime"
              class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-primary focus:border-primary sm:text-sm"
              placeholder="Enter your createTime"
            />
          </div>
        </div>
        <div class="flex w-auto justify-end">
          <button
            type="submit"
            class="px-4 py-2 bg-primary text-white rounded-md hover:bg-primary/90 focus:outline-none focus:ring focus:ring-primary"
          >
            Submit
          </button>
        </div>
      </form>
    </div>
  </vt-dialog>
</template>
