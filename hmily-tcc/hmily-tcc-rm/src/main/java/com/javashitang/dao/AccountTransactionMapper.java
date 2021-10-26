package com.javashitang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author lilimin
 * @since 2021-09-10
 */
public interface AccountTransactionMapper {

    @Insert("insert into account_transaction values(#{txId}, #{state}, now())")
    int insert(@Param("txId") String txId, @Param("state") Integer state);

    @Update("update account_transaction set state = #{state} where tx_id = #{txId}")
    int updateState(@Param("txId") String txId, @Param("state") Integer state);

    @Select("select state from account_transaction where tx_id = #{txId}")
    int selectState(@Param("txId") String txId);
}
