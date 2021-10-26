package com.javashitang.entity;

import lombok.Data;

/**
 * @author lilimin
 * @since 2021-09-08
 */
@Data
public class AccountMsg {

    private Integer flowId;

    private String fromUserId;

    private String toUserId;

    private Integer money;
}
