package com.study.sys.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/5/9 16:54:57
 */
public interface DtoFieldCacheHelper {

    /**
     * 检查是否已存在缓存，不存在则添加
     * @param className
     * @param clazz
     * @return
     */
    void checkAndAddAllIfNecessary(String className, Class<?> clazz);

    /**
     * 从缓存获取属性名列表
     * @param className
     * @return
     */
    List<String> getFieldNames(String className);

    /**
     * 从缓存获取属性列表
     * @param className
     * @return
     */
    Map<String, Field> getFields(String className);

    /**
     * 从缓存获取属性类型列表
     * @param className
     * @return
     */
    Map<String, Class<?>> getFieldTypes(String className);
}
