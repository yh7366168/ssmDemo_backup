package com.yh.dao;

import com.yh.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    String queryById(String userId);

    User queryByName(String name);

    int insertUser(User user);

    /**
     * 分页查询-统计总数
     * @param params
     * @return Integer
     */
    Integer queryPageCount(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return list
     */
    List<User> queryPageList(Map<String, Object> params);

    /**
     *更新用户信息
     * @param user
     * @return int
     * */
    int updateByPrimaryKeySelective(User user);
}
