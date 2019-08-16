package com.yh.controller;

import com.alibaba.fastjson.JSON;
import com.yh.pojo.Menu;
import com.yh.pojo.PageBean;
import com.yh.service.MenuService;
import com.yh.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yinhan
 * @date 2019-5-29
 */
@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/queryMenuList")
    public ModelAndView queryMenuList(ModelAndView model){
        Map<String, Object> params = new HashMap<String, Object>();
        List<Menu> menuList = menuService.queryListByParams(params);
        log.info("queryMenuList--查询结果：{}", JSON.toJSONString(menuList));
        model.addObject("menuList", menuList);
        model.setViewName("system/menuList");
        return model;
    }

    @RequestMapping(value = "/queryPageList")
    public ModelAndView queryPageList(ModelAndView model, @RequestParam("curPage") String currentPage){
        Integer curPage = Integer.valueOf(currentPage);
        log.info("queryPageList--分页查询开始，查询第{}页的信息！", curPage);
        PageUtil<Menu> pageUtil = new PageUtil<>();
        PageBean<Menu> pageBean = pageUtil.queryPageList(Menu.class, curPage, null);
        log.info("queryPageList--分页查询结束，查询结果{}", JSON.toJSONString(pageBean));
        model.addObject("menuList",pageBean.getPageList());
        model.addObject("pageBean",pageBean);
        model.setViewName("system/menuList");
        return model;
    }

    /**
     * 生成菜单树
     * */
    @RequestMapping("/generateMenuTree")
    public ModelAndView generateMenuTree(ModelAndView model){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("secondMenuLevel","2");
        //查询所有的二级菜单
        List<Menu> menuAllList = menuService.queryListByParams(params);
        //通过parent_id分组
        Map<Integer, List<Menu>> menuMapList = menuAllList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        log.info("generateMenuTree--一共{}条数据，查询结果{}", JSON.toJSONString(menuAllList.size()), JSON.toJSONString(menuAllList));
        Map<String, List<Menu>> resultMapList = new HashMap<String, List<Menu>>();
        //替换key
        for(Integer parentId : menuMapList.keySet()){
            List<Menu> list =  menuMapList.get(parentId);
            Menu parentMenu = menuService.queryMuneById(parentId);
            resultMapList.put(parentMenu.getMenuName(), list);
        }
        model.addObject("resultMapList",resultMapList);
        model.setViewName("main/mian_left_menu");
        return model;
    }

    /**
     * 条件查询按钮
     * required=false 不存在赋值null
     * */
    @RequestMapping("/queryMenuListByParams")
    public ModelAndView queryMenuListByParams(@RequestParam(value = "menuName", required = false)String menuName,
                                              @RequestParam(value = "menuLevel", required = false)String menuLevel,
                                              @RequestParam("curPage")String currentPage){
        Integer curPage = Integer.valueOf(currentPage);
        ModelAndView model = new ModelAndView();
        Map<String, Object> params = new HashMap<>();
        Menu menuDto = new Menu();
        if(StringUtils.hasText(menuName)){
            params.put("menuName", menuName);
            menuDto.setMenuName(menuName);
        }
        if(StringUtils.hasText(menuLevel)){
            params.put("menuLevel", Integer.valueOf(menuLevel));
            menuDto.setMenuLevel(Integer.valueOf(menuLevel));
        }

        PageUtil<Menu> pageUtil = new PageUtil();
        log.info("queryMenuListByParams--查询开始，第{}页，查询条件：{}", curPage ,JSON.toJSONString(params));
        PageBean<Menu> pageBean = pageUtil.queryPageList(Menu.class, curPage, params);
        List<Menu> menuList = pageBean.getPageList();
        model.addObject("pageBean", pageBean);
        model.addObject("menuList", menuList);
        model.addObject("menuDto",menuDto);
        log.info("queryMenuListByParams--查询结束，pageBean：{}，menuList：{}， menuDto：{}",
                JSON.toJSONString(pageBean), JSON.toJSONString(menuList), JSON.toJSONString(menuDto) );
        model.setViewName("system/menuList");
        return model;
    }
}
