package com.javashitang.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountServiceImpl implements AccountService {

    @Override
    public void recharge(String flowId, String userId, Integer money) {

    }

    @Override
    public void check(String flowId) {

    }

}
