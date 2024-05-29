import { Pagination } from "@/interface/utils/http"

export const useProjector = <T>(
    api?: string,
    initParam: object = {},
    isPageable: boolean = true,
    dataCallBack?: (data: any) => any,
    requestError?: (error: any) => void
) => {
    const state = reactive({
        tableData: [] as T[],
        pageable: {
            pageNum: 1,
            pageSize: 10,
            total: 0,
        },
        searchParam: {},
        searchInitParam: {},
        totalParam: {},
    })

    /**
   * 分页查询参数
   */
    const pageParam = computed({
        get: () => ({
            pageNum: state.pageable.pageNum,
            pageSize: state.pageable.pageSize,
        }),
        set: (newVal: any) => {
            console.log('Updated page params:', newVal)
        },
    })

    /**
     * 获取表格数据
     */
    const getTableList = async () => {
        if (!api) return
        try {
            Object.assign(
                state.totalParam,
                initParam,
                isPageable ? pageParam.value : {}
            )
            let { data } = await httpGet<Pagination>(api, {
                ...state.searchInitParam,
                ...state.totalParam,
            })

            dataCallBack && (data = dataCallBack(data))

            state.tableData = isPageable ? data.records : data
            if (isPageable) {
                const { current: pageNum, size: pageSize, total } = data
                updatePageable({ pageNum, pageSize, total })
            }
        } catch (error) {
            if (requestError) {
                requestError(error)
            }
        }
    }

    /**
     * 更新查询总参数
     */
    const updatedTotalParam = () => {
        state.totalParam = {}
        const nowSearchParam: any = {}
        for (const key in state.searchParam) {
            if (
                state.searchParam[key] ||
                state.searchParam[key] === false ||
                state.searchParam[key] === 0
            ) {
                nowSearchParam[key] = state.searchParam[key]
            }
        }
        Object.assign(
            state.totalParam,
            nowSearchParam,
            isPageable ? pageParam.value : {}
        )
    }

    /**
     * 更新分页信息
     * @param {Object} pageable 后台返回的分页数据
     */
    const updatePageable = (pageable: any) => {
        Object.assign(state.pageable, pageable)
    }

    /**
     * 表格数据查询
     */
    const search = () => {
        state.pageable.pageNum = 1
        updatedTotalParam()
        getTableList()
    }

    /**
     * 表格数据重置
     */
    const reset = () => {
        state.pageable.pageNum = 1
        state.searchParam = { ...state.searchInitParam }
        updatedTotalParam()
        getTableList()
    }

    /**
     * 每页条数改变
     * @param {Number} val 当前条数
     */
    const handleSizeChange = (val: number) => {
        state.pageable.pageNum = 1
        state.pageable.pageSize = val
        getTableList()
    }

    /**
     * 当前页改变
     * @param {Number} val 当前页
     */
    const handleCurrentChange = (val: number) => {
        state.pageable.pageNum = val
        getTableList()
    }

    return {
        ...toRefs(state),
        getTableList,
        search,
        reset,
        handleSizeChange,
        handleCurrentChange,
        updatedTotalParam,
    }
}