export const SideBarData = [
  {
    Title: 'Home',
    icon: 'bx:home',
    router: '/home',
    count: 0,
    children: [
      {
        Title: 'Music',
        icon: 'streamline:music-note-2',
        router: '/music',
        count: 0,
      },
      {
        Title: 'Video',
        icon: 'akar-icons:music-album',
        router: '/video',
        count: 0,
      },
      {
        Title: 'MiniPlayer',
        icon: 'bi:music-player',
        router: '/mini',
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
        Title: 'Theme',
        icon: 'twemoji:paintbrush',
        router: '/theme',
        count: 0,
      },
      {
        Title: 'DownLoad',
        icon: 'tabler:arrow-bar-to-down',
        router: '/download',
        count: 1,
      },
    ],
  },
]
