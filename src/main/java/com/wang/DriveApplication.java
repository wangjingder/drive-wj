package com.wang;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wang.dao")
public class DriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveApplication.class, args);
    }
}
