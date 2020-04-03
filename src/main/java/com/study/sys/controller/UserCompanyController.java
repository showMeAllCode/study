package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.UserCompany;
import com.study.sys.service.UserCompanyService;
/**
 * <p>
 * 用户所属公司及部门信息 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Api(value = "UserCompanyController控制类", tags = "用户所属公司及部门信息的控制类")
@Controller
@RequestMapping("/sys/user-company")
public class UserCompanyController extends BaseController<UserCompany, UserCompanyService> {

}
