package com.yh.pojo;

import lombok.Data;

import java.util.List;

/**
 * 工具类：分页
 *
 * @author yh
 * @date 2019-6-3 10:42:04
 */
@Data
public class PageBean<T> {
    /**
     * 当前页
     * */
    private Integer curPage;

    /**
     * 每页大小
     * */
    private Integer pageSize;

    /**
     * 总记录数
     * */
    private Integer totalCount;

    /**
     * 总页数
     * */
    private Integer totalPage;

    /**
     * 每页信息
     * */
    private List<T> pageList;

    /**
     * 当前页数据量
     * */
    private Integer curPageCount;
}
