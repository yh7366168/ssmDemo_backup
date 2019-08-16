package com.yh.dao;

import com.yh.pojo.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yh 2019-6-26
 * */
@Repository
public interface RoleMenuDao {
    int deleteByRoleId(@Param("roleId") Integer roleId);

    int insert(RoleMenu roleMenu);

    int insertSelective(RoleMenu roleMenu);

    List<RoleMenu> selectByRoleIdAndMeneId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    int batchInsertRoleMenu(List<RoleMenu> list);
}
