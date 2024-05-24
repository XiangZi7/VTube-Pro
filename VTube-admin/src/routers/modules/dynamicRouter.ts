import { RouteRecordRaw } from "vue-router";
// 引入 views 文件夹下所有 vue 文件
const modules = import.meta.glob("@/pages/**/*.vue");
import router from "@/routers";
/**
 * @description 初始化动态路由
 */
export const initDynamicRouter = async () => {
    const userStore = useUserStore();
    const menuStore = useMenuStore()
    try {
        // 1.获取菜单列表
        await menuStore.getAuthMenuList();

        // 2.判断当前用户有没有菜单权限
        if (!menuStore.authMenuListGet.length) {
            userStore.setUserInfo({});
            router.replace("/login");
            return Promise.reject("No permission");
        }

        buildTree(menuStore.authMenuListGet)
        // 3.添加动态路由
        menuStore.authMenuListGet.forEach(item => {
            item.children && delete item.children;
            if (item.component && typeof item.component == "string") {
                item.component = modules["/src/pages/" + item.component + ".vue"];
            }
            if (item.isFull) {
                router.addRoute(item as unknown as RouteRecordRaw);
            } else {
                router.addRoute("layout", item as unknown as RouteRecordRaw);
            }
        });
    } catch (error) {
        // 当按钮 || 菜单请求出错时，重定向到登陆页
        userStore.setUserInfo({});
        router.replace("/login");
        return Promise.reject(error);
    }
};