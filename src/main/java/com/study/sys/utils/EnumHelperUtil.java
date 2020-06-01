package com.study.sys.utils;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/4/23 16:47:15
 */
public class EnumHelperUtil {

    public static Map<String, CustomEnumUtil> customEnumUtilMapCache = new HashMap();

    private static Map<String, MybatisPlusIEnumUtil> mybatisPlusIEnumUtilMapCache = new HashMap();

    public static CustomEnumUtil customEnumUtil(Enum obj) {
        Assert.notNull(obj, "customEnumUtil入参为空");
        return getCustomEnumUtil(obj.getClass());
    }

    public static CustomEnumUtil customEnumUtil(Class<?> obj) {
        Assert.notNull(obj, "customEnumUtil入参为空");
        return getCustomEnumUtil(obj);
    }

    private static CustomEnumUtil getCustomEnumUtil(Class<?> obj) {
        CustomEnumUtil customEnumUtil = customEnumUtilMapCache.get(obj.getName());
        if (customEnumUtil == null) {
            customEnumUtil = new CustomEnumUtil(obj);
            customEnumUtilMapCache.put(obj.getName(), customEnumUtil);
        }
        return customEnumUtil;
    }

    public static MybatisPlusIEnumUtil IEnumUtil(Enum obj) {
        Assert.notNull(obj, "IEnumUtil入参为空");
        return getMybatisPlusIEnumUtil(obj.getClass());
    }

    public static MybatisPlusIEnumUtil IEnumUtil(Class<?> obj) {
        Assert.notNull(obj, "IEnumUtil入参为空");
        return getMybatisPlusIEnumUtil(obj);
    }

    private static MybatisPlusIEnumUtil getMybatisPlusIEnumUtil(Class<?> obj) {
        MybatisPlusIEnumUtil mybatisPlusIEnumUtil = mybatisPlusIEnumUtilMapCache.get(obj.getName());
        if (mybatisPlusIEnumUtil == null) {
            mybatisPlusIEnumUtil = new MybatisPlusIEnumUtil(obj);
            mybatisPlusIEnumUtilMapCache.put(obj.getName(), mybatisPlusIEnumUtil);
        }
        return mybatisPlusIEnumUtil;
    }
}
