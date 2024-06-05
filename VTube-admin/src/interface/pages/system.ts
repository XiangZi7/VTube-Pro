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
    type: string;   // 类型   0：目录   1：菜单   2：按钮
}

// -------------角色管理----------------
export interface Role {
    roleId: number;
    name: string
}
// -------------用户管理----------------
export interface User {
    userId: number;          // 用户ID
    userName: string;        // 用户名
    realName?: string;       // 用户真实姓名
    nickName?: string;       // 用户昵称
    phone?: string;          // 手机号
    email?: string;          // 邮箱
    avatarPath?: string;     // 用户头像
    createTime?: string;     // 创建时间
    sex?: string;            // M(男) or F(女)
    status?: string;         // 状态 1 正常 0 无效
}