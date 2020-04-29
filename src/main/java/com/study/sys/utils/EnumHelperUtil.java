package com.study.sys.utils;

/**
 * @author wxl
 * @date 2020/4/23 16:47:15
 */
public class EnumHelperUtil {

    public static CustomEnumUtil customEnumUtil(Enum obj){
        return new CustomEnumUtil(obj.getClass());
    }

    public static CustomEnumUtil customEnumUtil(Class<?> obj){
        return new CustomEnumUtil(obj);
    }
}
