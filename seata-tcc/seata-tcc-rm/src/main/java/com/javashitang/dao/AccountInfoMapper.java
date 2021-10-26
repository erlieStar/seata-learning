package com.javashitang.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author lilimin
 * @since 2021-09-06
 */
public interface AccountInfoMapper {

    @Update("update account_info set balance = balance + #{money} where balance + #{money} > 0 and user_id = #{userId}")
    int updateMoney(@Param("userId") String userId, @Param("money") Integer money);

    @Select("select count(*) from account_info where user_id = #{userId}")
    int selectByUserId(@Param("userId") String userId);
}
