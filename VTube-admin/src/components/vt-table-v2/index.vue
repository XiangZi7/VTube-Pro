<script setup lang="ts">
import { VxeColumnProps } from 'vxe-table'

defineProps({
  tableColumn: {
    type: Array as () => (VxeColumnProps & { key?: number; slot?: string })[],
    required: true,
  },
  tableData: {
    type: Array,
    default: () => [],
  },
  currentPage: {
    type: Number,
    default: 1,
  },
  pageSize: {
    type: Number,
    default: 10,
  },
  total: {
    type: Number,
    default: 0,
  },
})

const Emits = defineEmits(['pageChange'])
</script>
<template>
  <el-table :data="tableData" class="!flex-1">
    <el-table-column
      v-for="item in tableColumn"
      :key="item.field"
      :prop="item.field"
      :label="item.title"
    />
  </el-table>
</template>
