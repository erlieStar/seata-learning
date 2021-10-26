package com.javashitang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 参考博客
 * https://www.jianshu.com/p/3e952d7935a7
 * https://www.codenong.com/cs106852111/
 * 官方文档
 * https://dromara.org/zh/projects/hmily/quick-start-springcloud/
 * 配置详解
 * https://www.bookstack.cn/read/hmily-zh/1.md
 *
 * 框架坑比较多，demo还没调通，暂时不管了
 */
@MapperScan("com.javashitang.dao")
@EnableFeignClients
@SpringBootApplication
public class HmilyTccTm {

    public static void main(String[] args) {
        SpringApplication.run(HmilyTccTm.class, args);
    }
}
