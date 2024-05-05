<script setup lang="ts">
const dialogDisplay = ref(false)

defineExpose({ dialogDisplay })
</script>
<template>
  <vt-dialog v-model="dialogDisplay">
    <div>我是测试内容</div>
  </vt-dialog>
</template>
