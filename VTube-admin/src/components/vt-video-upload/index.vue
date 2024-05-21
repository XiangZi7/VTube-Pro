<script setup lang="ts">
import { ElNotification } from 'element-plus'
import draggable from 'vuedraggable'
import { Icon } from '@iconify/vue'
import axios from 'axios'
const videoUrl = defineModel()
defineProps({
  disabled: {
    type: Boolean,
    default: false,
  },
})
const state = reactive({
  enabled: true,
  drag: false,
})
const { fileList } = toRefs(state)

// 视频上传成功提示
function uploadSuccess() {
  ElNotification.success({
    title: '温馨提示',
    message: '视频上传成功！',
  })
}

// 视频上传失败提示
function uploadError() {
  ElNotification.error({
    title: '温馨提示',
    message: '视频上传失败，请您重新上传！',
  })
}

// // 上传之前的校验
// function beforeUpload(file) {
//   const isJPG = file.type === 'video/mp4'
//   const isLt2M = file.size / 1024 / 1024 < 50

//   if (!isJPG) {
//     ElNotification.error('上传视频只能是 video/mp4 格式!')
//   }
//   if (!isLt2M) {
//     ElNotification.error('上传视频大小不能超过 50MB!')
//   }
//   return isJPG && isLt2M
// }

// 上传方法
const uploadPercentage = ref(0)
const uploading = ref(false)
function handleHttpUpload(options) {
  let formData = new FormData()
  formData.append('file', options.file)
  uploading.value = true
  axios({
    url: import.meta.env.VITE_APP_BASE_API + '/uploadFile',
    method: 'post',
    data: formData,
    headers: { 'Content-Type': 'multipart/form-data' },
    onUploadProgress: (progressEvent) => {
      uploadPercentage.value = Math.round(
        (progressEvent.loaded / progressEvent.total) * 100
      )
    },
  })
    .then((res) => {
      videoUrl.value.push({
        title: '',
        videoPath: res.data.data,
        episodeNumber: videoUrl.value.length + 1,
      })
    })
    .finally(() => {
      uploading.value = false
      uploadPercentage.value = 0
    })
}

// 删除操作
function deleteXJ(index) {
  videoUrl.value.splice(index, 1)
}
</script>
<template>
  <div>
    <el-upload
      drag
      action="#"
      :multiple="false"
      :show-file-list="false"
      :on-success="uploadSuccess"
      :on-error="uploadError"
      :http-request="handleHttpUpload"
      :file-list="fileList"
      accept="video/*"
      :disabled="disabled"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <template #tip>
        <div class="el-upload__tip">只能上传视频文件</div>
      </template>
    </el-upload>
    <el-progress
      v-if="uploading"
      :percentage="uploadPercentage"
      status="success"
    />
    <el-scrollbar height="400px">
      <draggable
        sort="true"
        :list="videoUrl"
        item-key="id"
        :force-fallback="true"
      >
        <template #item="{ element, index }">
          <div class="marc-box">
            <div class="flex items-center gap-2 my-4">
              <span class="w-6">
                {{ index + 1 }}
              </span>
              <Icon icon="solar:videocamera-record-bold" />
              {{ element.name }}
              <el-input
                v-model="videoUrl[index].title"
                placeholder="请输入标题"
                :disabled="disabled"
              />
              <div>
                <Icon
                  v-if="!disabled"
                  icon="solar:trash-bin-minimalistic-2-broken"
                  @click="deleteXJ(index)"
                  class="cursor-pointer"
                />
              </div>
            </div>
          </div>
        </template>
      </draggable>
    </el-scrollbar>
  </div>
</template>
