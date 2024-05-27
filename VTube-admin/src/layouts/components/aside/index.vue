<template>
  <aside
    class="flex-shrink-0 rounded-tr-2xl box-border text-sm dark:text-neutral-200 px-4 py-8 hidden text-neutral-800 w-0 md:w-64 transition-width duration-300 ease-in-out"
    :class="isAside ? 'md:block' : ''"
  >
    <div class="flex items-center w-auto justify-center">
      <span class="text-lg font-semibold">
        <!-- <img src="@/assets/logo.png" class="object-cover w-24" /> -->
      </span>
    </div>
    <ul class="mt-2">
      <template
        v-for="item in menuStore.showMenuListGet"
        :key="item.meta.menuId"
      >
        <MenuItem
          :item-data="item"
          v-if="item.meta.visible"
          :menuIndex="item.path"
        />
      </template>
    </ul>
  </aside>
</template>
<script setup lang="ts">
import MenuItem from './menu-item.vue'
const menuStore = useMenuStore()
const { isAside } = storeToRefs(menuStore)
</script>
