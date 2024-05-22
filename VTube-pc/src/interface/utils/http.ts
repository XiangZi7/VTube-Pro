export interface ApiResponse<T> {
    code: number
    data: T
    message: string
    time: string
  }
  
  // 定义分页信息类型
  export interface Pagination<T = any> {
    total: number
    size: number
    current: number
    pages: number
    records: T
  }
  