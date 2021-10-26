package com.javashitang;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoDataSourceProxy(dataSourceProxyMode = "XA")
public class SeataXARm {

    public static void main(String[] args) {
        SpringApplication.run(SeataXARm.class, args);
    }
}
