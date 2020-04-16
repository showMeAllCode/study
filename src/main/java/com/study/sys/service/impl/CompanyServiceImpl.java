package com.study.sys.service.impl;

import com.study.sys.entity.Company;
import com.study.sys.mapper.CompanyMapper;
import com.study.sys.service.CompanyService;
import com.study.sys.utils.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司信息 服务实现类
 * </p>
 *
 * @author wxl
 * @since 2020-04-10
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<CompanyMapper, Company> implements CompanyService {

}
