package com.javashitang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author lilimin
 * @since 2021-09-06
 */
public interface AccountFlowMapper {

    @Insert("insert account_flow values (#{flowId}, #{userId}, #{money}, #{status})")
    int insertFlow(@Param("flowId") Integer flowId, @Param("userId") String userId,
                   @Param("money") Integer money, @Param("status") Integer status);

    @Select("select count(*) from account_flow where flow_id = #{flowId}")
    int selectByFlowId(@Param("flowId") Integer flowId);
}
