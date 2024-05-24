import { MenuItem, RouterMenu } from "@/interface/store/menuStore";
/**
 * @description 使用递归扁平化菜单，方便添加动态路由
 * @param {Array} menuList 菜单列表
 * @returns {Array}
 */
export function getFlatMenuList(menuList: MenuItem[]) {
    const newMenuList = JSON.parse(JSON.stringify(menuList));
    return newMenuList.flatMap(item => [item, ...(item.children ? getFlatMenuList(item.children) : [])]);
}

/**
 * @description 使用递归过滤出需要渲染在左侧菜单的列表 (需剔除 isHide == true 的菜单)
 * @param {Array} menuList 菜单列表
 * @returns {Array}
 * */
export function getShowMenuList(menuList: MenuItem[]) {
    // const newMenuList = JSON.parse(JSON.stringify(menuList));
    const newMenuList = getFlatMenuList(menuList);
    return newMenuList.filter(item => {
        item.children?.length && (item.children = getShowMenuList(item.children));
        return !item?.visible;
    });
}

// 整理成嵌套的树状结构
export function buildTree(arr: MenuItem[]): RouterMenu[] {
    const tree: RouterMenu[] = [];
    const map = {};

    arr.forEach(item => (map[item.menuId] = {
        path: item.path,
        component: item.component,
        name: item.name,
        redirect: item.redirect || undefined,
        meta: {
            icon: item.icon,
            title: item.title,
            keepAlive: item.isKeepAlive,
            isFull: item.isFull,
            menuId: item.menuId,
            orderNum: item.orderNum,
            parentId: item.parentId,
            permission: item.permission,
            visible: item.visible,
        },
        children: []
    }));

    arr.forEach(item => {
        if (item.parentId) {
            map[item.parentId].children.push(map[item.menuId]);
        } else {
            tree.push(map[item.menuId]);
        }
    });

    return tree as RouterMenu[];
}
