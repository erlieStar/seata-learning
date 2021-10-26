package com.javashitang;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 参考博客
 * https://zhuanlan.51cto.com/art/202102/644394.htm
 */
@RestController
@SpringBootApplication
public class AtomikosAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomikosAtApplication.class, args);
    }

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Resource
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;


    @Transactional
    @RequestMapping("")
    public String change(@RequestParam("userId") String userId,
                         @RequestParam("money") Integer money) {
        String sql = "update account_info set money = money + ? where user_id = ?";
        primaryJdbcTemplate.update(sql, new Object[]{-money, userId});
        int result = secondaryJdbcTemplate.update(sql, new Object[]{money, userId});
        if (result == 0) {
            throw new RuntimeException("转账失败");
        }
        return "success";
    }
}
