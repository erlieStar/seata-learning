package com.javashitang.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lilimin
 * @since 2021-09-12
 */
@FeignClient(value = "seata-tcc-rm", url = "http://127.0.0.1:30001")
public interface RmAccountClient {

    @RequestMapping("account/transfer")
    String transfer(@RequestParam("fromUserId") String fromUserId,
                    @RequestParam("toUserId") String toUserId,
                    @RequestParam("money") Integer money);

}
