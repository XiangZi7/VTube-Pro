
export interface MenuState {
  isAside: boolean;
  breadcrumbList: bread[];
  authMenuList: MenuItem[];
}

export interface bread {
  icon: string;
  title: string;
  router: string;
}


export interface MenuItem {
  children?: MenuItem[] | null;
  component: string | null | (() => Promise<any>);
  icon: string | null;
  isFull: number;
  isKeepAlive: number | null;
  menuId: number;
  name: string;
  orderNum: number;
  parentId: number | null;
  path: string;
  permission: string | null;
  redirect: string | null;
  title: string;
  visible: number;
}


export interface RouterMenu {
  path: string;
  component: string | Promise<Component> | null;
  name: string;
  redirect: string | null;
  children?: RouterMenu[] | null;
  meta: {
    title: string;
    icon: string | null;
    orderNum: number;
    parentId: number | null;
    visible: number;
    isFull: number;
    menuId: number;
    permission: string | null;
    isKeepAlive: number | null;
  }
}