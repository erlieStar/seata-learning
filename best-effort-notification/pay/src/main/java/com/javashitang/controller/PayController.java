package com.javashitang.controller;

import com.javashitang.service.PayService;
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
public class PayController {

    @Resource
    private PayService payService;

    @RequestMapping("recharge")
    private String recharge(@RequestParam("flowId") String flowId) {
        return payService.recharge(flowId);
    }

    /**
     * 账户系统一般会对没有收到充值结果的流水反查（定时任务），所以会提供一个回查接口
     */
    @RequestMapping("check")
    private String check(@RequestParam("flowId") String flowId) {
        return payService.recharge(flowId);
    }
}
