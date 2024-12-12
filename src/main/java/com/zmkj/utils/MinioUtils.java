package com.zmkj.utils;

import com.zmkj.config.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class MinioUtils {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioConfig configuration;

    /**
     * @param name 名字
     * @Description 判断bucket是否存在，不存在则创建
     */
    public boolean existBucket(String name) {
        boolean exists;
        try {
            exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exists = false;
        }
        return exists;
    }

    /**
     * @param bucketName 存储bucket名称
     * @Description 创建存储bucket
     */
    public Boolean makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param bucketName 存储bucket名称
     * @Description 删除存储bucket
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param fileName 文件名称
     * @param time     时间
     * @Description 获取上传临时签名
     */
    @SneakyThrows
    public Map getPolicy(String fileName, ZonedDateTime time, String bucketName) {
        PostPolicy postPolicy = new PostPolicy(bucketName, time);
        postPolicy.addEqualsCondition("key", fileName);
        try {
            Map<String, String> map = minioClient.getPresignedPostFormData(postPolicy);
            HashMap<String, String> map1 = new HashMap<>();
            map.forEach((k, v) -> {
                map1.put(k.replaceAll("-", ""), v);
            });
            map1.put("host", configuration.getUrl() + "/" + bucketName);
            return map1;
        } catch (Exception e) {
            throw new RuntimeException("has error");
        }
    }

    /**
     * @param objectName 对象名称
     * @param method     方法
     * @param time       时间
     * @param timeUnit   时间单位
     * @Description 获取上传文件的url
     */
    public String getPolicyUrl(String objectName, Method method, int time, TimeUnit timeUnit, String bucketName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(method)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(time, timeUnit).build());
        } catch (Exception e) {
            throw new RuntimeException("has error");
        }
    }


    /**
     * @param file     文件
     * @param fileName 文件名称
     * @Description 上传文件
     */
    public void upload(MultipartFile file, String fileName, String bucketName, String path) throws Exception {
        // 使用putObject上传一个文件到存储桶中。
        InputStream inputStream = file.getInputStream();
        if (StringUtils.isEmpty(path)) {
            fileName = path + "/" + fileName;
        }
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(inputStream, file.getSize(), -1)
                .contentType(file.getContentType())
                .build());
    }

    /**
     * @param objectName 对象名称
     * @param time       时间
     * @param timeUnit   时间单位
     * @Description 根据filename获取文件访问地址
     */
    public String getUrl(String objectName, int time, TimeUnit timeUnit, String bucketName) {
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(time, timeUnit).build());
        } catch (Exception e) {
            throw new RuntimeException("has error");
        }
        return url;
    }

    /**
     * @Description description: 下载文件
     */
    public void download(String bucket, String fileName, HttpServletResponse response) throws Exception {
        InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(fileName)
                .build());
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.close();
    }


    /**
     * @param objectFile 对象文件
     */
    public String getFileUrl(String objectFile, String bucketName) {
        try {

            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectFile)
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 创建文件夹
     *
     * @param bucketName
     * @param path       记得路径最后加一个/ 例如 bucket/folder/
     */
    public void createFolder(String bucketName, String path) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                        .build());
    }


    /**
     * 删除文件
     *
     * @param bucketName 库名称
     * @param fileName   文件名称
     * @return
     */
    public void removeObject(String bucketName, String fileName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build());
    }

    /**
     * 文件夹删除
     *
     * @param bucketName
     * @param fileName
     * @throws Exception
     */
    public void removeDirectory(String bucketName, String fileName) throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(fileName)
                .recursive(false)
                .build());
        for (Result<Item> item : results) {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName)
                    .object(item.get().objectName()).build());
        }
    }

}

