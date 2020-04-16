package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.DepPermission;
import com.study.sys.service.DepPermissionService;
/**
 * <p>
 * 部门权限 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-04-10
 */
@Api(value = "DepPermissionController控制类", tags = "部门权限的控制类")
@Controller
@RequestMapping("dep-permission")
public class DepPermissionController extends BaseController<DepPermission, DepPermissionService> {

}
