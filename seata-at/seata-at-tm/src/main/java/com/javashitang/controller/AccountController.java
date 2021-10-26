package com.javashitang.controller;

import com.javashitang.client.RmAccountClient;
import io.seata.spring.annotation.GlobalTransactional;
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
    @Resource
    private RmAccountClient rmAccountClient;

    @GlobalTransactional
    @RequestMapping("transfer")
    public String transfer(@RequestParam("fromUserId") String fromUserId,
                           @RequestParam("toUserId") String toUserId,
                           @RequestParam("money") Integer money,
                           @RequestParam(value = "flag", required = false) Boolean flag) {
        String sql = "update account_info set balance = balance + ? where user_id = ?";
        jdbcTemplate.update(sql, new Object[]{-money, fromUserId});
        String result = rmAccountClient.transfer(fromUserId, toUserId, money);
        if ("fail".equals(result)) {
            throw new RuntimeException("转账失败");
        }
        if (flag != null && flag) {
            throw new RuntimeException("测试同时回滚");
        }
        return "success";
    }
}
