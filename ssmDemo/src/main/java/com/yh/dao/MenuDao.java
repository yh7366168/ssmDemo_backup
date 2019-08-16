package com.yh.dao;

import com.yh.pojo.Menu;
import com.yh.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yinhan
 * @date 2019-5-29 11:31:42
 */
@Repository
public interface MenuDao {

    /**
     * 查询符合条件菜单列表
     * @param params 查询条件Map
     * @return list
     */
    List<Menu> queryListByParams(Map<String, Object> params);

    /**
     * 主键查询
     * @param id 主键
     * @return list
     */
    Menu queryMuneById(Integer id);

    /**
     * 统计所有的菜单
     * @param params
     * @return Integer
     */
    Integer queryPageCount(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return list
     */
    List<Menu> queryPageList(Map<String, Object> params);


    /**
     * 权限设计，角色可以查看的菜单
     * @param params
     * @return list
     * */
    List<Menu> queryRoleMenuListByParams(Map<String, Object> params);
}
