package com.javashitang;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoDataSourceProxy
public class SeataAtRm {

    public static void main(String[] args) {
        SpringApplication.run(SeataAtRm.class, args);
    }
}
