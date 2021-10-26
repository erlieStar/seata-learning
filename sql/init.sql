# Account
DROP SCHEMA IF EXISTS db_account_1;
CREATE SCHEMA db_account_1;
USE db_account_1;

-- hmily tcc模式用
CREATE DATABASE `hmily`;

-- 账户表
CREATE TABLE `account_info`
(
    `id`      INT(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户id',
    `balance` INT(11)      NOT NULL DEFAULT 0 COMMENT '用户余额',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 账户流水表
CREATE TABLE `account_flow`
(
    `flow_id` INT(11)      NOT NULL COMMENT '流水id',
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户id',
    `money`   INT(11)      NOT NULL COMMENT '变动金额' DEFAULT 0,
    `status`  INT(11)      NOT NULL COMMENT '状态，0待支付，1已完成' DEFAULT 0,
    PRIMARY KEY (`flow_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `pay_info`
(
    `flow_id` INT(11)      NOT NULL COMMENT '流水id',
    `status`  INT(11)      NOT NULL COMMENT '状态，0失败，1成功' DEFAULT 0,
    PRIMARY KEY (`flow_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO account_info (id, user_id, balance)
VALUES (1, '1001', 10000);
INSERT INTO account_info (id, user_id, balance)
VALUES (2, '1002', 10000);

-- tcc记录表
CREATE TABLE `account_transaction`
(
    `tx_id`       varchar(64) NOT NUll COMMENT '事务Id',
    `state`       int(64)     NOT NUll COMMENT '状态，1初始化，2已提交，3已回滚',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`tx_id`)
);

-- seata at模式用
CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)   NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11)      NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;


DROP SCHEMA IF EXISTS db_account_2;
CREATE SCHEMA db_account_2;
USE db_account_2;

-- 账户表
CREATE TABLE `account_info`
(
    `id`      INT(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户id',
    `balance` INT(11)      NOT NULL DEFAULT 0 COMMENT '用户余额',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 账户流水表
CREATE TABLE `account_flow`
(
    `flow_id` INT(11)      NOT NULL COMMENT '流水id',
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户id',
    `money`   INT(11)      NOT NULL COMMENT '变动金额' DEFAULT 0,
    `status`  INT(11)      NOT NULL COMMENT '状态，0待支付，1已完成' DEFAULT 0,
    PRIMARY KEY (`flow_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO account_info (id, user_id, balance)
VALUES (1, '1001', 10000);
INSERT INTO account_info (id, user_id, balance)
VALUES (2, '1002', 10000);

-- tcc记录表
CREATE TABLE `account_transaction`
(
    `tx_id`       varchar(64) NOT NUll COMMENT '事务Id',
    `state`       int(64)     NOT NUll COMMENT '状态，1初始化，2已提交，3已回滚',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`tx_id`)
);

CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)   NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11)      NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;


DROP SCHEMA IF EXISTS seata;
CREATE SCHEMA seata;
USE seata;

-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_gmt_modified_status` (`gmt_modified`, `status`),
    KEY `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- the table to store BranchSession data
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME(6),
    `gmt_modified`      DATETIME(6),
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- the table to store lock data
CREATE TABLE IF NOT EXISTS `lock_table`
(
    `row_key`        VARCHAR(128) NOT NULL,
    `xid`            VARCHAR(128),
    `transaction_id` BIGINT,
    `branch_id`      BIGINT       NOT NULL,
    `resource_id`    VARCHAR(256),
    `table_name`     VARCHAR(32),
    `pk`             VARCHAR(36),
    `gmt_create`     DATETIME,
    `gmt_modified`   DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_branch_id` (`branch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;