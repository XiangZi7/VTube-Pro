<script setup lang="ts">
import 'vue3-video-play/dist/style.css'
import { videoPlay } from 'vue3-video-play'

const fileUrl = import.meta.env.VITE_API_MINIO

defineProps({
  title: {
    type: String,
    default: '',
  },
  cover: {
    type: String,
    default: '',
  },
  url: {
    type: String,
    default: '',
  },
})
const type = ref('video/mp4')

/**
 * 根据URL的文件后缀进行不同的返回处理
 * @param {string} link
 * @returns {string} 处理过的URL
 */
function processUrl(link: string): string {
  if (link.endsWith('.m3u8')) {
    type.value = 'm3u8'
    return link
  } else {
    type.value = 'video/mp4'
    return fileUrl + link
  }
}
</script>
<template>
  <videoPlay
    width="100%"
    height="100%"
    :title="title"
    :src="processUrl(url)"
    :poster="cover"
    :type="type"
  />
</template>
