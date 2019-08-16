package com.yh.controller;

import com.yh.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yh create on 2019/7/3
 */
@Slf4j
@RequestMapping("test")
public class TestController {

    @RequestMapping("/testHttpGet")
    @ResponseBody
    public String testHttpGet(){
        log.info("testHttpGet--测试");
        return "123";
    }

    @RequestMapping("testHttpPost")
    @ResponseBody
    public String testHttpPost(@RequestBody User user){
        return user.toString();
    }


}
