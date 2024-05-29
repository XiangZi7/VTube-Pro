<script setup lang="ts">
import { TableColumn, TableRef } from '@/interface/components/vt-table-v2.ts'
import { VideoVO } from '@/interface/pages/video'
const fileUrl = import.meta.env.VITE_API_MINIO

const state = reactive({
  dialogVisible: false,
})
const { dialogVisible } = toRefs(state)
const Emits = defineEmits(['callback'])
const vtTable = ref<TableRef>()

const tableColumn = ref<TableColumn[]>([
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

function select(row: VideoVO) {
  Emits('callback', row)
  state.dialogVisible = false
}
</script>
<template>
  <div>
    <el-button type="primary" @click="dialogVisible = true">选择番剧</el-button>
    <el-dialog
      class="!rounded-lg"
      v-model="dialogVisible"
      title="选择番剧"
      width="90%"
    >
      <vt-table-v2
        ref="vtTable"
        api="/video/weekIsNullList"
        :columns="tableColumn"
        class="!flex-1"
        row-key="videoId"
      >
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
          <el-button type="primary" @click="select(row)"> 选择 </el-button>
        </template>
      </vt-table-v2>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="dialogVisible = false">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>
