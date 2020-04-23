package com.study.sys.utils;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020/4/7 13:58:56
 */
public interface EnumUtil {

    /**
     * 获取枚举value
     * @return
     */
    Serializable getValue();

    /**
     * 获取枚举desc
     * @return
     */
    Serializable getDesc();
}
