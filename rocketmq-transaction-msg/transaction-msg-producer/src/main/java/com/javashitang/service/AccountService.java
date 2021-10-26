package com.javashitang.service;

import com.javashitang.entity.AccountMsg;

/**
 * @author lilimin
 * @since 2021-09-06
 */
public interface AccountService {

    void sendUpdateMsg(AccountMsg accountMsg);

    void update(AccountMsg accountMsg);
}
