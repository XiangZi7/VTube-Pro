<script setup lang="ts">
import { TableColumn, TableRef } from '@/interface/components/vt-table-v2.ts'
import Dialog from './components/dialog.vue'
import { VideoVO } from '@/interface/pages/video'
import { messageProps } from 'element-plus'
const fileUrl = import.meta.env.VITE_API_MINIO

const DialogRef = ref<InstanceType<typeof Dialog> | null>(null)
const vtTable = ref<TableRef>()

const tableColumn = ref<TableColumn[]>([
  { type: 'selection', fixed: 'left', width: 70 },
  { prop: 'videoId', label: 'id' },
  { prop: 'imagePath', label: '封面', slotName: 'imagePath' },
  { prop: 'title', label: '标题', search: { el: 'input' } },
  {
    prop: 'categoryId',
    label: '分类',
    enum: 'category',
    search: { el: 'select', props: { filterable: true }, key: 'categoryId' },
  },
  {
    prop: 'description',
    label: '描述',
    width: 200,
  },
  { prop: 'views', label: '关注度' },
  {
    prop: 'type',
    label: '视频类型',
    enum: 'video_type',
    search: { el: 'select', props: { filterable: true } },
  },
  { prop: 'tags', label: '视频标签' },
  { prop: 'likes', label: '点赞' },
  {
    prop: 'userId',
    label: '上传者',
    enum: 'user',
    search: { el: 'select', props: { filterable: true }, key: 'userId' },
  },
  {
    prop: 'releaseTime',
    label: '上映时间',
    search: {
      el: 'date-picker',
      props: {
        valueFormat: 'YYYY-MM-DD',
        dateFormat: 'YYYY/MM/DD',
      },
    },
  },
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
  { prop: '', label: '操作', width: 200, fixed: 'right', slotName: 'action' },
])

// 打开对话框
const openDialog = (title: string, row: Partial<VideoVO> = {}) => {
  const props = {
    title,
    model: row,
    enumMap: vtTable.value?.enumMap,
    disabled: title === '查看',
    api: title === '新增' ? '/video/add' : '/video/edit',
    getTableList: vtTable.value?.getTableList,
  }
  DialogRef.value?.openDialog(props)
}
// 删除
const deletes = async (id: number | (number | string)[]) => {
  // 检查传入参数类型，统一处理为数组
  const ids = Array.isArray(id) ? id : [id]

  // 发出HTTP请求
  const { code, data, message } = await httpPost('/video/deletes', ids)

  // 弹出消息
  messagePro(code, data as string, message)

  // 检查响应状态码
  if (code !== 200) return

  // 更新表格
  if (vtTable.value?.getTableList) {
    vtTable.value.getTableList()
  }
}

function task(row: VideoVO, status: string) {
  httpPost('/auditing/task', {
    videoId: row.videoId,
    status,
    remark: row.remark,
  }).then(({ data, code }) => {
    messagePro(code, data)
    if (!data) return
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
        api="/auditing/list"
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
            :src="fileUrl + row.imagePath"
            :preview-src-list="[fileUrl + row.imagePath]"
            fit="fill"
          />
        </template>
        <template #action="{ row }">
          <div class="space-x-2 flex text-xl justify-around">
            <el-button type="primary" @click="openDialog('查看', row)" link>
              查看
            </el-button>
            <el-button type="primary" @click="openDialog('编辑', row)" link>
              编辑
            </el-button>
            <el-popconfirm
              :title="`确定要通过《${row.title}?》`"
              @confirm="task(row, '2')"
            >
              <template #reference>
                <el-button type="primary" link> 通过 </el-button>
              </template>
            </el-popconfirm>

            <el-popover trigger="click" width="220">
              <template #reference>
                <el-button type="primary" link> 驳回 </el-button>
              </template>
              <el-input
                type="textarea"
                v-model="row.remark"
                :rows="2"
                placeholder="请输入一个理由糊弄过去"
                clearable
              />
              <div class="flex justify-end mt-2 items-center">
                <el-button type="primary">取消</el-button>
                <el-button type="primary" @click="task(row, '3')"
                  >确定</el-button
                >
              </div>
            </el-popover>
          </div>
        </template>
      </vt-table-v2>
    </div>
  </div>
  <Dialog ref="DialogRef" />
</template>
