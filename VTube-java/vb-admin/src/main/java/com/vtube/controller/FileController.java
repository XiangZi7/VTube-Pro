package com.vtube.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.vtube.domain.SysUser;
import com.vtube.model.ApiResult;
import com.vtube.service.MinioService;
import com.vtube.vo.AdminLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController

public class FileController {

    @Autowired
    private MinioService minioService;

    @GetMapping("/buckets")
    public List<String> getAllBuckets() {
        return minioService.getAllBuckets();
    }

    @PostMapping("/createBucket")
    public void createBucket(@RequestParam String bucketName) throws Exception {
        minioService.createBucket(bucketName);
    }

    @DeleteMapping("/deleteBucket/{bucketName}")
    public void deleteBucket(@PathVariable("bucketName") String bucketName) throws Exception {
        minioService.deleteBucket(bucketName);
    }

    @PostMapping("/uploadFile")
    public ApiResult<?> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        AdminLoginVO admin = (AdminLoginVO) StpUtil.getSession().get("admin");
        String file1 = minioService.uploadFile(file, admin.getUserId());

        return ApiResult.ok(file1);
    }

    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam String bucketName, @RequestParam String objectName) throws Exception {
        minioService.downloadFile(bucketName, objectName);
    }
}
