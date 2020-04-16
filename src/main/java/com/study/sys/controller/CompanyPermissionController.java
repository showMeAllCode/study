package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.CompanyPermission;
import com.study.sys.service.CompanyPermissionService;
/**
 * <p>
 * 公司权限 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-04-10
 */
@Api(value = "CompanyPermissionController控制类", tags = "公司权限的控制类")
@Controller
@RequestMapping("company-permission")
public class CompanyPermissionController extends BaseController<CompanyPermission, CompanyPermissionService> {

}
