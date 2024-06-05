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
const rules = {
  title: [
    {
      required: true,
      message: '请输入',
      trigger: 'change',
    },
  ],
  type: [
    {
      required: true,
      message: '请选择类型',
      trigger: 'change',
    },
  ],
  parentId: [
    {
      required: true,
      message: '请上级菜单',
      trigger: 'change',
    },
  ],
  path: [
    {
      required: true,
      message: '请输入菜单路径',
      trigger: 'change',
    },
  ],
  component: [
    {
      required: true,
      message: '前端组件',
      trigger: 'change',
    },
  ],
}
// 打开对话框
function openDialog(params: DialogState) {
  dialogDisplay.value = true
  acceptParam.value = params

  // 默认一级菜单
  if (acceptParam.value.title == '新增') {
    acceptParam.value.model.type = '0'
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
      <el-form label-width="auto" :model="acceptParam.model" :rules="rules">
        <div class="flex flex-col gap-5">
          <el-form-item label="菜单类型" prop="type">
            <el-radio-group
              v-model="acceptParam.model.type"
              :disabled="acceptParam.disabled"
            >
              <el-radio value="0">一级菜单</el-radio>
              <el-radio value="1">子菜单</el-radio>
              <el-radio value="2">按钮/权限</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item
            prop="title"
            :label="acceptParam.model.type === '2' ? '按钮/权限' : '菜单名称'"
          >
            <el-input
              v-model="acceptParam.model!.title"
              :disabled="acceptParam.disabled"
              :placeholder="`请输入${acceptParam.model.type === '2' ? '按钮/权限' : '菜单名称'}`"
              clearable
            />
          </el-form-item>
          <el-form-item
            label="上级菜单"
            v-if="acceptParam.model.type != '0'"
            prop="parentId"
          >
            <el-tree-select
              :check-strictly="true"
              v-model="acceptParam.model!.parentId"
              :data="treeData"
              :disabled="acceptParam.disabled"
              :expand-on-click-node="false"
              placeholder="请上级菜单"
            />
          </el-form-item>
          <el-form-item label="菜单路径" prop="path">
            <el-input
              v-model="acceptParam.model!.path"
              :disabled="acceptParam.disabled"
              placeholder="请输入菜单路径"
              clearable
            />
          </el-form-item>
          <el-form-item label="授权标识" v-if="acceptParam.model.type === '2'">
            <el-input
              v-model="acceptParam.model!.permission"
              :disabled="acceptParam.disabled"
              placeholder="请输入授权标识，如：user:list"
              clearable
            />
          </el-form-item>
          <template v-if="acceptParam.model.type != '2'">
            <el-form-item label="前端组件" prop="component">
              <el-input
                :disabled="acceptParam.disabled"
                v-model="acceptParam.model!.component"
                placeholder="请输入前端组件"
                clearable
              />
            </el-form-item>
            <el-form-item
              label="默认跳转路径"
              v-if="acceptParam.model.type == '0'"
            >
              <el-input
                v-model="acceptParam.model!.redirect"
                :disabled="acceptParam.disabled"
                placeholder="请输入默认跳转路径"
                clearable
              />
            </el-form-item>
            <el-form-item label="菜单图标">
              <el-input
                v-model="acceptParam.model!.icon"
                :disabled="acceptParam.disabled"
                placeholder="请输入菜单图标"
                clearable
              />
            </el-form-item>
            <el-form-item label="排序">
              <el-input
                v-model="acceptParam.model!.orderNum"
                :disabled="acceptParam.disabled"
                placeholder="请输入排序"
                clearable
              />
            </el-form-item>
            <el-form-item label="是否全屏页面">
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="acceptParam.model!.isFull"
                :disabled="acceptParam.disabled"
              />
            </el-form-item>
            <el-form-item label="隐藏路由">
              <el-switch
                :active-value="0"
                :inactive-value="1"
                v-model="acceptParam.model!.visible"
                :disabled="acceptParam.disabled"
              />
            </el-form-item>
            <el-form-item label="是否缓存路由">
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="acceptParam.model!.isKeepAlive"
                :disabled="acceptParam.disabled"
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
