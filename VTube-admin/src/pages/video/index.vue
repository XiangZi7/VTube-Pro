<script setup lang="ts">
import { VxeColumnProps } from 'vxe-table'
import { onMounted } from 'vue'
import { RowVO } from '@/interface/pages/video.ts'
import { Icon } from '@iconify/vue'
import Dialog from './dialog.vue'

const DialogRef = ref(null)

const tableColumn = ref<(VxeColumnProps & { slot?: string })[]>([
  { type: 'seq', width: 60 },
  { type: 'checkbox', width: 50 },
  { field: 'id', title: 'id' },
  { field: 'title', title: '标题' ,},
  { field: 'description', title: '描述', width: 200 },
  { field: 'views', title: '关注度' },
  { field: 'likes', title: '点赞' },
  { field: 'userId', title: '上传者' },
  { field: 'createTime', title: '创建时间', sortable: true },
  { field: '', title: '', width: 100, fixed: 'right', slot: 'action' },
])

const tableData = ref<RowVO[]>([])

const edit = (row: RowVO) => {
  DialogRef.value.dialogDisplay = true
}

onMounted(() => {
  httpGet('/video/list').then((res) => {
    tableData.value = res.data.records
  })
})
</script>
<template>
  <div class="h-full">
    <div class="flex gap-1 mb-3 mt-1">
      <button
        class="w-28 flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3"
      >
        <Icon icon="material-symbols:add-rounded" class="text-2xl" />
        新增
      </button>
      <button
        class="w-28 flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3"
      >
        <Icon icon="material-symbols:delete-outline" class="text-xl" />
        删除
      </button>
    </div>
    <vt-table :tableColumn="tableColumn" :tableData="tableData">
      <template #action="{ row }">
        <div class="flex gap-1">
          <button
            class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3 w-full"
            @click="edit(row)"
          >
            编辑
          </button>
        </div>
      </template>
    </vt-table>
    <Dialog ref="DialogRef" />
  </div>
</template>
