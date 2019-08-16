package com.yh.service;

import com.yh.pojo.User;

/**
 * @author yh
 * @date 2019-5-29 14:29:16
 */
public interface UserService {

    /**
     * 通过用户Id查询用户信息
     * */
    String queryById(String userId);

    /**
     * 通过用户名字查询用户信息
     * @param name, name唯一
     * */
    User queryByName(String name);

    /**
     * 新增用户
     * */
    int insertUser(User user);

    int updateByPrimaryKeySelective(User user);
}
