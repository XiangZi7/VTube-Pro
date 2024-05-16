import { defineStore } from 'pinia'
import { MenuState } from '@/interface/store/menuStore.ts'
import piniaPersistConfig from '@/comfig/piniaPersist.ts'

/**
 * 全局设置
 */
export const useMenuStore = defineStore({
  id: 'useMenuStore',
  state: (): MenuState => {
    return {
      isAside: false,
    }
  },
  actions: {
    setIsAside(isAside: boolean) {
      this.isAside = isAside
    },
  },
  persist: piniaPersistConfig('useMenuStore'),
})
