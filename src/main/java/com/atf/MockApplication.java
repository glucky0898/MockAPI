package com.atf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages="com.atf.mappers")
@SpringBootApplication
public class MockApplication {
    public static void main(String[] args) {
        SpringApplication.run(MockApplication.class, args);
    }
}
