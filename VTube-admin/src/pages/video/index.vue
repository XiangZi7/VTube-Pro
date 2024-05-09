<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { VxeColumnProps } from 'vxe-table'

import { queryParamVO, VideoVO } from '@/interface/pages/video.ts'
import Dialog from './dialog.vue'

import { Pagination } from '@/interface/http'

const DialogRef = ref<InstanceType<typeof Dialog> | null>(null)

const tableColumn = ref<(VxeColumnProps & { slot?: string })[]>([
  { field: 'videoId', title: 'id' },
  { field: 'title', title: '标题' },
  { field: 'description', title: '描述', width: 200 },
  { field: 'views', title: '关注度' },
  { field: 'likes', title: '点赞' },
  { field: 'userName', title: '上传者' },
  { field: 'createTime', title: '创建时间', sortable: true },
  { field: '', title: '', width: 200, fixed: 'right', slot: 'action' },
])

const tableData = ref<VideoVO[]>([])
const total = ref<number>(0)
const current = ref<number>(1)
const currentSize = ref<number>(20)
const queryParam = ref<queryParamVO>({})
// 打开对话框
const openDialog = (title: string, row: Partial<VideoVO> = {}) => {
  const props = {
    title,
    model: row,
    disabled: title === '查看',
  }
  DialogRef.value?.openDialog(props)
}
// 获取数据
const fetchData = async (pageNum: number = 1, pageSize: number = 20) => {
  // params
  const queryParams: any = queryParam.value
  const params: any = {
    pageNum,
    pageSize,
  }

  Object.keys(queryParams).forEach((key: string) => {
    if (queryParams[key]) {
      params[key] = queryParams[key]
    }
  })

  const res = await httpGet<Pagination>('/video/list', params)
  console.log(res)
  tableData.value = res.data.records
  total.value = res.data.total
}
// 分页
const pageChange = (currentPage: number, Size: number) => {
  currentSize.value = Size
  current.value = currentPage
  fetchData(currentPage, Size)
}

onMounted(() => {
  fetchData()
})

// 搜索
const search = () => {
  fetchData()
}

// 重置
const reset = () => {
  const queryParams: any = queryParam.value

  // 将所有 queryParam 的值重置为空字符串或 undefined
  Object.keys(queryParams).forEach((key: string) => {
    queryParams[key] = '' // 或者将值重置为 undefined
  })

  // 更新 queryParam
  queryParam.value = { ...queryParams }
  fetchData()
}
// 删除
const deletes = async (id: number) => {
  const res = await httpPost('/video/deletes', [id])
  console.log(res)
}
</script>
<template>
  <div class="flex flex-col h-full">
    <div class="h-full w-full p-2 box-border flex flex-1 flex-col shadow-app rounded-2xl bg-white/90 gap-2 overflow-x-hidden overflow-auto">
      <div class="p-2">
        <button
            class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3"
        >
          Add user
        </button>
      </div>
      <el-table :data="tableData" class="!flex-1">
        <el-table-column
            v-for="item in tableColumn"
            :key="item.field"
            :prop="item.field"
            :label="item.title"
        />
      </el-table>
    </div>
  </div>
  <Dialog ref="DialogRef" />
</template>
