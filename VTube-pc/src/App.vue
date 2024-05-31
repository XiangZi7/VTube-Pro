<script setup lang="ts">
import DefaultLayout from '@/layouts/index.vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'

const language = ref('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))
useTheme()
</script>

<template>
  <el-config-provider :locale="locale">
    <default-layout />
  </el-config-provider>
</template>
