package com.javashitang.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lilimin
 * @since 2021-09-08
 */
@FeignClient(value = "pay", url = "http://127.0.0.1:30001")
public interface PayClient {

    @RequestMapping("recharge")
    String check(@RequestParam("flowId") String flowId);
}
