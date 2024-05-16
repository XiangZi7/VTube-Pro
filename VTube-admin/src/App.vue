<script setup lang="ts">
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'

const language = ref('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))
</script>

<template>
  <el-config-provider :locale="locale">
    <router-view />
  </el-config-provider>
</template>
