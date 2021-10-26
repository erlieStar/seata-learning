package com.javashitang.mq;

import com.alibaba.fastjson.JSONObject;
import com.javashitang.dao.AccountFlowMapper;
import com.javashitang.entity.AccountMsg;
import com.javashitang.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author lilimin
 * @since 2021-09-08
 */
@Slf4j
@Component
@RocketMQTransactionListener
public class AccountListener implements RocketMQLocalTransactionListener {

    @Resource
    private AccountService accountService;
    @Resource
    private AccountFlowMapper accountFlowMapper;

    /**
     * 事务消息发送成功回调
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            String messageStr = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
            AccountMsg accountMsg = JSONObject.parseObject(messageStr, AccountMsg.class);
            accountService.update(accountMsg);
        } catch (Exception e) {
            log.error("executeLocalTransaction error", e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     * 事务状态回查
     * 有流水说明账户更新成功，否则更新失败
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String messageStr = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        AccountMsg accountMsg = JSONObject.parseObject(messageStr, AccountMsg.class);
        int result = accountFlowMapper.selectByFlowId(accountMsg.getFlowId());
        if (result == 1) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
