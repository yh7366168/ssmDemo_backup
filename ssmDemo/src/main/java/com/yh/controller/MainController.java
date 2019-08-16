package com.yh.controller;

import com.alibaba.fastjson.JSON;
import com.yh.dao.UserRoleDao;
import com.yh.pojo.Menu;
import com.yh.pojo.User;
import com.yh.pojo.UserRole;
import com.yh.pojo.vo.UserRoleVO;
import com.yh.service.MenuService;
import com.yh.service.UserService;
import com.yh.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private HttpSession session;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleDao userRoleDao;

    @RequestMapping("/loginMain")
    public ModelAndView loginMain(ModelAndView model) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("secondMenuLevel", "2");
        //查询所有的二级菜单
        List<Menu> menuAllList = menuService.queryListByParams(params);
        //通过parent_id分组
        Map<Integer, List<Menu>> menuMapList = menuAllList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        Map<String, List<Menu>> resultMapList = new HashMap<String, List<Menu>>();
        //替换key
        for (Integer parentId : menuMapList.keySet()) {
            List<Menu> list = menuMapList.get(parentId);
            Menu parentMenu = menuService.queryMuneById(parentId);
            resultMapList.put(parentMenu.getMenuName(), list);
        }
        model.addObject("resultMapList", resultMapList);
        model.setViewName("main/main");
        return model;
    }

    /**
     * 登录
     */
    @RequestMapping("/loginCheckMain")
    public ModelAndView loginCheckMain(HttpServletRequest request, ModelAndView model, @RequestParam("username") String username) {
        try{
            User user = userService.queryByName(username);
            user.setUpdateTime(DateUtil.getNow());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("secondMenuLevel", "2");
            params.put("username", username);
            //查询该角色下的所有菜单
            List<Menu> menuAllList = menuService.queryRoleMenuListByParams(params);
            //通过parent_id分组
            Map<Integer, List<Menu>> menuMapList = menuAllList.stream().collect(Collectors.groupingBy(Menu::getParentId));
            Map<String, List<Menu>> resultMapList = new HashMap<String, List<Menu>>();
            //替换key
            for (Integer parentId : menuMapList.keySet()) {
                List<Menu> list = menuMapList.get(parentId);
                Menu parentMenu = menuService.queryMuneById(parentId);
                resultMapList.put(parentMenu.getMenuName(), list);
            }
            model.addObject("resultMapList", resultMapList);
            //查询当前用户信息
            UserRoleVO userRoleVO = userRoleDao.queryCurrentUserRole(username);
            model.addObject("userRoleVO", userRoleVO);
            model.setViewName("main/main");
            //把用户信息存放在session里面
            session.setAttribute("userRoleVO", userRoleVO);
        }catch (Exception e){
            log.info("loginCheckMain-异常信息：{}", e);
            model.addObject("expMsg", e.getMessage());
        }
        return model;
    }
}
