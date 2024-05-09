<script setup lang="ts">
import { VxeColumnProps } from 'vxe-table'

interface pageVo {
  currentPage: number
  pageSize: number
}

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

function pageChange({ currentPage, pageSize }: pageVo) {
  Emits('pageChange', currentPage, pageSize)
}
</script>
<template>
  <div class="h-full rounded-2xl overflow-auto">
    <vxe-table
      round
      :data="tableData"
      auto-resize
      :column-config="{ resizable: true }"
      :row-config="{ isHover: true }"
    >
      <vxe-column
        v-for="config in tableColumn"
        :key="config.key"
        :align="config.align ?? 'center'"
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
  </div>
  <vxe-pager
    class="mt-2 rounded-xl"
    :current-page="currentPage"
    :page-size="pageSize"
    :total="total"
    @page-change="pageChange"
  />
</template>
