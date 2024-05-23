// 定义数据类型
export interface Episode {
    episodeNumber: number;
    title: string;
    videoPath: string
}

export interface VideoDetails {
    videoPath?: string;
    imagePath?: string;
    title?: string;
    likes?: number;
    views?: number;
    description?: string;
}

// 定义状态类型
export interface PlayState {
    activeNames: string[];
    details: VideoDetails;
    episodeList: Episode[];
    playIndex: number;
}