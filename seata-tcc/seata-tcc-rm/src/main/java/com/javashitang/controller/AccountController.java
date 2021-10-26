package com.javashitang.controller;

import com.javashitang.service.RmTccService;
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
    private RmTccService rmTccService;

    @RequestMapping("transfer")
    public String transfer(@RequestParam("fromUserId") String fromUserId,
                           @RequestParam("toUserId") String toUserId,
                           @RequestParam("money") Integer money) {
        boolean flag = rmTccService.prepare(null, fromUserId, toUserId, money);
        return flag ? "success" : "fail";
    }
}
