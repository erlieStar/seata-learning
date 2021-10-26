package com.javashitang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 框架坑比较多，demo还没调通，暂时不管了
 */
@MapperScan("com.javashitang.dao")
@SpringBootApplication
public class HmilyTccRm {

    public static void main(String[] args) {
        SpringApplication.run(HmilyTccRm.class, args);
    }
}
