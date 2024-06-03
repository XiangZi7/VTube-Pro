<script setup lang="ts">
import Video from './components/video.vue'
const state = reactive({
  list: [],
  activeName: 'first',
})
const { list, activeName } = toRefs(state)
</script>
<template>
  <div class="w-full px-5 py-3">
    <el-tabs v-model="activeName" class="demo-tabs">
      <el-tab-pane label="视频管理" name="first">
        <Video />
      </el-tab-pane>
      <el-tab-pane label="论坛帖子管理" name="second">论坛帖子管理</el-tab-pane>
    </el-tabs>
  </div>
</template>
