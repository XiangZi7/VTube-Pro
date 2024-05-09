<script setup lang="ts">
import { onMounted, PropType } from 'vue'
import { DictVO } from '@/interface/dict'

const VModel = defineModel()
const dictData = ref<DictVO[]>([])
defineProps({
  dict: {
    type: String,
    default: '',
  },
  placeholder: {
    type: String,
    default: '',
  },
  optionMap: {
    type: Object as PropType<DictVO>,
    default: () => ({ label: 'label', value: 'value' }),
  },
})

onMounted(() => {
  httpGet<DictVO[]>(`/dict/user`).then((res) => {
    dictData.value = res.data
  })
})
</script>
<template>
  <el-select-v2
    v-model="VModel"
    :options="dictData"
    :placeholder="placeholder"
    style="width: 240px"
  />
</template>
