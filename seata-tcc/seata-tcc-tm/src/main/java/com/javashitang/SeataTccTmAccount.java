package com.javashitang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * tcc模式源码分析比较好的文章
 * https://blog.csdn.net/hosaos/article/details/89847554
 */
@EnableFeignClients
@MapperScan("com.javashitang.dao")
@SpringBootApplication
public class SeataTccTmAccount {

    public static void main(String[] args) {
        SpringApplication.run(SeataTccTmAccount.class, args);
    }
}
