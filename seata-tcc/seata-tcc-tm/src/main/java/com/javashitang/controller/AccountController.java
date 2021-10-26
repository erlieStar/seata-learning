package com.javashitang.controller;

import com.javashitang.client.RmAccountClient;
import com.javashitang.service.TmTccService;
import io.seata.spring.annotation.GlobalTransactional;
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
    private TmTccService tmTccService;
    @Resource
    private RmAccountClient rmAccountClient;


    @GlobalTransactional
    @RequestMapping("transfer")
    public String transfer(@RequestParam("fromUserId") String fromUserId,
                           @RequestParam("toUserId") String toUserId,
                           @RequestParam("money") Integer money) {
        boolean ret = tmTccService.prepare(null, fromUserId, toUserId, money);
        if (!ret) {
            throw new RuntimeException("预扣款失败");
        }
        String rmRet = rmAccountClient.transfer(fromUserId, toUserId, money);
        if ("fail".equals(rmRet)) {
            throw new RuntimeException("预收款失败");
        }
        return "success";
    }
}
