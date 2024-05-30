import { MenuItem, RouterMenu } from "@/interface/store/menuStore";

// 整理成嵌套的树状结构
export function buildTree(arr: MenuItem[]): RouterMenu[] {
    const tree: RouterMenu[] = [];
    const map: { [key: number]: RouterMenu } = {};

    arr.forEach(item => {
        map[item.menuId] = {
            path: item.path,
            component: item.component,
            name: item.name,
            redirect: item.redirect,
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
        };
    });

    arr.forEach(item => {
        if (item.parentId) {
            if (!map[item.parentId].children) {
                map[item.parentId].children = [];
            }
            map[item.parentId].children.push(map[item.menuId]);
            // 对子节点进行排序
            map[item.parentId].children.sort((a, b) => a.meta.orderNum - b.meta.orderNum);
        } else {
            tree.push(map[item.menuId]);
        }
    });

    // 对根节点进行排序
    tree.sort((a, b) => a.meta.orderNum - b.meta.orderNum);

    return tree;
}
/**
 * 
 * @param arr 
 */
export function buildMenu(arr: any) {
    const tree = [];
    const map = {};

    arr.forEach(item => (map[item.menuId] = {
        ...item,
        children: []
    }));

    arr.forEach(item => {
        if (item.parentId) {
            map[item.parentId].children.push(map[item.menuId]);
        } else {
            tree.push(map[item.menuId]);
        }
    });

    return tree;
}

/**
 * 
 * @param arr 
 * @description 生成数据下拉框
 */
export function buildSelectMenu(arr: any) {
    const tree = [];
    const map = {};

    arr.forEach(item => (map[item.menuId] = {
        value: item.menuId,
        label: item.title,
        children: []
    }));

    arr.forEach(item => {
        if (item.parentId) {
            map[item.parentId].children.push(map[item.menuId]);
        } else {
            tree.push(map[item.menuId]);
        }
    });

    return tree;
}
