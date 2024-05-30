<script setup lang="ts">
import { DialogState } from '@/interface/pages/dialog'
import { Menu } from '@/interface/pages/system'
const dialogDisplay = ref(false)
const acceptParam = ref<DialogState<Menu>>({
  title: '',
  model: {},
  disabled: false,
  api: '',
})
const treeData = ref([])
// 打开对话框
function openDialog(params: DialogState) {
  dialogDisplay.value = true
  acceptParam.value = params

  // 默认一级菜单
  if (acceptParam.value.title == '新增') {
    acceptParam.value.model.type = 0
    acceptParam.value.model.isKeepAlive = 0
    acceptParam.value.model.isFull = 0
  }

  // 获取菜单全部数据
  httpGet('/menu/allMenuData').then(({ data }) => {
    treeData.value = buildSelectMenu(data)
  })
}

// 提交表单
function submit() {
  const flag = acceptParam.value.title
  httpPost(
    flag == '编辑' ? '/menu/editMenu' : '/menu/addMenu',
    acceptParam.value?.model
  ).then(({ code, data }) => {
    if (code !== 200) return
    messagePro(code, data as string)

    if (acceptParam.value?.getTableList) {
      // 重新获取表单数据
      acceptParam.value.getTableList()
    }
    dialogDisplay.value = false
  })
  console.log('🚀 => acceptParam.value.model:', acceptParam.value.model)
}

defineExpose({ dialogDisplay, openDialog })
</script>
<template>
  <el-dialog
    class="!rounded-lg"
    v-model="dialogDisplay"
    @close="acceptParam.model = {}"
  >
    <template #header>
      {{ acceptParam?.title }}
    </template>
    <div class="p-4">
      <el-form label-width="auto" action="javascript:;">
        <div class="flex flex-col gap-5">
          <el-form-item label="菜单类型">
            <el-radio-group v-model="acceptParam.model.type">
              <el-radio :value="0">一级菜单</el-radio>
              <el-radio :value="1">子菜单</el-radio>
              <el-radio :value="2">按钮/权限</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item
            :label="acceptParam.model.type == 2 ? '按钮/权限' : '菜单名称'"
          >
            <el-input
              v-model="acceptParam.model!.title"
              :placeholder="`请输入${acceptParam.model.type == 2 ? '按钮/权限' : '菜单名称'}`"
              clearable
            />
          </el-form-item>
          <el-form-item label="上级菜单" v-if="acceptParam.model.type != 0">
            <el-tree-select
              v-model="acceptParam.model!.parentId"
              :data="treeData"
              :render-after-expand="false"
              placeholder="请上级菜单"
            />
          </el-form-item>
          <el-form-item label="菜单路径">
            <el-input
              v-model="acceptParam.model!.path"
              placeholder="请输入菜单路径"
              clearable
            />
          </el-form-item>
          <el-form-item label="授权标识" v-if="acceptParam.model.type == 2">
            <el-input
              v-model="acceptParam.model!.permission"
              placeholder="请输入授权标识，如：user:list"
              clearable
            />
          </el-form-item>
          <template v-if="acceptParam.model.type != 2">
            <el-form-item label="前端组件">
              <el-input
                v-model="acceptParam.model!.component"
                placeholder="请输入前端组件"
                clearable
              />
            </el-form-item>
            <el-form-item
              label="默认跳转路径"
              v-if="acceptParam.model.type == 0"
            >
              <el-input
                v-model="acceptParam.model!.redirect"
                placeholder="请输入默认跳转路径"
                clearable
              />
            </el-form-item>
            <el-form-item label="菜单图标">
              <el-input
                v-model="acceptParam.model!.icon"
                placeholder="请输入菜单图标"
                clearable
              />
            </el-form-item>
            <el-form-item label="排序">
              <el-input
                v-model="acceptParam.model!.orderNum"
                placeholder="请输入排序"
                clearable
              />
            </el-form-item>
            <el-form-item label="是否路由菜单">
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="acceptParam.model!.isFull"
              />
            </el-form-item>
            <el-form-item label="隐藏路由">
              <el-switch
                :active-value="0"
                :inactive-value="1"
                v-model="acceptParam.model!.visible"
              />
            </el-form-item>
            <el-form-item label="是否缓存路由">
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="acceptParam.model!.isKeepAlive"
              />
            </el-form-item>
          </template>
        </div>

        <div class="flex w-auto justify-end">
          <el-button
            v-if="acceptParam.title !== '查看'"
            @click="submit"
            type="primary"
          >
            确定
          </el-button>
        </div>
      </el-form>
    </div>
  </el-dialog>
</template>
