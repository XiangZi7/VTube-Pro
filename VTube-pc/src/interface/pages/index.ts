export interface weeklyAmime {
    imagePath: string;
    title: string;
    videoId: number;
    views: number

}

export interface indexState {
    weeklyAnime: weeklyAmime[][];
    weekTabsIndex: number;
    top10: VideoDTO[];
    recommended: VideoDTO[]
}


export interface VideoDTO {
    videoId: number;
    title: string;
    createTime: Date;
    imagePath: string;
    nickName: string;
    avatarPath: string;
    views: number;
    likes: number;
}

// 定义状态类型
export interface HomeState {
    LatestUpdate: VideoDTO[];
    hot: VideoDTO[];
    page: number
}


