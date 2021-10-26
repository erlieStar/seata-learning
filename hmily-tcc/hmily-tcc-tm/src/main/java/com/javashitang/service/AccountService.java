package com.javashitang.service;

/**
 * @author lilimin
 * @since 2021-09-06
 */
public interface AccountService {

    void update(String fromUserId, String toUserId, Integer money);

    void confirm(String userId, Integer money);

    void cancel(String userId, Integer money);
}
