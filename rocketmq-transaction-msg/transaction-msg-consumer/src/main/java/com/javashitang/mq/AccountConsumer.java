package com.javashitang.mq;

import com.javashitang.dao.AccountFlowMapper;
import com.javashitang.dao.AccountMapper;
import com.javashitang.entity.AccountMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-08
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "tx_consumer", topic = "account_topic", selectorExpression = "account_tag")
public class AccountConsumer implements RocketMQListener<AccountMsg> {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountFlowMapper accountFlowMapper;

    /**
     * 需要根据流水号加幂等，我就不加幂等代码了
     */
    @Override
    @Transactional
    public void onMessage(AccountMsg accountMsg) {
        log.info("onMessage param flowId: {}", accountMsg.getFlowId());
        accountMapper.updateMoney(accountMsg.getToUserId(), accountMsg.getMoney());
        accountFlowMapper.insertFlow(accountMsg.getFlowId(), accountMsg.getToUserId(), accountMsg.getMoney(), 1);
    }
}
