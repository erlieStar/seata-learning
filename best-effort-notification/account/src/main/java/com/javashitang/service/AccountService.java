package com.javashitang.service;

/**
 * @author lilimin
 * @since 2021-09-06
 */
public interface AccountService {

    void recharge(String flowId, String userId, Integer money);

    void check(String flowId);
}
