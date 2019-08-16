package com.yh.pojo;

import lombok.Data;

/**
 * @author yinhan
 * @date 2019-5-29 11:29:47
 */
@Data
public class Menu {
    private Integer menuId;

    private String menuName;

    /**
     * 菜单级别(1-一级菜单，2-二级菜单)
     * */
    private Integer menuLevel;

    private Integer parentId;

    /**
     * 菜单状态（0-未生效，1-生效）
     * */
    private Integer menuStatus;

    /**
     * 菜单路由
     * */
    private String menuUrl;

    private String createTime;

    private String updateTime;

}
