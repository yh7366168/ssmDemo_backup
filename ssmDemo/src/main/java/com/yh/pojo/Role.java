package com.yh.pojo;

import lombok.Data;

/**
 * @author yh create in 2019-6-11
 * */
@Data
public class Role {
    /**
     * 角色ID
     * */
    private Integer roleId;

    /**
     * 角色名称
     * */
    private String roleName;

    /**
     * 角色描述
     * */
    private String roleDesc;

    /**
     * 角色状态
     * */
    private Integer roleStatus;

    /**
     * 创建人
     * */
    private String createUser;

    /**
     * 创建时间
     * */
    private String createTime;

    /**
     * 修改人
     * */
    private String updateUser;

    /**
     * 修改时间
     * */
    private String updateTime;
}
