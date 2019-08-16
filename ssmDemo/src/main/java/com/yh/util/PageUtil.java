package com.yh.util;

import com.alibaba.fastjson.JSON;
import com.yh.pojo.PageBean;
import com.yh.util.constant.SystemExceptionMessage;
import com.yh.util.exception.YhSystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类：
 *
 * @author yh
 * @date 2019-6-3 11:25:44
 */
@Slf4j
@Controller
@RequestMapping("/pageUtil")
public class PageUtil<T> {

    /*默认页码大小10*/
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 分页查询
     * @param clzss   实体类class对象
     * @param curPage 当前页
     * @param params  分页查询条件
     * @return pageBean
     */
    public PageBean<T> queryPageList(Class<T> clzss, Integer curPage, Map<String, Object> params) {
        log.info("PageUtil--queryPageList，入参curPage:{}, params:{}", curPage, JSON.toJSONString(params));
        Integer totalCount;
        Integer beginNum;
        List<T> pageList;
        Integer pageSize = DEFAULT_PAGE_SIZE;
        int totalPage;

        String className = clzss.getSimpleName();
        String nameDao = className + "Dao";
        nameDao = StringUtil.lowerFirstString(nameDao);
        PageBean<T> pageBean = new PageBean<T>();
        if (curPage == null || curPage <= 0) {
            curPage = 1;
        }
        if (params == null) {
            params = new HashMap<>();
        }
        pageBean.setCurPage(curPage);
        pageBean.setPageSize(pageSize);
        beginNum = pageSize * (curPage - 1);
        //获取获取上下文中的bean的“objectDao”
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        Object objectDao = context.getBean(nameDao);
        Class objectDaoClz = objectDao.getClass();
        try {
            Method methodOne = objectDaoClz.getMethod("queryPageCount", Map.class);
            totalCount = (Integer) methodOne.invoke(objectDao, params);
            pageBean.setTotalCount(totalCount);
            log.info("PageUtil--queryCount，入参:{}，查询到{}条结果",JSON.toJSONString(params), JSON.toJSONString(totalCount));
            if (totalCount % pageSize <= 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = totalCount / pageSize + 1;
            }
            pageBean.setTotalPage(totalPage);
        } catch (Exception e) {
            log.info("PageUtil--queryCount执行失败！异常信息：{}" , e);
            throw new YhSystemException(SystemExceptionMessage.CommonMessage.SYSTEM_MESSAGE);
        }
        try {
            Method queryListMethod = objectDaoClz.getMethod("queryPageList", Map.class);
            params.put("beginNum", beginNum);
            params.put("pageSize", pageSize);
            pageList = (List<T>) queryListMethod.invoke(objectDao, params);
            log.info("PageUtil--queryPageList,入参：{}, 查询结果：{}", JSON.toJSONString(params), JSON.toJSONString(pageList));
            pageBean.setPageList(pageList);
            Integer curPageCount = 0;
            if(!CollectionUtils.isEmpty(pageList)){
                curPageCount = pageList.size();
            }
            pageBean.setCurPageCount(curPageCount);
        } catch (Exception e) {
            log.info("PageUtil--queryPageList执行失败！异常信息：{}", e);
            throw new YhSystemException(SystemExceptionMessage.CommonMessage.SYSTEM_MESSAGE);
        }
        log.info("PageUtil--queryPageList-分页查询结果：{}", JSON.toJSONString(pageBean));
        return pageBean;
    }

    /**
     * 分页查询
     * @param clzss    实体类class对象
     * @param curPage  当前页
     * @param pageSize 每页大小
     * @param params   分页查询条件
     * @return pageBean
     */
    public PageBean<T> queryPageList(Class<T> clzss, Integer curPage, Integer pageSize, Map<String, Object> params) {
        log.info("PageUtil--分页查询开始，入参curPage:{}，pageSize:{}，params:{}", String.valueOf(curPage), String.valueOf(pageSize), JSON.toJSONString(params));
        Integer totalCount;
        Integer beginNum;
        List<T> pageList;
        int totalPage = 0;

        String className = clzss.getSimpleName();
        String nameDao = className + "Dao";
        nameDao = StringUtil.lowerFirstString(nameDao);
        PageBean<T> pageBean = new PageBean<T>();
        if (curPage == null || curPage < 1) {
            curPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (params == null) {
            params = new HashMap<>();
        }
        pageBean.setCurPage(curPage);
        pageBean.setPageSize(pageSize);
        beginNum = pageSize * (curPage - 1);
        //获取获取上下文中的bean的“objectDao”
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        Object objectDao = context.getBean(nameDao);
        Class objectDaoClz = objectDao.getClass();
        try {
            Method methodOne = objectDaoClz.getMethod("queryPageCount", Map.class);
            log.info("PageUtil--queryCount查询，入参{}", JSON.toJSONString(params));
            totalCount = (Integer) methodOne.invoke(objectDao, params);
            pageBean.setTotalCount(totalCount);
            log.info("PageUtil--queryCount查询到{}条结果", JSON.toJSONString(totalCount));
            if (totalCount % pageSize <= 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = totalCount / pageSize + 1;
            }
            pageBean.setTotalPage(totalPage);
        } catch (Exception e) {
            log.info("PageUtil--queryCount执行失败！");
            throw new YhSystemException(SystemExceptionMessage.CommonMessage.SYSTEM_MESSAGE);
        }
        try {
            Method queryListMethod = objectDaoClz.getMethod("queryPageList", Map.class);
            params.put("beginNum", beginNum);
            params.put("pageSize", pageSize);
            log.info("PageUtil--queryPageList查询，入参{}", JSON.toJSONString(params));
            pageList = (List<T>) queryListMethod.invoke(objectDao, params);
            log.info("PageUtil--queryPageList查询结果：{}", JSON.toJSONString(pageList));
            pageBean.setPageList(pageList);
        } catch (Exception e) {
            log.info("PageUtil--queryPageList执行失败！异常信息{}", e);
            throw new YhSystemException(SystemExceptionMessage.CommonMessage.SYSTEM_MESSAGE);
        }
        log.info("PageUtil--分页查询结束，结果：{}", JSON.toJSONString(pageBean));
        return pageBean;
    }
}
