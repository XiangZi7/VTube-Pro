<script setup lang="ts">
import { DialogState } from '@/interface/pages/dialog'

const dialogDisplay = ref(false)

const acceptParam = ref<DialogState>({
  title: '',
  model: undefined,
  disabled: false,
  api: '',
})

function openDialog(params: DialogState) {
  dialogDisplay.value = true
  acceptParam.value = params
}

function submit() {
  httpPost(acceptParam.value?.api as string, acceptParam.value?.model).then(
    ({ code, data }) => {
      messagePro(code, data as string)
      if (code !== 200) return

      if (acceptParam.value.getTableList) {
        acceptParam.value.getTableList()
      }

      dialogDisplay.value = false
    }
  )
}

defineExpose({ dialogDisplay, openDialog })
</script>
<template>
  <vt-dialog v-model="dialogDisplay" width="900">
    <template #header>
      {{ acceptParam?.title }}
    </template>
    <div class="p-4">
      <el-form label-position="top" class="w-full" action="javascript:;">
        <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
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
                  'categoryName'
                )"
                :key="index"
                :label="col.label"
                :value="col.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="观看量" prop="views">
            <el-input
              v-model="acceptParam!.model.views"
              :disabled="acceptParam?.disabled"
              class="shadow-sm"
              placeholder="Enter your views"
            />
          </el-form-item>
          <el-form-item label="收藏量" prop="likes">
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
                v-for="(col, index) in acceptParam?.enumMap?.get('userName')"
                :key="index"
                :label="col.label"
                :value="col.value"
              />
            </el-select>
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
        <div class="flex w-auto justify-end">
          <button
            v-if="acceptParam.title !== '查看'"
            type="submit"
            @click="submit"
            class="px-4 py-2 bg-primary text-white rounded-md hover:bg-primary/90 focus:outline-none focus:ring focus:ring-primary"
          >
            确定
          </button>
        </div>
      </el-form>
    </div>
  </vt-dialog>
</template>
