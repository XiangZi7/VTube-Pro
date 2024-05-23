<script setup lang="ts">
import 'vue3-video-play/dist/style.css'
import { videoPlay } from 'vue3-video-play'

const fileUrl = import.meta.env.VITE_API_MINIO

const VideoUrl = defineModel()
defineProps({
  title: {
    type: String,
    default: '',
  },
  cover: {
    type: String,
    default: '',
  },
})
</script>
<template>
  <videoPlay
    width="100%"
    height="100%"
    :title="title"
    :src="fileUrl + VideoUrl"
    :poster="cover"
  />
</template>
<style lang="scss" scoped></style>
