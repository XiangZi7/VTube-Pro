<script setup lang="ts">
const state = reactive({
  activeNames: '1',
})
const { activeNames } = toRefs(state)
interface searchProps {
  searchParam?: { [key: string]: any } // 搜索参数
  search: (params: any) => void // 搜索方法
  reset: (params: any) => void // 重置方法
}
// 默认值
withDefaults(defineProps<searchProps>(), {
  searchParam: () => ({}),
})
</script>
<template>
  <div class="w-full h-full overflow-hidden ">
    <el-collapse v-model="activeNames">
      <el-collapse-item title="标题" name="1">
        <div class="space-y-4">
          <el-input
            v-model="searchParam.title"
            placeholder="请输入标题"
            clearable
          />
        </div>
      </el-collapse-item>
      <el-collapse-item title="类型" name="2">
        <div class="space-y-4">
          <el-input
            v-model="searchParam.categoryId"
            placeholder="请选择分类"
            clearable
          />
          <el-input
            v-model="searchParam.tags"
            placeholder="请输入标签(逗号隔开如:动漫,唯美)"
            clearable
          />
        </div>
      </el-collapse-item>
      <el-collapse-item title="年份" name="3">
        <el-date-picker
          v-model="searchParam.releaseTime"
          type="year"
          placeholder="Pick a year"
          value-format="YYYY-MM-DD"
          date-format="YYYY/MM/DD"
        />
      </el-collapse-item>
    </el-collapse>
    <div class="space-x-2 my-5">
      <el-button type="primary" @click="search">
        <div class="flex gap-1 items-center">
          <icon-material-symbols:flight-takeoff />
          起飞
        </div>
      </el-button>
      <el-button type="primary" @click="reset">
        <div class="flex gap-1 items-center">
          <icon-system-uicons:reset />
          重置
        </div>
      </el-button>
    </div>
  </div>
</template>
