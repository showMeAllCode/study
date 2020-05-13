package com.study.sys.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/5/9 16:56:57
 */
public class DtoFieldCacheUtil implements DtoFieldCacheHelper {

    /**
     * 添加缓存
     * @param className
     * @param clazz
     */
    private void setAllFieldCache(String className, Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldNameList = new ArrayList<>();
        Map<String, Field> fieldMap = new HashMap<>();
        Map<String, Class<?>> fieldTypesMap = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            fieldNameList.add(fieldName);
            fieldMap.put(fieldName, field);
            fieldTypesMap.put(fieldName, field.getType());
        }
        DtoFieldCache.dtoFieldNameCache.put(className, fieldNameList);
        DtoFieldCache.dtoFieldCache.put(className, fieldMap);
        DtoFieldCache.dtoFieldTypeCache.put(className, fieldTypesMap);
    }

    /**
     * 检查是否已存在缓存，不存在则添加
     * @param className
     * @param clazz
     * @return
     */
    @Override
    public void checkAndAddAllIfNecessary(String className, Class<?> clazz) {
        if((DtoFieldCache.dtoFieldNameCache.get(className) == null)){
            setAllFieldCache(className, clazz);
        }
    }

    /**
     * 从缓存获取属性名列表
     * @param className
     * @return
     */
    @Override
    public List<String> getFieldNames(String className) {
        return DtoFieldCache.dtoFieldNameCache.get(className);
    }

    /**
     * 从缓存获取属性列表
     * @param className
     * @return
     */
    @Override
    public Map<String, Field> getFields(String className){
        return DtoFieldCache.dtoFieldCache.get(className);
    }

    /**
     * 从缓存获取属性类型列表
     * @param className
     * @return
     */
    @Override
    public Map<String, Class<?>> getFieldTypes(String className){
        return DtoFieldCache.dtoFieldTypeCache.get(className);
    }
}
