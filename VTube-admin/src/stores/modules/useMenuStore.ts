import { defineStore } from 'pinia'
import { MenuState } from '@/interface/store/menuStore.ts'
import piniaPersistConfig from '@/comfig/piniaPersist.ts'
import { bread } from "@/interface/store/menuStore";
/**
 * 全局设置
 */
export const useMenuStore = defineStore({
  id: 'useMenuStore',
  state: (): MenuState => ({
    isAside: false,
    breadcrumbList: [
      { icon: 'ant-design:dashboard-outlined', title: "仪表盘", router: "/dashboard" }
    ]
  }),
  actions: {
    setIsAside(isAside: boolean) {
      this.isAside = isAside
    },
    setBreadcrumbList(bread: bread) {
      // 如果 breadcrumbList 中已经包含传入的 bread 对象的 router 属性值，则不做任何操作
      if (this.breadcrumbList.some(item => item.router === bread.router)) return

      // 如果 breadcrumbList 中不包含传入的 bread 对象的 router 属性值，则将该对象添加到数组中
      this.breadcrumbList.push(bread);
    },
    deleteBread(bread: bread) {
      if (this.breadcrumbList.length === 1) {
        messagePro(300, '我是最后一个，不能在删除啦')
        return;
      }
      const index = this.breadcrumbList.findIndex(item => item.router === bread.router);
      if (index !== -1) {
        this.breadcrumbList.splice(index, 1);
      }
    }
  },
  persist: piniaPersistConfig('MenuStore'),
})
