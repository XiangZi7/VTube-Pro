<script setup lang="ts">
import { DialogState } from '@/interface/pages/dialog'
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
  httpPost(acceptParam.value.api, acceptParam.value.model).then(({ data, code }) => {
    if (code !== 200) return
    messagePro(code, data as string)
    if (acceptParam.value?.getTableList) {
      // 重新获取表单数据
      acceptParam.value.getTableList()
    }
    dialogDisplay.value = false
  })
}

defineExpose({ dialogDisplay, openDialog })
</script>
<template>
  <el-dialog class="!rounded-lg" v-model="dialogDisplay" @close="">
    <template #header>
      {{ acceptParam?.title }}
    </template>
    <div class="p-4">
      <el-form label-position="top" class="w-full" action="javascript:;">
        <el-form-item label="角色名称">
          <el-input
            v-model="acceptParam.model.name"
            placeholder="请输入角色名称"
            clearable
          />
        </el-form-item>
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
