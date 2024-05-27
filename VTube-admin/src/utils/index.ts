import { MenuItem, RouterMenu } from "@/interface/store/menuStore";

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
