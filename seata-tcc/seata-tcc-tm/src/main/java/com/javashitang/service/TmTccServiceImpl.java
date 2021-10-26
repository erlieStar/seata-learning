package com.javashitang.service;

import com.javashitang.dao.AccountInfoMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lilimin
 * @since 2021-09-03
 */
@Slf4j
@Service
public class TmTccServiceImpl implements TmTccService {

    @Resource
    private AccountInfoMapper accountInfoMapper;

    @Override
    public boolean prepare(BusinessActionContext context, String fromUserId, String toUserId, Integer money) {
        log.info("prepare");
        int result = accountInfoMapper.updateMoney(fromUserId, money * -1);
        return result == 1;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        log.info("commit");
        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        log.info("cancel");
        String fromUserId = String.valueOf(context.getActionContext("fromUserId"));
        Integer money = (Integer) context.getActionContext("money");
        accountInfoMapper.updateMoney(fromUserId, money);
        return true;
    }
}
