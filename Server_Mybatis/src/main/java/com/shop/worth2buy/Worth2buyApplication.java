package com.shop.worth2buy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(basePackages = "com.shop.worth2buy.mapper")
@SpringBootApplication
@EnableCaching  //开启缓存功能
public class Worth2buyApplication {

    public static void main(String[] args) {
        SpringApplication.run(Worth2buyApplication.class, args);
    }

}
