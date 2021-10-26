package com.javashitang.service;

import com.javashitang.dao.AccountInfoMapper;
import io.seata.core.context.RootContext;
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
public class RmTccServiceImpl implements RmTccService {

    @Resource
    private AccountInfoMapper accountInfoMapper;

    @Override
    public boolean prepare(BusinessActionContext context, String fromUserId, String toUserId, Integer money) {
        log.info("prepare");
        int result = accountInfoMapper.selectByUserId(toUserId);
        return result == 1;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        log.info("commit");
        String toUserId = String.valueOf(context.getActionContext("toUserId"));
        Integer money = (Integer) context.getActionContext("money");
        accountInfoMapper.updateMoney(toUserId, money);
        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        log.info("cancel");
        return true;
    }
}
