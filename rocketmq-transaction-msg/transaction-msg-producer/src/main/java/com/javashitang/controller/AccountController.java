package com.javashitang.controller;

import com.javashitang.entity.AccountMsg;
import com.javashitang.service.AccountService;
import org.springframework.web.bind.annotation.RequestBody;
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
    private String transfer(@RequestBody AccountMsg accountMsg) {
        try {
            accountService.sendUpdateMsg(accountMsg);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }
}
