package com.zmkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.zmkj.mapper.mysql", "com.zmkj.mapper.clickhouse"})
public class KsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KsServerApplication.class, args);
    }
}
