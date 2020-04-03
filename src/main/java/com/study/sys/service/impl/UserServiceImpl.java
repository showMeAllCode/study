package com.study.sys.service.impl;

import com.study.sys.entity.User;
import com.study.sys.mapper.UserMapper;
import com.study.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
