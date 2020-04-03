package com.study.sys.service.impl;

import com.study.sys.entity.UserCompany;
import com.study.sys.mapper.UserCompanyMapper;
import com.study.sys.service.UserCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户所属公司及部门信息 服务实现类
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Service
public class UserCompanyServiceImpl extends ServiceImpl<UserCompanyMapper, UserCompany> implements UserCompanyService {

}
