package com.javashitang.service;

/**
 * @author lilimin
 * @since 2021-09-08
 */
public interface PayService {

    String recharge(String flowId);

    String check(String flowId);
}
