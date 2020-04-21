package com.study.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wxl
 * @date 2020/3/30 13:58:19
 */
@Api(value = "首页控制类", tags = "首页控制类")
@Controller
public class IndexController {

    @ApiOperation(value = "登录")
    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @ApiOperation(value = "首页")
    @RequestMapping("/")
    public String index(){
        return "test";
    }
}
