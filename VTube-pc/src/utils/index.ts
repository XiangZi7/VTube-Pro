export function timeAgo(dateStr: string): string {
    const now = new Date();
    const inputDate = new Date(dateStr.replace(/-/g, '/')); // IE 兼容性处理

    const diffInMs = now.getTime() - inputDate.getTime();
    const diffInSec = Math.floor(diffInMs / 1000);
    const diffInMin = Math.floor(diffInSec / 60);
    const diffInHours = Math.floor(diffInMin / 60);
    const diffInDays = Math.floor(diffInHours / 24);

    if (diffInSec < 60) {
        return '刚刚';
    } else if (diffInMin < 60) {
        return `${diffInMin}分钟前`;
    } else if (diffInHours < 24) {
        return `${diffInHours}小时前`;
    } else if (diffInDays < 7) {
        return `${diffInDays}天前`;
    } else {
        return inputDate.toLocaleDateString(); // 返回具体日期
    }
}


interface MenuItem {
    value: string;
    label: string;
    parentId?: string;  // parentId 是可选属性
    children?: MenuItem[];  // children 是可选属性
}

/**
 * @param arr - 输入的数组，数组中的对象应包含 `value`、`label` 和 `parentId` 属性
 * @description 生成层级结构的下拉菜单数据
 * @returns 返回由输入数组生成的树形结构
 */
export function buildSelectMenu(arr: MenuItem[]): MenuItem[] {
    const tree: MenuItem[] = [];
    const map: { [key: string]: MenuItem } = {};

    // 第一次遍历：构造一个哈希表，便于快速查找节点
    arr.forEach(item => {
        map[item.value] = {
            value: item.value,
            label: item.label,
            children: []  // 初始化 children 数组
        }
    });

    // 第二次遍历：构造树形结构
    arr.forEach(item => {
        // 检查父节点是否存在
        const parent = item.parentId ? map[item.parentId] : null;
        if (parent && parent.children) {
            // 如果有父节点，并且父节点存在于 map 中，添加到父节点的 children 中
            parent.children.push(map[item.value]);
        } else if (!item.parentId) {
            // 如果没有父节点，直接添加到树的顶层
            tree.push(map[item.value]);
        }
    });

    // 移除空的 `children` 属性
    const removeEmptyChildren = (nodes: MenuItem[]) => {
        nodes.forEach(node => {
            if (node.children && node.children.length === 0) {
                delete node.children;  // 删除空的 `children` 属性
            } else {
                if (!node.children) return
                removeEmptyChildren(node.children);  // 递归处理子节点
            }
        });
    }

    // 调用递归函数，移除空的 `children` 属性
    removeEmptyChildren(tree);

    return tree;
}
