<script setup lang="ts">
import { videoStatus } from '@/enums'
const fileUrl = import.meta.env.VITE_API_MINIO

const router = useRouter()
const state = reactive({
  list: [],
})
const { list } = toRefs(state)
onMounted(() => {
  httpGet('/manuscript/VideoManagementList').then(({ data }) => {
    state.list = data.records
  })
})

function edit(row: any) {
  router.push(`/user/upload/video?type=edit&videoId=${row.videoId}`)
}
</script>
<template>
  <div class="w-full h-full">
    <div class="bg-white p-4 rounded-lg">
      <div class="flex flex-col gap-4 justify-between h-full">
        <div
          v-for="item in list"
          :key="item.videoId"
          ref="target"
          class="flex flex-col md:flex-row items-center justify-between gap-3 w-full md:w-auto"
        >
          <div class="flex gap-4 items-center">
            <el-image
              class="w-full md:w-40 md:h-24 rounded-lg"
              :src="fileUrl + item.imagePath"
            />
            <div class="flex flex-col mt-2 md:mt-0 gap-2 h-full flex-1">
              <span>{{ item.title }}</span>
              <span class="text-xs text-gray-400">{{ item.createTime }}</span>
              <div class="flex items-center text-gray-400 gap-4 text-xs">
                <div class="flex items-center gap-1">
                  <icon-material-symbols-light:smart-display class="text-sm" />
                  <span> {{ item.views }} </span>
                </div>
                <div class="flex items-center gap-1">
                  <icon-icon-park-solid:like class="text-xs" />
                  <span> {{ item.likes }} </span>
                </div>
                <div class="flex items-center gap-1">
                  <el-tag :type="videoStatus[item.reviewStatus].type">
                    <p
                      :title="item.remark"
                      :class="
                        item.reviewStatus == '3'
                          ? 'cursor-help'
                          : 'cursor-default'
                      "
                    >
                      {{ videoStatus[item.reviewStatus].label }}
                    </p>
                  </el-tag>
                </div>
              </div>
            </div>
          </div>
          <div class="mt-4 md:mt-0">
            <el-button type="primary" @click="edit(item)">编辑</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
