export interface StateProps {
  tableData: any[]
  pageable: {
    pageNum: number
    pageSize: number
    total: number
  }
  searchParam: Record<string, any>
  searchInitParam: Record<string, any>
  totalParam: Record<string, any>
}
