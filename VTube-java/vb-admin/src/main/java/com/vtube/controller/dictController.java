package com.vtube.controller;

import com.vtube.domain.Dictionary;
import com.vtube.mapper.DictionaryMapper;
import com.vtube.model.ApiResult;
import com.vtube.vo.dictVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor
@Tag(name = "字典")
@RequestMapping("/dict")
public class dictController {


    @Resource
    private DictionaryMapper dictionaryMapper;

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
}