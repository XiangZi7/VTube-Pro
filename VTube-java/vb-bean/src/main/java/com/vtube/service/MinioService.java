package com.vtube.service;

import com.vtube.domain.Files;
import io.minio.*;
import io.minio.errors.MinioException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final String bucketName;

    //筛选不需要的文件格式
    @Value("${minio.not-support-file-type}")
    private String notSupportFileType;

    @Resource
    private FilesService filesService;

    public MinioService(MinioClient minioClient, @Value("${minio.bucket}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }

    public List<String> getAllBuckets() {
        List<String> bucketNames = new ArrayList<>();
        try {
            for (io.minio.messages.Bucket bucket : minioClient.listBuckets(ListBucketsArgs.builder().build())) {
                bucketNames.add(bucket.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bucketNames;
    }


    public void createBucket(String bucketName) throws Exception {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                throw new IllegalArgumentException("Bucket " + bucketName + " already exists");
            }
        } catch (Exception e) {
            throw new Exception("Failed to create bucket", e);
        }
    }

    public boolean checkBucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteBucket(String bucketName) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    public String uploadFile(MultipartFile file, Integer userId) throws Exception {
        try {
            String originalFilename = file.getOriginalFilename();
            String[] split = originalFilename.split("\\.");
            String fileType = split[split.length - 1].toLowerCase();
            String fileName = DigestUtils.md5DigestAsHex((split[0] + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)) + "." + fileType;

            if (isForbiddenFileType(fileType)) {
                throw new IllegalArgumentException("不允许上传 " + fileType + " 文件类型");
            }

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());

            Files files = new Files();
            files.setUserId(userId);
            files.setFileName(originalFilename);
            files.setFilePath(fileName);
            files.setFileSize((double) ((float) file.getSize() / 1024 / 1024));
            files.setFileType(fileType);
            files.setUploadDate(LocalDateTime.now());
            filesService.save(files);
            return fileName;
        } catch (MinioException | IOException e) {
            throw new Exception("Failed to upload file to MinIO", e);
        }
    }

    public void downloadFile(String bucketName, String objectName) throws Exception {
        try {
            InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
            // 下载文件操作
        } catch (MinioException | IOException e) {
            throw new Exception("Failed to download file from MinIO", e);
        }
    }

    private boolean isForbiddenFileType(String fileType) {
        List<String> forbiddenFileTypes = Arrays.asList(notSupportFileType);
        return forbiddenFileTypes.contains(fileType);
    }
}