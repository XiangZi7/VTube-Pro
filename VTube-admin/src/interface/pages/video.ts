export interface VideoVO {
  id: number
  title: string
  description: string
  views: number
  likes: number
  userId: number
  userName: string
  createTime: string
  videoPath: string
  imagePath: string
  categoryId: string;
}

export interface queryParamVO {
  title?: string
  userId?: number
}

export interface WeekVO {
  videoId: number;
  title: string;
  imagePath: string;
  weekDay: number;
  updateId: number;
}
