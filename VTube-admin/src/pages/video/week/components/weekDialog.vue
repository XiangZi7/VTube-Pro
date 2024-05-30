<script setup lang="ts">
import { DialogState } from '@/interface/pages/dialog'
import { VideoVO } from '@/interface/pages/video'
import weekPopup from './weekPopup.vue'
const dialogDisplay = ref(false)
const acceptParam = ref<DialogState>({
  title: '',
  model: {},
  disabled: false,
  api: '',
})

// 打开对话框
function openDialog(params: DialogState) {
  dialogDisplay.value = true
  acceptParam.value = params
}

// 提交表单
function submit() {
  Object.assign(acceptParam.value?.model)
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

function callback(row: VideoVO) {
  acceptParam.value.model = row
}

defineExpose({ dialogDisplay, openDialog })
</script>
<template>
  <el-dialog class="!rounded-lg" v-model="dialogDisplay">
    <template #header>
      {{ acceptParam.title }}
    </template>
    <div class="p-4">
      <el-form class="w-full" action="javascript:;" label-width="auto">
        <div class="flex flex-col gap-5">
          <el-form-item label="选择番剧" v-if="acceptParam.title == '新增'">
            <weekPopup @callback="callback" />
          </el-form-item>
          <el-form-item label="封面" prop="imagePath">
            <vt-upload
              v-model="acceptParam.model.imagePath"
              :disabled="true"
              class="shadow-sm"
            />
          </el-form-item>
          <el-form-item label="标题" prop="title">
            <el-input
              v-model="acceptParam.model.title"
              :disabled="true"
              class="shadow-sm"
              placeholder="Enter your title"
            />
          </el-form-item>
          <el-form-item label="周" prop="weekDay">
            <el-select
              v-model="acceptParam.model.weekDay"
              :disabled="acceptParam.disabled"
              class="shadow-sm"
              placeholder="请选择分类"
            >
              <el-option
                v-for="(col, index) in acceptParam.enumMap?.get('weekDay')"
                :key="index"
                :label="col.label"
                :value="col.value"
              />
            </el-select>
          </el-form-item>
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
