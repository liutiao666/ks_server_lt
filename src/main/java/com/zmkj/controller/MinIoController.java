package com.zmkj.controller;

import com.zmkj.utils.MinioUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/minio")
public class MinIoController {

    @Resource
    private MinioUtils minioUtils;

    @PostMapping("/upload")
    public String uplaodFile(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {

        try {
            minioUtils.upload(file, file.getOriginalFilename(), "zmkj", path);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

}
