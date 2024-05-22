<template>
  <el-form inline label-suffix=" :" class="p-2">
    <el-row :gutter="10">
      <template v-for="column in columns" :key="column.id">
        <el-col :xs="10" :sm="8" :md="4" :lg="4" :xl="4" v-if="column.search">
          <el-form-item :label="column.label" class="w-full">
            <component
              class="w-full"
              :is="`el-${column.search.el}`"
              v-model="searchParam[column.search.key ?? column.prop!]"
              v-bind="column.search.props"
            >
              <template v-if="column.search?.el === 'select'">
                <component
                  :is="`el-option`"
                  v-for="(col, index) in enumMap.get(column.prop)"
                  :key="index"
                  :label="col.label"
                  :value="col.value"
                />
              </template>
            </component>
          </el-form-item>
        </el-col>
      </template>
      <div class="flex gap-2 ml-auto">
        <el-button
          type="primary"
          class="w-20 rounded-md px-3"
          @click="search"
        >
          搜索
        </el-button>
        <el-button
          type="danger"
          @click="reset"
          class="w-20 rounded-md px-3"
        >
          重置
        </el-button>
      </div>
    </el-row>
    <div></div>
  </el-form>
</template>

<script setup lang="ts">
import { BreakPoint, TableColumn } from '@/interface/components/vt-table-v2.ts'

interface ProTableProps {
  columns?: TableColumn[] // 搜索配置列
  searchParam?: { [key: string]: any } // 搜索参数
  searchCol: number | Record<BreakPoint, number>
  search: (params: any) => void // 搜索方法
  reset: (params: any) => void // 重置方法
}
// 默认值
withDefaults(defineProps<ProTableProps>(), {
  columns: () => [],
  searchParam: () => ({}),
})

// 接收 enumMap (el 为 select-v2 需单独处理 enumData)
const enumMap = inject('enumMap', ref(new Map()))
</script>
