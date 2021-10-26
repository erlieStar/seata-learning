package com.javashitang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.javashitang.dao")
@SpringBootApplication
public class TransactionMsgProducer {

    public static void main(String[] args) {
        SpringApplication.run(TransactionMsgProducer.class, args);
    }
}
