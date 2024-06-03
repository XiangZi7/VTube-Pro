<script setup lang="ts">
import type { FormInstance } from 'element-plus'
const route = useRoute()
const ruleFormRef = ref<FormInstance>()
const model = ref({})
const episodeList = ref([])
const rules = {
  imagePath: [
    {
      required: true,
      message: '请上传封面',
      trigger: 'change',
    },
  ],
  type: [
    {
      required: true,
      message: '请选择类别',
      trigger: 'change',
    },
  ],
  categoryId: [
    {
      required: true,
      message: '请选择分类',
      trigger: 'change',
    },
  ],
  tags: [
    {
      required: true,
      message: '请输入标签',
      trigger: 'change',
    },
  ],
  description: [
    {
      required: true,
      message: '请输入简介',
      trigger: 'change',
    },
  ],
  title: [
    {
      required: true,
      message: '请输入标题',
      trigger: 'change',
    },
  ],
}

onMounted(() => {
  console.log('🚀 => route:', route.query.type == 'edit')
  if (route.query.type == 'edit') {
    httpPost('/manuscript/queryByVideoId', {
      videoId: route.query.videoId,
    }).then(({ data }) => {
      // console.log(res)
      model.value = data
    })
  }
})

async function addVideo() {
  await ruleFormRef.value?.validate((valid) => {
    if (!valid) return

    const videoPayload = {
      video: model.value,
      categoryId: model.value.categoryId,
      episodeList: episodeList.value,
    }

    httpPost('/manuscript/addVideo', videoPayload).then(({ code, data }) => {
      if (code !== 200) return
      messagePro(code, data as string)
    })
  })
}
</script>
<template>
  <div class="py-12 px-4 sm:px-6 lg:px-8 w-full flex">
    <div class="w-2/3">
      <div class="space-y-6">
        <div
          class="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 space-y-6"
        >
          <el-form
            ref="ruleFormRef"
            :model="model"
            label-width="auto"
            :rules="rules"
          >
            <el-form-item label="封面" prop="imagePath">
              <vt-upload v-model="model.imagePath" />
            </el-form-item>
            <el-form-item label="标题" prop="title">
              <el-input
                v-model="model.title"
                placeholder="请输入标题"
                show-word-limit
                maxlength="80"
                clearable
              />
            </el-form-item>
            <el-form-item label="类型" prop="type">
              <vt-select v-model="model.type" dict="video_type" />
            </el-form-item>
            <el-form-item label="分区" prop="categoryId">
              <el-input
                v-model="model.categoryId"
                placeholder="请选择分区"
                clearable
              />
            </el-form-item>
            <el-form-item label="标签" prop="tags">
              <el-input v-model="model.tags" placeholder="请输入标签" clearable>
                <template #suffix> 还能输入10个标签 </template>
              </el-input>
            </el-form-item>
            <el-form-item label="简介" prop="description">
              <el-input
                type="textarea"
                v-model="model.description"
                :autosize="{ minRows: 4 }"
                placeholder="请输入简介"
                show-word-limit
                maxlength="2000"
                clearable
              />
            </el-form-item>
          </el-form>

          <div class="flex justify-end">
            <el-button type="primary" @click="addVideo"> 确定 </el-button>
          </div>
        </div>
      </div>
    </div>
    <div class="w-1/3 px-5">
      <div class="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 space-y-6">
        <vt-video-upload v-model="episodeList" />
      </div>
    </div>
  </div>
</template>
