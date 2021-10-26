package com.javashitang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author lilimin
 * @since 2021-09-06
 */
public interface AccountFlowMapper {

    @Insert("insert account_flow values (#{flowId}, #{userId}, #{money}, #{status})")
    int insertFlow(@Param("flowId") Integer flowId, @Param("userId") String userId,
                   @Param("money") Integer money, @Param("status") Integer status);
}
