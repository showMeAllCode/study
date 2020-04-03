package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.CompanyDepartment;
import com.study.sys.service.CompanyDepartmentService;
/**
 * <p>
 * 公司隶属部门 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Api(value = "CompanyDepartmentController控制类", tags = "公司隶属部门的控制类")
@Controller
@RequestMapping("/sys/company-department")
public class CompanyDepartmentController extends BaseController<CompanyDepartment, CompanyDepartmentService> {

}
