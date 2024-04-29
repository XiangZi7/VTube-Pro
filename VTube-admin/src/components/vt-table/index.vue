<script setup lang="ts">
import { VxeColumnProps } from 'vxe-table'

defineProps({
  tableColumn: {
    type: Array as () => (VxeColumnProps & { key: number; slot?: string })[],
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
    default: 30,
  },
  total: {
    type: Number,
    default: 0,
  },
})
</script>
<template>
  <vxe-table
    round
    :data="tableData"
    height="90%"
    auto-resize
    :column-config="{ resizable: true }"
    :row-config="{ isHover: true }"
  >
    <vxe-column
      v-for="config in tableColumn"
      :key="config.key"
      :type="config.type"
      :field="config.field"
      :title="config.title"
      :fixed="config.fixed"
      :sortable="config.sortable"
      :width="config.width"
      :filters="config.filters"
      show-overflow="ellipsis"
    >
      <template #default="{ row }" v-if="config.slot">
        <slot :name="config.slot" :row="row" />
      </template>
    </vxe-column>
  </vxe-table>
  <vxe-pager
    class="mt-2 rounded-xl"
    :current-page="currentPage"
    :page-size="pageSize"
    :total="total"
  />
</template>
