package com.javashitang;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableAutoDataSourceProxy
public class SeataAtTm {

    public static void main(String[] args) {
        SpringApplication.run(SeataAtTm.class, args);
    }
}
