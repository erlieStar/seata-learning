package com.javashitang.mq;

import com.javashitang.dao.AccountFlowMapper;
import com.javashitang.entity.PayInfo;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author lilimin
 * @since 2021-09-08
 */
@Component
@RocketMQMessageListener(topic = "pay_topic", consumerGroup = "pay_group", selectorExpression = "pay_tag")
public class NotifyListener implements RocketMQListener<PayInfo> {

    @Resource
    private AccountFlowMapper accountFlowMapper;

    @Override
    public void onMessage(PayInfo message) {
    }
}
