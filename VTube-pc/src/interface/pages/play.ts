import { Pagination } from '@/interface/utils/http'

// 定义数据类型
export interface Episode {
    episodeNumber: number;
    title: string;
    videoPath: string
}

export interface VideoDetails {
    videoId: number;
    title: string;
    description: string;
    avatarPath: string;
    videoPath: string;
    nickName: string;
    signature: string;
    createTime: string;
    views: number;
    likes: number;
    userId: number;
}

// 定义状态类型
export interface PlayState {
    activeNames: string[];
    details: Partial<VideoDetails>;
    episodeList: Episode[];
    playIndex: number;
    comText: string;
    comText2: string;
    comments?: Partial<Pagination<Comment[]>>;
}

export interface Comment {
    commentId: number;
    userId: number;
    content: string;
    avatarPath: string;
    nickName: string;
    createTime: string;
    children: Comment[];
}