export const SideBarData = [
  {
    Title: '仪表盘',
    icon: 'ant-design:dashboard-outlined',
    router: '/dashboard',
    count: 0,
    children: [
      {
        Title: 'Video',
        icon: 'akar-icons:music-album',
        router: '/video',
        count: 0,
      },
      {
        Title: '数据字典',
        icon: 'material-symbols:dictionary-outline-rounded',
        router: '/dict',
        count: 0,
      },
    ],
  },
  {
    Title: 'Settings',
    icon: 'ic:sharp-settings',
    router: '/theme',
    count: 0,
    children: [
      {
        Title: '设置',
        icon: 'ri:settings-line',
        router: '/setting',
      },
    ],
  },
]
