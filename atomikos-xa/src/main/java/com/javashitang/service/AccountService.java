package com.javashitang.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-07
 */
@Service
public class AccountService {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Resource
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void tx1() {
        Integer money = 100;
        String sql = "update account_info set balance = balance + ? where user_id = ?";
        primaryJdbcTemplate.update(sql, new Object[]{-money, 1001});
        secondaryJdbcTemplate.update(sql, new Object[]{money, 1002});
    }

    @Transactional(rollbackFor = Exception.class)
    public void tx2() {
        Integer money = 100;
        String sql = "update account_info set balance = balance + ? where user_id = ?";
        primaryJdbcTemplate.update(sql, new Object[]{-money, 1001});
        secondaryJdbcTemplate.update(sql, new Object[]{money, 1002});
        throw new RuntimeException();
    }
}
