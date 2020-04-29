package com.study.sys.utils.aop;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author wxl
 * @date 2020/4/29 17:11:35
 */
@Component
@EnableAspectJAutoProxy(proxyTargetClass = false,exposeProxy = true)
public class EnableAspect {
}
