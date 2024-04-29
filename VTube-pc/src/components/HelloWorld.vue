<script setup lang="ts">
import { Icon } from '@iconify/vue'

defineProps<{ msg: string }>()

const tests = ref('testss')
</script>

<template>
  <h1 class="text-2xl text-red-500">{{ msg }}</h1>
  <h1 class="text-2xl text-red-500">{{ tests }}</h1>
  <Icon icon="streamline:music-note-2" />
</template>
