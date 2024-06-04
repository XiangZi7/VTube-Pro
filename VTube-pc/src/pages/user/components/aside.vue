<script setup lang="ts">
import MenuItem from './menu-item.vue'
import { data } from '../data'
</script>
<template>
  <aside
    class="flex-shrink-0 md:block rounded-tr-2xl box-border text-sm dark:text-neutral-200 px-4 py-8 hidden text-neutral-800 w-0 md:w-64"
  >
    <ul class="mt-2">
      <template v-for="item in data" :key="item.router">
        <MenuItem :item-data="item" :menuIndex="item.router" />
      </template>
    </ul>
  </aside>
</template>
