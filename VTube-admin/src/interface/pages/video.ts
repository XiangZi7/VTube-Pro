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
}

export interface queryParamVO {
  title?: string
  userId?: number
}
