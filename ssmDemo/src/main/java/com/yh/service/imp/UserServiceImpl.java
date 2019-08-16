package com.yh.service.imp;

import com.yh.dao.UserDao;
import com.yh.pojo.User;
import com.yh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public String queryById(String userId){
        return userDao.queryById(userId);
    }

    @Override
    public User queryByName(String name){
        return userDao.queryByName(name);
    }

    @Override
    public int insertUser(User user){
         return userDao.insertUser(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User user){
        return userDao.updateByPrimaryKeySelective(user);
    }
}
