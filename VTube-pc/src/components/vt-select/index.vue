<script setup lang="ts">
import { onMounted, PropType } from 'vue'
import { DictVO } from '@/interface/dict'

const VModel = defineModel()
const dictData = ref<DictVO[]>([])
const props = defineProps({
  dict: {
    type: String,
    default: '',
  },
  optionMap: {
    type: Object as PropType<DictVO>,
    default: () => ({ label: 'label', value: 'value' }),
  },
})

onMounted(() => {
  httpGet<DictVO[]>(`/dict/${props.dict}`).then((res) => {
    dictData.value = res.data
  })
})
</script>
<template>
  <el-select-v2
    v-model="VModel"
    :options="dictData"
  />
</template>
