export interface DialogState<T = any> {
  enumMap?: Map<string, { [key: string]: any }[]>;
  title: string
  model: T
  disabled: boolean;
  api: string;
  getTableList?: () => void;
}

// 定义接口类型
export interface Video {
  title: string;
  videoPath: string;
  episodeNumber: number;
  episodeId?: number;
  videoId?: number;
}