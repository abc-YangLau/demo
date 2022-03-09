package com.framework.tables.perAccountInfo.perAccountInfoDAO;


import com.framework.tables.perAccountInfo.perAccountInfoEntity.PerAccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerAccountDao {
    //查询所有账户信息
    List<PerAccountEntity> getAll();
    //查询某个账户
    PerAccountEntity getOne(@Param("accountNo") String accountNo);
    //新增账户信息
    void insert(@Param("PerAccountEntity") PerAccountEntity perAccountEntity);
    //更新账户信息
    void update(@Param("PerAccountEntity") PerAccountEntity perAccountEntity);
    //删除账户信息
    void delete(@Param("accountNo") String accountNo);
}
