package com.vtube.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Dictionary;
import com.vtube.mapper.CategoryMapper;
import com.vtube.mapper.DictionaryMapper;
import com.vtube.model.ApiResult;
import com.vtube.vo.Param.DictParam;
import com.vtube.vo.dictVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "字典")
@RequestMapping("/dict")
public class dictController {


    @Resource
    private DictionaryMapper dictionaryMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @GetMapping("/list")
    @Operation(summary = "字典列表", description = "字典列表")
    public ApiResult<?> list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                             @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, DictParam dictParam) {
        Page<Dictionary> page = new Page<>(pageNum, pageSize);

        IPage<Dictionary> list = dictionaryMapper.DictList(page,dictParam);

        return ApiResult.ok(list);
    }

    @GetMapping("/{dict}")
    public ApiResult<?> dict(@PathVariable("dict") String dict) {
        List<dictVO> dictionaries = dictionaryMapper.selectDictData(dict);
        return ApiResult.ok(dictionaries);
    }
    @GetMapping("/user")
    public ApiResult<?> user() {
        List<dictVO> dictionaries = dictionaryMapper.selectDictUserData();
        return ApiResult.ok(dictionaries);
    }
    @GetMapping("/category")
    public ApiResult<?> category() {
        List<dictVO> dictVOS = categoryMapper.categoryDict();
        return ApiResult.ok(dictVOS);
    }
}