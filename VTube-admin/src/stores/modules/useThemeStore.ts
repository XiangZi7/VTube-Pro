import { defineStore } from 'pinia'
import piniaPersistConfig from '@/comfig/piniaPersist.ts'
import { themeState } from '@/interface/store/themeStore'

/**
 * 全局设置
 */
export const useThemeStore = defineStore({
  id: 'useThemeStore',
  state: (): themeState => ({
    isDark: false,
    primary: '#000000',
    language: 'Chinese'
  }),
  actions: {
    setDark(isDark: string | number | boolean) {
      this.isDark = isDark
    },
    setPrimary(primary: string) {
      this.primary = primary
    },
    setLanguage(language: string) {
      this.language = language
    },
  },
  persist: piniaPersistConfig('ThemeStore'),
})
