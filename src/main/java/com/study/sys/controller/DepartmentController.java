package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.Department;
import com.study.sys.service.DepartmentService;
/**
 * <p>
 * 部门信息 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Api(value = "DepartmentController控制类", tags = "部门信息的控制类")
@Controller
@RequestMapping("/sys/department")
public class DepartmentController extends BaseController<Department, DepartmentService> {

}
