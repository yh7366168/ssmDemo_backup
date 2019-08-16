package com.yh.util.constant;

/**
 * @author yh create on 2019/8/15
 */
public enum RoleConstant {

    ADMIN(101);

    private Integer roleId;

    private RoleConstant(Integer roleId){
        this.roleId = roleId;
    }

    public Integer getRoleId(){
        return roleId;
    }

}
