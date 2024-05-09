<template>
  <div
    v-if="VModel"
    class="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50 z-[9998] duration-200"
    @click="close"
  >
    <div
      :style="{ width: width + 'px' }"
      class="bg-white border p-6 shadow-lg rounded-lg relative"
      @click.stop
    >
      <div class="absolute right-5 top-2">
        <Icon
          icon="material-symbols:close-rounded"
          class="text-xl cursor-pointer"
          @click="VModel = false"
        />
      </div>
      <div
        v-if="isHeader"
        class="dialog-header py-2 text-black whitespace-nowrap text-lg font-semibold leading-none tracking-tight"
      >
        <slot name="header">Confirm Action</slot>
      </div>
      <div class="dialog-content text-sm text-muted-foreground text-gray-500">
        <slot
          >Are you sure you want to proceed with this action? This cannot be
          undone.
        </slot>
      </div>
      <div class="dialog-footer py-2 text-right" v-if="isFooter">
        <slot name="footer"></slot>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue'

const VModel = defineModel<boolean>()
defineProps({
  isHeader: {
    type: Boolean,
    default: true,
  },
  isFooter: {
    type: Boolean,
    default: true,
  },
  width: {
    type: [String, Number],
    default: 500,
  },
})
const close = () => {
  VModel.value = false
}
</script>
