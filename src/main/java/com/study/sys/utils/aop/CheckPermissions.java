package com.study.sys.utils.aop;

import java.lang.annotation.*;

/**
 * @author wxl
 * @date 2020/4/29 18:38:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface CheckPermissions {
}
