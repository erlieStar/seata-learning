package com.javashitang.controller;

import com.javashitang.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-07
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("transfer")
    private String transfer(@RequestParam("userId") String userId,
                            @RequestParam("money") Integer money) {
        accountService.update(userId, money);
        return "success";
    }
}
