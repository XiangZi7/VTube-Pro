export const data = [
    {
        title: "个人信息",
        icon: "material-symbols:account-circle-full",
        router: "/user",
        children: []
    },
    {
        title: "内容管理",
        icon: "material-symbols:content-paste",
        router: "/user/upload-manager",
        children: [
            {
                title: "稿件管理",
                icon: "mdi-light:content-paste",
                router: "/user/upload-manager/article",
            },
        ]
    },
    {
        title: "投稿",
        icon: "tdesign:undertake-delivery",
        router: "/user/upload/video",
        children: [
            {
                title: "视频投稿",
                icon: "ph:film-strip",
                router: "/user/upload/video",
            },
            {
                title: "论坛发表",
                icon: "material-symbols:post-add",
                router: "/user/upload/video",
            },
        ]
    },
    {
        title: "设置",
        icon: "ci:settings-future",
        router: "/user/setting",
        children: []
    },
]