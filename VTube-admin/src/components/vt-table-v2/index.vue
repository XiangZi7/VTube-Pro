<!-- eslint-disable consistent-return -->
<script setup lang="ts">
import { TableProps, TableColumn } from '@/interface/components/vt-table-v2.ts'
import { useTable } from '@/hooks/useTable.ts'
import SearchForm from './components/SearchForm.vue'
// 接受父组件参数，配置默认值
const props = withDefaults(defineProps<TableProps>(), {
  columns: () => [],
  requestAuto: true,
  pagination: true,
  initParam: {},
  border: true,
  toolButton: true,
  rowKey: 'id',
  searchCol: () => ({ xs: 1, sm: 2, md: 2, lg: 3, xl: 4 }),
})

// table Hooks
const {
  tableData,
  handleSizeChange,
  handleCurrentChange,
  getTableList,
  pageable,
  searchParam,
  reset,
  search,
} = useTable(
  props.api,
  props.initParam,
  props.pagination,
  props.dataCallBack,
  props.requestError
)

// 定义 emit 事件
const emit = defineEmits<{
  search: []
  reset: []
  dargSort: [{ newIndex?: number; oldIndex?: number }]
}>()
const enumMap = ref(new Map<string, { [key: string]: any }[]>())
const setEnumMap = async ({ prop, enum: enumValue }: TableColumn) => {
  if (!enumMap.value.has(prop!)) {
    // 检查enumMap中是否已经存在item.prop
    let data
    if (enumValue && typeof enumValue === 'string') {
      const res = await httpGet(`/dict/${enumValue}`)
      data = res.data
    } else if (enumValue && typeof enumValue === 'function') {
      data = await enumValue()
    }

    if (data) {
      enumMap.value.set(prop!, data)
    }
  }
}
// 字典操作
const searchColumns = computed(() => props.columns)

searchColumns.value.forEach(async (item) => {
  // 设置 enumMap
  await setEnumMap(item)
})
// // 注入 enumMap
provide('enumMap', enumMap)

onMounted(() => {
  getTableList()
})

const hSearch = () => {
  search()
  emit('search')
}

const hreset = () => {
  reset()
  emit('reset')
}

defineExpose({
  enumMap,
})
</script>
<template>
  <div
    class="h-full w-full box-border flex flex-1 flex-col overflow-x-hidden overflow-auto"
  >
    <!-- Search Form -->
    <SearchForm
      :search="hSearch"
      :reset="hreset"
      :columns="searchColumns"
      :search-param="searchParam"
      :search-col="searchCol"
    />
    <!-- 按钮区 -->
    <slot name="btn" />
    <el-table :data="tableData" :row-key="rowKey" class="!flex-1">
      <slot />
      <template v-for="column in columns" :key="column.prop">
        <el-table-column
          v-bind="column"
          :align="column.align ?? 'center'"
          :reserve-selection="column.type == 'selection'"
        >
          <template v-if="column.slotName" #default="scope">
            <slot :name="column.slotName" v-bind="scope" />
          </template>
        </el-table-column>
      </template>
    </el-table>

    <el-pagination
      v-if="pagination"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      v-model:current-page="pageable.pageNum"
      v-model:page-size="pageable.pageSize"
      :page-sizes="[10, 30, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pageable.total"
      class="flex justify-end items-center mt-2"
    />
  </div>
</template>