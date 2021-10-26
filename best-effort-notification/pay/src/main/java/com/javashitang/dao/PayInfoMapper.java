package com.javashitang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.plugin.Intercepts;

/**
 * @author lilimin
 * @since 2021-09-06
 */
public interface PayInfoMapper {

    @Insert("update account_info set balance = balance + #{money} where balance + #{money} > 0 and user_id = #{userId}")
    int insertPayInfo(@Param("flowId") String flowId, @Param("status") Integer status);
}
