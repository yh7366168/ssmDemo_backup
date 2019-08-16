package com.yh.pojo;

import lombok.Data;

/**
 *@author  create by yh 2019-6-26
 * 用户角色表
 * */
@Data
public class UserRole {

    private String userId;

    private Integer roleId;

    private String createUser;

    private String createTime;

    private String updateUser;

    private String updateTime;
}
