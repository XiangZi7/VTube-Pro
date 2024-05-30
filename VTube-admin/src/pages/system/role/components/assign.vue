<script setup lang="ts">
import { DialogState } from '@/interface/pages/dialog'
const dialogDisplay = ref(false)
const acceptParam = ref<DialogState>({
  title: '',
  model: {},
  disabled: false,
  api: '',
})
// 菜单树级数据
const tree = ref()
const treeData = ref([])
const defaultProps = {
  children: 'children',
  label: 'label',
}
// 打开对话框
function openDialog(params: DialogState) {
  dialogDisplay.value = true
  acceptParam.value = params
  tree.value?.setCheckedKeys([])

  if (acceptParam.value.model.roleId) {
    httpPost('/role/menu', { roleId: acceptParam.value.model.roleId }).then(
      ({ data }) => {
        const checkKey = data.map((item) => item.menuId)
        tree.value.setCheckedKeys(checkKey)
      }
    )
  }
  httpGet('/menu/allMenuData').then(({ data }) => {
    treeData.value = buildSelectMenu(data)
  })
}

// 提交表单
function submit() {
  httpPost('/role/assign', {
    roleId: acceptParam.value.model.roleId,
    menuId: tree.value.getCheckedKeys(),
  }).then(({ data, code }) => {
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
        <el-tree
          ref="tree"
          :data="treeData"
          :props="defaultProps"
          default-expand-all
          show-checkbox
          node-key="value"
        />
        <div class="flex w-auto justify-end mt-5">
          <el-button
            v-if="acceptParam.title !== '查看'"
            @click="submit"
            type="primary"
            class="px-4 py-2 bg-primary text-white rounded-md hover:bg-primary/90 focus:outline-none focus:ring focus:ring-primary"
          >
            确定
          </el-button>
        </div>
      </el-form>
    </div>
  </el-dialog>
</template>
