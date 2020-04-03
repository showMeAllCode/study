package com.study.sys.service.impl;

import com.study.sys.entity.Employee;
import com.study.sys.mapper.EmployeeMapper;
import com.study.sys.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户的员工信息 服务实现类
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
