package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.Permission;
import com.study.sys.service.PermissionService;
/**
 * <p>
 * 功能权限 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Api(value = "PermissionController控制类", tags = "功能权限的控制类")
@Controller
@RequestMapping("/sys/permission")
public class PermissionController extends BaseController<Permission, PermissionService> {

}
