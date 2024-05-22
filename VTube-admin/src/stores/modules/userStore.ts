import { defineStore } from 'pinia'
import piniaPersistConfig from '@/comfig/piniaPersist.ts'
import { userState, user } from '@/interface/store/userStore'

/**
 * 全局设置
 */
export const useUserStore = defineStore({
    id: 'useUserStore',
    state: (): userState => ({
        userInfo: {},
    }),
    actions: {
        setUserInfo(userInfo: user) {
            this.userInfo = userInfo
        },
    },
    persist: piniaPersistConfig('UserStore'),
})
