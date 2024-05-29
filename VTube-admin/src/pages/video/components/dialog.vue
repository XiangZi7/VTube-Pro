<script setup lang="ts">
import { DialogState } from '@/interface/pages/dialog'
import { Video } from '@/interface/pages/dialog'
const dialogDisplay = ref(false)
const episodeList = ref<Video[]>([])
const acceptParam = ref<DialogState>({
  title: '',
  model: undefined,
  disabled: false,
  api: '',
})

// 打开对话框
function openDialog(params: DialogState) {
  dialogDisplay.value = true
  acceptParam.value = params

  httpPost<Video[]>('/video/sublist', {
    videoId: acceptParam.value.model.videoId,
  }).then(({ data }) => {
    episodeList.value = data
  })
}

// 提交表单
function submit() {
  Object.assign(acceptParam.value?.model, { episodeList: episodeList.value })
  httpPost(acceptParam.value?.api as string, acceptParam.value?.model).then(
    ({ code, data }) => {
      if (code !== 200) return
      messagePro(code, data as string)

      if (acceptParam.value?.getTableList) {
        // 重新获取表单数据
        acceptParam.value.getTableList()
      }
      dialogDisplay.value = false
    }
  )
}

// tags
const tagsVal = ref('')
function creatTags() {
  if (acceptParam.value?.model?.tags) {
    const tagsList = acceptParam.value?.model?.tags.split(',')
    tagsList.push(tagsVal.value)
    acceptParam.value.model.tags = tagsList.join()
  } else {
    acceptParam.value.model.tags = tagsVal.value
  }
  tagsVal.value = ''
}
defineExpose({ dialogDisplay, openDialog })
</script>
<template>
  <el-dialog
    class="!rounded-lg"
    v-model="dialogDisplay"
    width="90%"
    @close="episodeList = []"
  >
    <template #header>
      {{ acceptParam?.title }}
    </template>
    <div class="p-4">
      <el-form label-position="top" class="w-full" action="javascript:;">
        <div class="flex gap-5">
          <div class="grid grid-cols-2 md:grid-cols-4 gap-4 w-2/3">
            <el-form-item label="封面" prop="imagePath">
              <vt-upload
                v-model="acceptParam!.model.imagePath"
                :disabled="acceptParam?.disabled"
                class="shadow-sm"
              />
            </el-form-item>
            <el-form-item label="标题" prop="title">
              <el-input
                v-model="acceptParam!.model.title"
                :disabled="acceptParam?.disabled"
                class="shadow-sm"
                placeholder="Enter your title"
              />
            </el-form-item>
            <el-form-item label="分类" prop="categoryId">
              <el-select
                v-model="acceptParam.model.categoryId"
                :disabled="acceptParam?.disabled"
                class="shadow-sm"
                placeholder="请选择分类"
              >
                <el-option
                  v-for="(col, index) in acceptParam?.enumMap?.get(
                    'categoryId'
                  )"
                  :key="index"
                  :label="col.label"
                  :value="col.value"
                />
              </el-select>
            </el-form-item>

            <el-form-item label="关注度" prop="views">
              <el-input
                v-model="acceptParam!.model.views"
                :disabled="acceptParam?.disabled"
                class="shadow-sm"
                placeholder="Enter your views"
              />
            </el-form-item>
            <el-form-item label="视频类型" prop="type">
              <el-select
                v-model="acceptParam.model.type"
                :disabled="acceptParam?.disabled"
                class="shadow-sm"
                placeholder="请选择分类"
              >
                <el-option
                  v-for="(col, index) in acceptParam?.enumMap?.get('type')"
                  :key="index"
                  :label="col.label"
                  :value="col.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="视频标签" prop="tags">
              <div class="flex gap-2">
                <el-input
                  v-model="tagsVal"
                  :disabled="acceptParam?.disabled"
                  class="shadow-sm"
                  placeholder="Enter your tags"
                  @keyup.enter="creatTags"
                />
                <el-button
                  type="primary"
                  @click="creatTags"
                  :disabled="acceptParam?.disabled"
                  >添加</el-button
                >
              </div>
              <div
                class="flex gap-2 flex-wrap pt-2"
                v-if="acceptParam!.model.tags"
              >
                <el-tag
                  type="success"
                  v-for="(item, index) in acceptParam!.model.tags.split(',')"
                  :key="index"
                  >{{ item }}</el-tag
                >
              </div>
            </el-form-item>
            <el-form-item label="点赞" prop="likes">
              <el-input
                v-model="acceptParam!.model.likes"
                class="shadow-sm"
                :disabled="acceptParam?.disabled"
                placeholder="Enter your likes"
              />
            </el-form-item>

            <el-form-item label="上传者" prop="userId">
              <el-select
                v-model="acceptParam!.model.userId"
                class="shadow-sm"
                :disabled="acceptParam?.disabled"
                placeholder="请选择用户"
              >
                <el-option
                  v-for="(col, index) in acceptParam?.enumMap?.get('userId')"
                  :key="index"
                  :label="col.label"
                  :value="col.value"
                />
              </el-select>
            </el-form-item>

            <el-form-item label="上映时间" prop="releaseTime">
              <el-date-picker
                v-model="acceptParam!.model.releaseTime"
                class="shadow-sm"
                type="date"
                :disabled="acceptParam?.disabled"
                value-format="YYYY-MM-DD"
                date-format="YYYY/MM/DD"
                placeholder="请选择日期"
              />
            </el-form-item>
            <el-form-item label="创建时间" prop="userId">
              <el-date-picker
                v-model="acceptParam!.model.createTime"
                class="shadow-sm"
                type="datetime"
                :disabled="acceptParam?.disabled"
                value-format="YYYY-MM-DD HH:mm:ss"
                date-format="YYYY/MM/DD ddd"
                time-format="A hh:mm:ss"
                placeholder="请选择日期"
              />
            </el-form-item>
            <el-form-item label="描述" prop="description">
              <el-input
                v-model="acceptParam!.model.description"
                :disabled="acceptParam?.disabled"
                type="textarea"
                :rows="2"
                class="shadow-sm"
                placeholder="请输入"
                clearable
              />
            </el-form-item>
          </div>
          <div class="w-1/3">
            <h2>视频集数</h2>
            <vt-video-upload
              :disabled="acceptParam?.disabled"
              v-model="episodeList"
            />
          </div>
        </div>

        <div class="flex w-auto justify-end">
          <el-button
            v-if="acceptParam.title !== '查看'"
            @click="submit"
            class="px-4 py-2 bg-primary text-white rounded-md hover:bg-primary/90 focus:outline-none focus:ring focus:ring-primary"
          >
            确定
          </el-button>
        </div>
      </el-form>
    </div>
  </el-dialog>
</template>
