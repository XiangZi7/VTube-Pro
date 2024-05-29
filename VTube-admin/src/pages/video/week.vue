<script setup lang="ts">
import { TableColumn, TableRef } from '@/interface/components/vt-table-v2.ts'
import { Icon } from '@iconify/vue'
import Dialog from './components/weekDialog.vue'
import { WeekVO } from '@/interface/pages/video'
import { weekDays } from '@/enums'
const fileUrl = import.meta.env.VITE_API_MINIO

const DialogRef = ref<InstanceType<typeof Dialog> | null>(null)
const vtTable = ref<TableRef>()

const tableColumn = ref<TableColumn[]>([
  { type: 'selection', fixed: 'left', width: 70 },
  // { prop: 'updateId', label: 'id' },
  { prop: 'imagePath', label: '封面', slotName: 'imagePath' },
  { prop: 'title', label: '标题', search: { el: 'input' } },
  {
    prop: 'weekDay',
    label: '周',
    enum: weekDays,
    search: { el: 'select', props: { filterable: true }, key: 'weekDay' },
  },
  { prop: '', label: '操作', width: 150, fixed: 'right', slotName: 'action' },
])

// 打开对话框
const openDialog = (title: string, row: Partial<WeekVO> = {}) => {
  const props = {
    title,
    model: row,
    enumMap: vtTable.value?.enumMap,
    disabled: title === '查看',
    api: title === '新增' ? '/video/weekAdd' : '/video/weekEdit',
    getTableList: vtTable.value?.getTableList,
  }
  DialogRef.value?.openDialog(props)
}
// 删除
const deletes = async (id: number | (number | string)[]) => {
  // 检查传入参数类型，统一处理为数组
  const ids = Array.isArray(id) ? id : [id]

  // 发出HTTP请求
  const { code, data, message } = await httpPost('/video/weekDeletes', ids)

  // 弹出消息
  messagePro(code, data as string, message)

  // 检查响应状态码
  if (code !== 200) return

  // 更新表格
  if (vtTable.value?.getTableList) {
    vtTable.value.getTableList()
  }
}
</script>
<template>
  <div class="flex flex-col h-full">
    <div
      class="h-full w-full p-2 box-border flex flex-1 flex-col shadow-app rounded-2xl bg-gray-400/20 dark:bg-white-800/10 gap-2"
    >
      <vt-table-v2
        ref="vtTable"
        api="/video/weekList"
        :columns="tableColumn"
        class="!flex-1"
        row-key="videoId"
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
                <button
                  :disabled="!isSelected"
                  class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3"
                >
                  批量删除
                </button>
              </template>
            </el-popconfirm>
          </div>
        </template>
        <template #imagePath="{ row }">
          <el-image
            class="rounded-xl w-32 h-40"
            preview-teleported
            :src="fileUrl + row.imagePath"
            :preview-src-list="[fileUrl + row.imagePath]"
            fit="fill"
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
              :title="`确定要删除《${row.title}?》`"
              @confirm="deletes(row.videoId)"
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
