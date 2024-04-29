<script setup lang="ts">
import { VxeColumnProps } from 'vxe-table'
import { onMounted } from 'vue'

const tableColumn = ref<(VxeColumnProps & { slot?: string })[]>([
  { type: 'seq', width: 60 },
  { type: 'checkbox', width: 50 },
  { field: 'id', title: 'id' },
  {
    title: '标题',
    field: 'title',
  },
  { field: 'description', title: '描述', width: 200 },
  { field: 'views', title: '关注度' },
  { field: 'likes', title: '点赞' },
  { field: 'userId', title: '上传者' },
  { field: 'createTime', title: '创建时间', sortable: true },
  { field: '', title: '', width: 200, fixed: 'right', slot: 'action' },
])

interface RowVO {
  id: number
  name: string
  role: string
  sex: string
  age: number
  address: string
}

const tableData = ref<RowVO[]>([])

const edit = (row: RowVO) => {
  console.log(row)
}

onMounted(() => {
  httpGet('/video/list').then((res) => {
    console.log(res)
    tableData.value = res.data.records
  })
})
</script>
<template>
  <vt-table :tableColumn="tableColumn" :tableData="tableData">
    <template #action="{ row }">
      <div class="flex gap-1">
        <button
          class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3 w-full"
          @click="edit(row)"
        >
          编辑
        </button>
        <button
          class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3 w-full"
        >
          推送
        </button>
      </div>
    </template>
  </vt-table>
</template>
