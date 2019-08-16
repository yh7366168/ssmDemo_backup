package com.yh.controller;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yh.dao.RoleDao;
import com.yh.dao.RoleMenuDao;
import com.yh.pojo.Role;
import com.yh.pojo.RoleMenu;
import com.yh.pojo.vo.RoleDetailVO;
import com.yh.util.exception.YhSimpleException;
import jdk.nashorn.internal.objects.NativeJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

/**
 * @author yh create on 2019/7/11
 */
@Slf4j
@Controller
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private RoleDao roleDao;

    @RequestMapping("/queryRoleMenuDetail")
    public ModelAndView queryRoleMenuDetail(Integer roleId) {
        ModelAndView model = new ModelAndView();
        try{
            RoleDetailVO vo = spellRoleDetailVO(roleId);
            vo.setRoleId(roleId);
            //查询角色信息
            Role role = roleDao.selectByPrimaryKey(roleId);
            if(role==null){
                log.info("queryRoleMenuDetail-查询角色信息失败，roleId：{}", roleId);
                throw new YhSimpleException("查询角色信息失败！");
            }
            vo.setRoleName(role.getRoleName());
            log.info("queryRoleMenuDetail-roleId:{}， RoleDetailVO:{}", roleId, JSON.toJSONString(vo));
            model.addObject("roleDetailVO", vo);
            model.setViewName("system/roleDetail");
        }catch ( Exception e){
            log.info("查询用户权限详情页面失败！", e);
        }
        return model;
    }

    /**
     * 查询按钮的权限
     */
    @RequestMapping("queryButtonRole")
    public ModelAndView queryButtonRole(Integer roleId, Integer menuId) {
        ModelAndView model = new ModelAndView();
        List<RoleMenu> roleMenuList = roleMenuDao.selectByRoleIdAndMeneId(roleId, menuId);
        if (roleMenuList == null) {

        } else {
            Integer butttonId = null;
            for (RoleMenu roleMenu : roleMenuList) {
                butttonId = roleMenu.getButtonId();
                if (butttonId == 1) {
                    model.addObject("isCanSelect", true);
                } else if (butttonId == 2) {
                    model.addObject("isCanAdd", true);
                } else if (butttonId == 3) {
                    model.addObject("isCanUpdate", true);
                } else if (butttonId == 4) {
                    model.addObject("isCanDel", true);
                } else {
                    model.addObject("isCanCheck", true);
                }
            }
        }
        return model;
    }

    /**
     * 拼装页面显示对象
     */
    private RoleDetailVO spellRoleDetailVO(Integer roleId) {
        RoleDetailVO roleVO = new RoleDetailVO();
        Integer menuId = null;
        List<RoleMenu> roleMenuList = null;
        //用户管理
        menuId = 10001;
        roleMenuList = roleMenuDao.selectByRoleIdAndMeneId(roleId, menuId);
        for (RoleMenu roleMenu : roleMenuList) {
            Integer buttonId = roleMenu.getButtonId();
            if (buttonId == 1) {
                roleVO.setUserIsSelect(true);
            } else if (buttonId == 2) {
                roleVO.setUserIsAdd(true);
            } else if (buttonId == 3) {
                roleVO.setUserIsUpdate(true);
            } else {
                roleVO.setUserIsDelete(true);
            }
        }
        //菜单管理
        menuId = 10002;
        roleMenuList = roleMenuDao.selectByRoleIdAndMeneId(roleId, menuId);
        for (RoleMenu roleMenu : roleMenuList) {
            Integer buttonId = roleMenu.getButtonId();
            if (buttonId == 1) {
                roleVO.setMenuIsSelect(true);
            } else if (buttonId == 2) {
                roleVO.setMenuIsAdd(true);
            } else if (buttonId == 3) {
                roleVO.setMenuIsUpdate(true);
            } else {
                roleVO.setMenuIsDelete(true);
            }
        }
        //角色管理
        menuId = 10003;
        roleMenuList = roleMenuDao.selectByRoleIdAndMeneId(roleId, menuId);
        for (RoleMenu roleMenu : roleMenuList) {
            Integer buttonId = roleMenu.getButtonId();
            if (buttonId == 1) {
                roleVO.setRoleIsSelect(true);
            } else if (buttonId == 2) {
                roleVO.setRoleIsAdd(true);
            } else if (buttonId == 3) {
                roleVO.setRoleIsUpdate(true);
            } else {
                roleVO.setRoleIsDelete(true);
            }
        }
        //黑名单管理
        menuId = 20001;
        roleMenuList = roleMenuDao.selectByRoleIdAndMeneId(roleId, menuId);
        for (RoleMenu roleMenu : roleMenuList) {
            Integer buttonId = roleMenu.getButtonId();

            if (buttonId == 1) {
                roleVO.setBlackListIsSelect(true);
            } else if (buttonId == 2) {
                roleVO.setBlackListIsAdd(true);
            } else if (buttonId == 3) {
                roleVO.setBlackListIsUpdate(true);
            } else {
                roleVO.setBlackListIsDelete(true);
            }
        }
        //白名单管理
        menuId = 20002;
        roleMenuList = roleMenuDao.selectByRoleIdAndMeneId(roleId, menuId);
        for (RoleMenu roleMenu : roleMenuList) {
            Integer buttonId = roleMenu.getButtonId();
            if (buttonId == 1) {
                roleVO.setWhiteListIsSelect(true);
            } else if (buttonId == 2) {
                roleVO.setWhiteListIsAdd(true);
            } else if (buttonId == 3) {
                roleVO.setWhiteListIsUpdate(true);
            } else {
                roleVO.setWhiteListIsDelete(true);
            }
        }
        return roleVO;
    }

    @RequestMapping("/saveRoleMenu")
    @ResponseBody
    public ModelAndView saveRoleMenu(ModelAndView model, HttpServletRequest request) {
        List<RoleMenu> list = new ArrayList<>();
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("userIsSelect")), roleId, 10001, 1);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("userIsAdd")), roleId, 10001, 2);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("userIsUpdate")), roleId, 10001, 3);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("userIsDelete")), roleId, 10001, 4);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("menuIsSelect")), roleId, 10002, 1);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("menuIsAdd")), roleId, 10002, 2);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("menuIsUpdate")), roleId, 10002, 3);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("menuIsDelete")), roleId, 10002, 4);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("roleIsSelect")), roleId, 10003, 1);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("roleIsAdd")), roleId, 10003, 2);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("roleIsUpdate")), roleId, 10003, 3);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("roleIsDelete")), roleId, 10003, 4);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("whiteListIsSelect")), roleId, 20001, 1);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("whiteListIsAdd")), roleId, 20001, 2);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("whiteListIsUpdate")), roleId, 20001, 3);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("whiteListIsDelete")), roleId, 20001, 4);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("blackListIsSelect")), roleId, 20002, 1);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("blackListIsAdd")), roleId, 20002, 2);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("blackListIsUpdate")), roleId, 20002, 3);
        list = addRoleMenuList(list, Boolean.valueOf(request.getParameter("blackListIsDelete")), roleId, 20002, 4);
        //先删除角色所有的记录
        int delNum = roleMenuDao.deleteByRoleId(roleId);
        log.info("saveRoleMenu-删除{}条权限历史记录！", delNum);
        int batchNum = roleMenuDao.batchInsertRoleMenu(list);
        log.info("saveRoleMenu-新增{}条权限记录！", batchNum);
        RoleDetailVO vo = spellRoleDetailVO(roleId);
        model.addObject("roleDetailVO", vo);
        model.setViewName("system/roleDetail");
        return model;
    }

    private List<RoleMenu> addRoleMenuList(List<RoleMenu> list, Boolean isTrue, Integer roleId, Integer menuId, Integer buttonId) {
        if (isTrue) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setButtonId(buttonId);
            list.add(roleMenu);
        }
        return list;
    }
}
