<template>
  <div class="">
    <div
      v-for="(panel, index) in panels"
      :key="index"
      class="mb-2 p-2 border-b rounded-md"
    >
      <button
        class="w-full text-left focus:outline-none flex justify-between items-center"
        @click="togglePanel(index)"
      >
        <span>{{ panel.title }}</span>
        <Icon
          :class="{ 'rotate-90': panel.isRotated }"
          icon="material-symbols:arrow-back-ios-new-rounded"
        />
      </button>
      <div v-if="panel.open" class="mt-2">
        <slot :panel="panel"></slot>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue'

interface Panel {
  title: string
  open: boolean
}

const props = defineProps({
  title: String,
  panels: {
    type: Array as () => Panel[],
    required: true,
  },
})

const togglePanel = (index: number) => {
  props.panels[index].open = !props.panels[index].open
  props.panels[index].isRotated = !props.panels[index].isRotated
}
</script>
<style scoped lang="scss">
.rotate-90 {
  transform: rotate(-90deg);
  transition: transform 0.3s;
}
</style>
