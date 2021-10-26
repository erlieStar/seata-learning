package com.javashitang.service;

import com.javashitang.dao.AccountFlowMapper;
import com.javashitang.dao.AccountMapper;
import com.javashitang.entity.AccountMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-06
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountFlowMapper accountFlowMapper;

    /**
     * 需要根据流水号加幂等哈，我就不加了
     */
    @Override
    public void sendUpdateMsg(AccountMsg accountMsg) {
        Message<AccountMsg> message = MessageBuilder.withPayload(accountMsg).build();
        rocketMQTemplate.sendMessageInTransaction("account_topic:account_tag", message, null);
    }

    /**
     * 更新账户
     * 增加流水
     */
    @Override
    @Transactional
    public void update(AccountMsg accountMsg) {
        accountMapper.updateMoney(accountMsg.getFromUserId(), accountMsg.getMoney() * -1);
        accountFlowMapper.insertFlow(accountMsg.getFlowId(), accountMsg.getFromUserId(), accountMsg.getMoney(), 1);
    }
}
