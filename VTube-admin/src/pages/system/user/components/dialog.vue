<script setup lang="ts">
import { DialogState } from '@/interface/pages/dialog'
import { User } from '@/interface/pages/system'
const dialogDisplay = ref(false)
const acceptParam = ref<DialogState<User>>({
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
  httpPost(acceptParam.value.api, acceptParam.value.model).then(
    ({ data, code }) => {
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

defineExpose({ dialogDisplay, openDialog })
</script>
<template>
  <el-dialog class="!rounded-lg" v-model="dialogDisplay">
    <template #header>
      {{ acceptParam?.title }}
    </template>
    <div class="p-4">
      <el-form class="w-full" action="javascript:;" label-width="auto">
        <el-form-item label="头像" prop="avatarPath">
          <vt-upload-avatar
            v-model="acceptParam.model.avatarPath"
            :disabled="acceptParam.disabled"
            class="shadow-sm"
          />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input
            v-model="acceptParam.model.userName"
            placeholder="请输入用户名"
            clearable
          />
        </el-form-item>

        <el-form-item label="真实姓名">
          <el-input
            v-model="acceptParam.model.realName"
            placeholder="请输入真实姓名"
            clearable
          />
        </el-form-item>

        <el-form-item label="用户昵称">
          <el-input
            v-model="acceptParam.model.nickName"
            placeholder="请输入用户昵称"
            clearable
          />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input
            v-model="acceptParam.model.phone"
            placeholder="请输入手机号"
            clearable
          />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input
            v-model="acceptParam.model.email"
            placeholder="请输入邮箱"
            clearable
          />
        </el-form-item>

        <el-form-item label="性别">
          <el-select
            v-model="acceptParam.model.sex"
            :disabled="acceptParam.disabled"
            class="shadow-sm"
            placeholder="请选择性别"
          >
            <el-option
              v-for="(col, index) in acceptParam.enumMap?.get('sex')"
              :key="index"
              :label="col.label"
              :value="col.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-switch
            v-model="acceptParam.model.status"
            style="--el-switch-on-color: #13ce66"
            active-value="1"
            inactive-value="0"
          />
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker
            v-model="acceptParam.model.createTime"
            class="shadow-sm"
            type="date"
            :disabled="acceptParam.disabled"
            value-format="YYYY-MM-DD"
            date-format="YYYY/MM/DD"
            placeholder="请选择日期"
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
