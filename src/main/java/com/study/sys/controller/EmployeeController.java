package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.Employee;
import com.study.sys.service.EmployeeService;
/**
 * <p>
 * 用户的员工信息 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Api(value = "EmployeeController控制类", tags = "用户的员工信息的控制类")
@Controller
@RequestMapping("/sys/employee")
public class EmployeeController extends BaseController<Employee, EmployeeService> {

}
