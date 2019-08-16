package com.yh.controller;

import com.alibaba.fastjson.JSON;
import com.yh.pojo.PageBean;
import com.yh.pojo.User;
import com.yh.service.UserService;
import com.yh.util.DateUtil;
import com.yh.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryById")
    public ModelAndView queryById(ModelAndView model, @RequestParam Integer id){
        log.info("queryById--入参id:{}", JSON.toJSONString(id));
        String name = userService.queryById(String.valueOf(id));
        log.info("queryById--查询结果name:{}", JSON.toJSONString(name));
        model.addObject("name", name);
        model.setViewName("user");
        return model;
    }

    /**
     * 用户注册，校验用户是否存在
     * */
    @RequestMapping(value = "/checkUsernameIsExist", method = RequestMethod.GET)
    @ResponseBody
    public String checkUserExist(@RequestParam String username){
        if(username!=null){
            username = username.trim();
        }
        log.info("checkUserExist--入参username:{}", username);
        User user = userService.queryByName(username);
        log.info("checkUserExist--返回结果{}", JSON.toJSONString(user));
        if(user!=null){
            return "exist";
        }else{
            return "not exist";
        }
    }

    /**
     * 用户登录loginCheckUser
     * */
    @RequestMapping(value = "/loginCheckUser",produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public String loginCheckUser(@RequestParam String username, @RequestParam String password){
        String result = null;
        log.info("loginCheckUser-用户登录，用户名：{}，密码：{}", username, password);
        User user = userService.queryByName(username);
        if(user==null){
            result = "用户名不存在！";
        }else if(!password.equals(user.getPassword())){
            result = "用户密码不正确！";
        }else{
            user.setLastLoginTime(DateUtil.getNow());
            int upNum = userService.updateByPrimaryKeySelective(user);
            if(upNum == 1){
                log.info("loginCheckUser-登录成功！入参,username:{}, 返回结果{}", username,  JSON.toJSONString(user));
            }
            result = "success";
        }
        return result;
    }

    /**
     * 用户注册
     * */
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String registerUser(@RequestBody User user){
        log.info("registerUser--入参{}", JSON.toJSONString(user));
        int insertResult = userService.insertUser(user);
        log.info("registerUser--insert结果{}条", JSON.toJSONString(insertResult));
        if(insertResult == 1){
            return "success";
        }else{
            return "";
        }
    }

    /**
     * 分页查询
     * */
    @RequestMapping("/queryUserPageList")
    public ModelAndView queryUserPageList(@RequestParam(value = "userName", required = false)String username,
                                          @RequestParam(value = "curPage")String currentPage){
        Integer curPage = Integer.valueOf(currentPage);
        Map<String, Object> params = new HashMap<>();
        User user = new User();
        if(StringUtils.hasText(username)){
            params.put("userName", username);
            user.setUsername(username);
        }
        PageUtil<User> pageUtil = new PageUtil<>();
        PageBean<User> pageBean = pageUtil.queryPageList(User.class, curPage, params );
        log.info("queryUserPageList-分页查询结果，pageBean：{}", JSON.toJSONString(pageBean));
        List<User> userList = pageBean.getPageList();
        ModelAndView model = new ModelAndView();
        model.addObject("userList", userList);
        model.addObject("pageBean", pageBean);
        model.addObject("userDto", user);
        model.setViewName("system/userList");
        return model;
    }

}
