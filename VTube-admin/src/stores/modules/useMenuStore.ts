import { defineStore } from 'pinia'
import { MenuState, MenuItem } from '@/interface/store/menuStore.ts'
import { bread } from "@/interface/store/menuStore";
/**
 * 全局设置
 */
export const useMenuStore = defineStore({
  id: 'useMenuStore',
  state: (): MenuState => ({
    isAside: true,
    breadcrumbList: [
      { icon: 'ant-design:dashboard-outlined', title: "仪表盘", router: "/dashboard" }
    ],
    authMenuList: []
  }),
  getters: {
    // // 按钮权限列表
    // authButtonListGet: state => state.authButtonList,
    // // 菜单权限列表 ==> 这里的菜单没有经过任何处理
    authMenuListGet: state => state.authMenuList,
    // // 菜单权限列表 ==> 左侧菜单栏渲染，需要剔除 isHide == true
    showMenuListGet: state => buildTree(state.authMenuList),
    // // 菜单权限列表 ==> 扁平化之后的一维数组菜单，主要用来添加动态路由
    flatMenuListGet: state => buildTree(state.authMenuList),
    // // 递归处理后的所有面包屑导航列表
    // breadcrumbListGet: state => getAllBreadcrumbList(state.authMenuList)
  },
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
    },
    async getAuthMenuList() {
      const { data } = await httpPost<MenuItem[]>("/permissions")
      this.authMenuList = data
    }
  },
  // persist: piniaPersistConfig('MenuStore'),
})
