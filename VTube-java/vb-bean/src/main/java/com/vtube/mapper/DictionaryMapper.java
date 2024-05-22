package com.vtube.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.vo.Param.DictParam;
import com.vtube.vo.dictVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vt_dictionary(字典表)】的数据库操作Mapper
* @createDate 2024-05-22 10:24:24
* @Entity com.vtube.domain.Dictionary
*/
public interface DictionaryMapper extends BaseMapper<Dictionary> {
    List<dictVO> selectDictData(@Param("dict") String dict);

    List<dictVO> selectDictUserData();

    IPage<Dictionary> DictList(IPage<Dictionary> page, @Param("dict") DictParam dict);


}




