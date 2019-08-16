package com.yh.util.intercepor;

import com.alibaba.fastjson.JSON;
import com.yh.dao.RoleMenuDao;
import com.yh.pojo.RoleMenu;
import com.yh.pojo.vo.UserRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yh create on 2019/8/14
 * 自定义拦截器，加入session角色权限信息
 */
@Slf4j
public class RoleInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * 拦截器执行之前运行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 拦截器执行之后运行； 在执行完controller之后，ModelAndView渲染之前开始运行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        UserRoleVO userRoleVO = (UserRoleVO) session.getAttribute("userRoleVO");
        String username = userRoleVO.getUserName();
        Integer roleId = userRoleVO.getRoleId();
        List<RoleMenu> roleMenuList = roleMenuDao.selectByRoleIdAndMeneId(roleId, null);
        session.setAttribute("roleMenuListVO", roleMenuList);
    }

    /**
     * 清理资源，在preHandle为true时执行； DispatcherServlet渲染了视图后执行
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }
}
