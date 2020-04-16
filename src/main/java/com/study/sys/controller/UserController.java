package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.User;
import com.study.sys.service.UserService;
/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-04-10
 */
@Api(value = "UserController控制类", tags = "用户信息的控制类")
@Controller
@RequestMapping("user")
public class UserController extends BaseController<User, UserService> {

}
