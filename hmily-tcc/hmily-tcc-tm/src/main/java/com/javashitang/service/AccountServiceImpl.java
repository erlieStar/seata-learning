package com.javashitang.service;

import com.javashitang.client.RmClient;
import com.javashitang.dao.AccountInfoMapper;
import com.javashitang.dao.AccountTransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-06
 *
 * try {
 *     try幂等校验
 *     try悬挂处理
 *     检查余额是够扣减金额
 *     扣减金额
 * }
 * confirm {
 *     空
 * }
 * cancel {
 *     cancel幂等校验
 *     cancel空回滚处理
 *     增加可用余额
 * }
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountTransactionMapper transactionMapper;
    @Resource
    private RmClient rmClient;

    @Hmily(confirmMethod = "confirm", cancelMethod = "cancel")
    @Transactional
    @Override
    public void update(String fromUserId, String toUserId, Integer money) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("tm begin try, transId: {}", transId);
        transactionMapper.insert(transId, 1);
        if (accountInfoMapper.updateMoney(fromUserId, money * - 1) <= 0) {
            log.error("subtractAccountBalance error");
            throw new HmilyRuntimeException("扣款失败" + transId);
        }
        rmClient.transfer(toUserId, money);
    }

    @Override
    public void confirm(String userId, Integer money) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("tm begin confirm, transId: {}", transId);
    }

    @Transactional
    @Override
    public void cancel(String userId, Integer money) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("tm begin cancel, transId: {}", transId);
        Integer state = transactionMapper.selectState(transId);
        if (state == null) {
            // 没有执行try，先插入一条记录，不让执行try
            // 插入成功则结束
            // 插入失败则try方法正在执行，后续会重试
            transactionMapper.insert(transId, 3);
            return;
        } else if (state == 1) {
            // 正常执行cancel
            transactionMapper.updateState(transId, state);
            accountInfoMapper.updateMoney(userId, money);
        } else if (state == 3) {
            // confirm后cancel，报警
        }
    }
}
