package com.vtube.mapper;

import com.vtube.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.vo.dictVO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vt_category(视频分类表)】的数据库操作Mapper
* @createDate 2024-05-16 11:33:06
* @Entity com.vtube.domain.Category
*/
public interface CategoryMapper extends BaseMapper<Category> {

    // 分类字典
    List<dictVO> categoryDict();

}




