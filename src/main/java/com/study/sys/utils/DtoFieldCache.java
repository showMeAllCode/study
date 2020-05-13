package com.study.sys.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/5/9 17:13:12
 */
public class DtoFieldCache {

    /**
     * 类名缓存
     */
    public static Map<String, List<String>> dtoFieldNameCache = new HashMap();

    /**
     * 类属性缓存
     */
    public static Map<String, Map<String, Field>> dtoFieldCache = new HashMap();

    /**
     * 类属性类型缓存
     */
    public static Map<String, Map<String, Class<?>>> dtoFieldTypeCache = new HashMap();
}
