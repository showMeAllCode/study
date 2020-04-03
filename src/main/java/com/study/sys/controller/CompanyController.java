package com.study.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.study.sys.utils.BaseController;
import io.swagger.annotations.Api;
import com.study.sys.entity.Company;
import com.study.sys.service.CompanyService;
/**
 * <p>
 * 公司信息 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Api(value = "CompanyController控制类", tags = "公司信息的控制类")
@Controller
@RequestMapping("/sys/company")
public class CompanyController extends BaseController<Company, CompanyService> {

}
