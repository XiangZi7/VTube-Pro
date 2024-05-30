// -------------菜单管理----------------
export interface Menu {
    menuId: number;   // 主键ID，自增
    parentId: number | null; // 父菜单ID，顶级菜单为NULL
    title: string;   // 菜单标题
    icon: string;   // 菜单图标
    path: string;   // 菜单路由路径
    name: string;   // 路由name
    component: string;   // 前端Vue组件路径
    orderNum: number;   // 菜单排序顺序
    visible: number;   // 菜单是否可见, 1为可见，0为不可见
    permission: string;   // 权限标识，用于权限控制
    isKeepAlive: number;   // 当前路由是否缓存 0：否 1：是
    redirect: string;   // 路由重定向地址
    isFull: number;   // 是否全屏
    type: number;   // 类型   0：目录   1：菜单   2：按钮
}

// -------------角色管理----------------
export interface Role {
    roleId: number;
    name: string
}