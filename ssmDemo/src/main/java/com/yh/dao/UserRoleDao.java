package com.yh.dao;

import com.yh.pojo.UserRole;
import com.yh.pojo.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yh 2019-6-26
 * */
@Repository
public interface UserRoleDao {

    int deleteByPrimaryKey(UserRole key);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(UserRole key);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    UserRole selectBySelective(UserRole userRole);

    UserRoleVO queryCurrentUserRole(@Param("username") String username);
}
