package com.study.sys.service.impl;

import com.study.sys.entity.User;
import com.study.sys.mapper.UserMapper;
import com.study.sys.service.UserService;
import com.study.sys.utils.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author wxl
 * @since 2020-04-10
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
