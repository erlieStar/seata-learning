package com.javashitang.client;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lilimin
 * @since 2021-09-06
 */
@FeignClient(value="hmily-tcc-rm", url="http://127.0.0.1:20001")
public interface RmClient {

    @GetMapping("account/transfer")
    @Hmily
    String transfer(@RequestParam("userId") String userId,
                    @RequestParam("money") Integer money);
}
