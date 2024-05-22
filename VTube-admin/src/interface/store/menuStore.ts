export interface MenuState {
  isAside: boolean;
  breadcrumbList: bread[]
}

export interface bread {
  icon: string;
  title: string;
  router: string;
}
