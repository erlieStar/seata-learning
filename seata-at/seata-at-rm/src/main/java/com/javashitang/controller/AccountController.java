package com.javashitang.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-12
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("transfer")
    public String transfer(@RequestParam("fromUserId") String fromUserId,
                           @RequestParam("toUserId") String toUserId,
                           @RequestParam("money") Integer money) {
        String sql = "update account_info set balance = balance + ? where user_id = ?";
        int result = jdbcTemplate.update(sql, new Object[]{money, toUserId});
        if (result == 0) {
            return "fail";
        }
        return "success";
    }
}
