package com.javashitang.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author lilimin
 * @since 2021-09-03
 */
@LocalTCC
public interface RmTccService {

    @TwoPhaseBusinessAction(name = "RmTccService", commitMethod = "commit", rollbackMethod = "cancel")
    boolean prepare(BusinessActionContext context,
                    @BusinessActionContextParameter(paramName = "fromUserId") String fromUserId,
                    @BusinessActionContextParameter(paramName = "toUserId") String toUserId,
                    @BusinessActionContextParameter(paramName = "money") Integer money);

    /**
     * 确认方法，可以重命名，但要和commitMethod保持一致
     */
    boolean commit(BusinessActionContext context);

    /**
     * 取消方法，可以重命名，但要和rollbackMethod保持一致
     */
    boolean cancel(BusinessActionContext context);
}
