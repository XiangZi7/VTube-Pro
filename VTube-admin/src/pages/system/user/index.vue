<script setup lang="ts">
import { TableColumn, TableRef } from '@/interface/components/vt-table-v2.ts'
import { Icon } from '@iconify/vue'
import Dialog from './components/dialog.vue'
import { VideoVO } from '@/interface/pages/video'
const fileUrl = import.meta.env.VITE_API_MINIO

const DialogRef = ref<InstanceType<typeof Dialog> | null>(null)
const vtTable = ref<TableRef>()

const tableColumn = ref<TableColumn[]>([
  { type: 'selection', fixed: 'left', width: 70 },
  { prop: 'userId', label: 'id' },
  { prop: 'avatarPath', label: '头像', slotName: 'imagePath' },
  { prop: 'userName', label: '用户名', search: { el: 'input' } },
  { prop: 'realName', label: '真实姓名', search: { el: 'input' } },
  { prop: 'nickName', label: '昵称', search: { el: 'input' } },
  {
    prop: 'sex',
    label: '性别',
    enum: 'gender',
    search: { el: 'select', props: { filterable: true } },
  },
  {
    prop: 'status',
    label: '用户状态',
    search: { el: 'select' },
    slotName: 'status',
  },
  { prop: 'phone', label: '电话号码', search: { el: 'input' } },
  { prop: 'email', label: '电子邮箱', search: { el: 'input' } },
  {
    prop: 'createTime',
    label: '创建时间',
    search: {
      el: 'date-picker',
      props: {
        valueFormat: 'YYYY-MM-DD',
        dateFormat: 'YYYY/MM/DD',
      },
    },
  },
  { prop: '', label: '操作', width: 150, fixed: 'right', slotName: 'action' },
])

// 打开对话框
const openDialog = (title: string, row: Partial<VideoVO> = {}) => {
  const props = {
    title,
    model: row,
    enumMap: vtTable.value?.enumMap,
    disabled: title === '查看',
    api: title === '新增' ? '/user/add' : '/user/edit',
    getTableList: vtTable.value?.getTableList,
  }
  DialogRef.value?.openDialog(props)
}
// 删除
const deletes = async (id: number | (number | string)[]) => {
  // 检查传入参数类型，统一处理为数组
  const ids = Array.isArray(id) ? id : [id]

  // 发出HTTP请求
  const { code, data, message } = await httpPost('/user/deletes', ids)

  // 弹出消息
  messagePro(code, data as string, message)

  // 检查响应状态码
  if (code !== 200) return

  // 更新表格
  if (vtTable.value?.getTableList) {
    vtTable.value.getTableList()
  }
}

// 重置用户密码
function resetPwd(userId: string) {
  httpPost('/user/reset', { userId }).then(({ code, data, message }) => {
    // 弹出消息
    messagePro(code, data as string, message)

    // 检查响应状态码
    if (code !== 200) return

    // 更新表格
    if (vtTable.value?.getTableList) {
      vtTable.value.getTableList()
    }
  })
}
</script>
<template>
  <div class="flex flex-col h-full">
    <div
      class="h-full w-full p-2 box-border flex flex-1 flex-col shadow-app rounded-2xl bg-gray-400/20 dark:bg-white-800/10 gap-2"
    >
      <vt-table-v2
        ref="vtTable"
        api="/user/list"
        :columns="tableColumn"
        class="!flex-1"
        row-key="userId"
      >
        <template #btn="{ isSelected, selectedListIds }">
          <div class="p-2 space-x-2">
            <el-button
              @click="openDialog('新增')"
              type="primary"
              class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3"
            >
              新增
            </el-button>
            <el-popconfirm
              title="确定要批量删除这些数据？"
              @confirm="deletes(selectedListIds)"
            >
              <template #reference>
                <el-button
                  :disabled="!isSelected"
                  type="danger"
                  class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3"
                >
                  批量删除
                </el-button>
              </template>
            </el-popconfirm>
          </div>
        </template>
        <template #imagePath="{ row }">
          <el-image
            class="rounded-xl"
            preview-teleported
            :src="fileUrl + row.avatarPath"
            :preview-src-list="[fileUrl + row.avatarPath]"
            fit="fill"
          />
        </template>
        <template #status="{ row }">
          <el-switch
            v-model="row.status"
            style="--el-switch-on-color: #13ce66"
            disabled
            active-value="1"
            inactive-value="0"
          />
        </template>
        <template #action="{ row }">
          <div class="space-x-2 flex text-xl justify-around">
            <Icon
              icon="ic:round-remove-red-eye"
              class="cursor-pointer"
              @click="openDialog('查看', row)"
            />
            <Icon
              icon="material-symbols-light:edit-square-outline-rounded"
              class="cursor-pointer"
              @click="openDialog('编辑', row)"
            />
            <el-popconfirm
              :title="`确定要重置《${row.realName}?》的密码？`"
              @confirm="resetPwd(row.userId)"
            >
              <template #reference>
                <Icon
                  icon="material-symbols-light:lock-reset"
                  class="cursor-pointer"
                />
              </template>
            </el-popconfirm>
            <el-popconfirm
              :title="`确定要删除《${row.realName}?》`"
              @confirm="deletes(row.userId)"
            >
              <template #reference>
                <Icon icon="mdi:trash-can-outline" class="cursor-pointer" />
              </template>
            </el-popconfirm>
          </div>
        </template>
      </vt-table-v2>
    </div>
  </div>
  <Dialog ref="DialogRef" />
</template>
