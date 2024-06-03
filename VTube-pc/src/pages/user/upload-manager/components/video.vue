<script setup lang="ts">
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
      <div class="flex flex-col gap-4 justify-between">
        <div
          v-for="item in list"
          :key="item.videoId"
          class="flex flex-col md:flex-row items-center justify-between gap-3 w-full md:w-auto"
        >
          <div class="flex gap-2 items-center">
            <el-image
              class="w-full md:w-40 md:h-24 rounded-lg"
              :src="fileUrl + item.imagePath"
            />
            <div class="flex flex-col mt-2 md:mt-0">
              <span>{{ item.title }}</span>
              <span class="text-sm text-gray-400">{{ item.createTime }}</span>
              <div class="flex items-center text-sm text-gray-400">
                <span>{{ item.views }} views</span>
                <span>{{ item.likes }} likes </span>
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
