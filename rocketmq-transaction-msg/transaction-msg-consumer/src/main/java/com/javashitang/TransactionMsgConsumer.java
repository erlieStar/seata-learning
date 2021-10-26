package com.javashitang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.javashitang.dao")
@SpringBootApplication
public class TransactionMsgConsumer {

    public static void main(String[] args) {
        SpringApplication.run(TransactionMsgConsumer.class, args);
    }
}
