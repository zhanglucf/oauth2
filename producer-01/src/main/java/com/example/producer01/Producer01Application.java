package com.example.producer01;

import org.mvnsearch.spring.boot.dubbo.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.example.producer01.mapper")
@EnableDubboConfiguration("com.example.producer01.service")
@SpringBootApplication
public class Producer01Application {

    public static void main(String[] args) {
        SpringApplication.run(Producer01Application.class, args);
    }

}
