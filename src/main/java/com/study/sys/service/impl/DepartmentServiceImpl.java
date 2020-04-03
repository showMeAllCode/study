package com.study.sys.service.impl;

import com.study.sys.entity.Department;
import com.study.sys.mapper.DepartmentMapper;
import com.study.sys.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门信息 服务实现类
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
