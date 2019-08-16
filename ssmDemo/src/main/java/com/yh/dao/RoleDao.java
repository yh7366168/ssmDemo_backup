package com.yh.dao;

import com.yh.pojo.Role;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.List;

/**
 * @author yh 2019-6-11
 * */
@Repository
public interface RoleDao {

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Integer queryPageCount(Map<String, Object> params);

    List<Role> queryPageList(Map<String, Object> params);
}
