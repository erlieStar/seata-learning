package com.javashitang;


import com.javashitang.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AtomikosAtApplicationTests {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Resource
    private AccountService accountService;

    @Test
    public void test0() {
        String sql = "select count(*) from account_info";
        Integer result = primaryJdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(result);
    }

    @Test
    public void test1() {
        accountService.tx1();
    }

    @Test
    public void test2() {
        accountService.tx2();
    }
}
