<template>
  <div
    v-if="VModel"
    class="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50 z-[9998] duration-200"
    @click="close"
  >
    <div
      class="bg-white max-w-lg gap-4 border p-6 shadow-lg rounded-lg sm:max-w-[420px]"
      @click.stop
    >
      <div
        v-if="isHeader"
        class="dialog-header py-2 text-black whitespace-nowrap text-lg font-semibold leading-none tracking-tight"
      >
        <slot name="header">Confirm Action</slot>
      </div>
      <div class="dialog-content text-sm text-muted-foreground text-gray-500">
        <slot name="content"
          >Are you sure you want to proceed with this action? This cannot be
          undone.</slot
        >
      </div>
      <div class="dialog-footer py-2 text-right" v-if="isFooter">
        <slot name="footer"></slot>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
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
})
const close = () => {
  VModel.value = false
}
</script>
