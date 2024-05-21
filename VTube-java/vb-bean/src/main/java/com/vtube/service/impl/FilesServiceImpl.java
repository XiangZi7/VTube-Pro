package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Files;
import com.vtube.service.FilesService;
import com.vtube.mapper.FilesMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_files(上传文件信息表，存储每个用户上传的文件信息)】的数据库操作Service实现
* @createDate 2024-05-20 13:16:07
*/
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files>
    implements FilesService{

}




