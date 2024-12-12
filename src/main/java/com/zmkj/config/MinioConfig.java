package com.zmkj.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MinioConfig {

    @Value("${spring.servlet.minio.access-key}")
    private String accesssKey;

    @Value("${spring.servlet.minio.secret-key}")
    private String secretKey;

    @Value("${spring.servlet.minio.url}")
    private String url;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accesssKey, secretKey)
                .build();
    }

}
