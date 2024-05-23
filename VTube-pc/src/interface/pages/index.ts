export interface weeklyAmime {
    imagePath: string;
    title: string;
    videoId: number
}

export interface indexState {
    weeklyAnime: weeklyAmime[][];
    weekTabsIndex: number
}