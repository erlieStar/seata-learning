package com.javashitang.service;

import com.javashitang.dao.AccountInfoMapper;
import com.javashitang.dao.AccountTransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-06
 *
 * try {
 *   空
 * }
 * confirm {
 *   confirm幂等校验
 *   正式增加金额
 * }
 * cancel {
 *   空
 * }
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountTransactionMapper transactionMapper;

    @Hmily(confirmMethod = "confirm", cancelMethod = "cancel")
    @Override
    public void update(String userId, Integer money) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("tm begin try, transId: {}", transId);
    }

    @Transactional
    @Override
    public void confirm(String userId, Integer money) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("tm begin confirm, transId: {}", transId);
        Integer state = transactionMapper.selectState(transId);
        if (state == null || state == 2 || state == 3) {
            return;
        }
        // 这里插入的时候还是要加一个分布式锁，有可能2个线程同时查询出来state=1
        accountInfoMapper.updateMoney(userId, money);
    }

    @Override
    public void cancel(String userId, Integer money) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("tm begin cancel, transId: {}", transId);
    }
}
