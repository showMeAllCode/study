package com.study.sys.utils;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020/4/23 15:59:02
 */
public class CustomEnumUtil<E extends Enum<?> & EnumUtil> {

    private Class<E> clazz;

    CustomEnumUtil(Class<E> clazz){
        this.clazz = clazz;
    }

    /**
     * 枚举转换，通过desc值返回枚举
     * @param clazz
     * @param desc
     * @return
     */
    public E descOf(Class<E> clazz, Object desc) {
        E[] es = (E[])clazz.getEnumConstants();
        for(int i = 0; i < es.length; ++i) {
            E e = es[i];
            if (((EnumUtil)e).getDesc() == desc) {
                return e;
            }

            if (desc instanceof Number) {
                if (((EnumUtil)e).getDesc() instanceof Number && ((Number)desc).doubleValue() == ((Number)((EnumUtil)e).getDesc()).doubleValue()) {
                    return e;
                }
            } else if (String.valueOf(desc).equals(String.valueOf(((EnumUtil)e).getDesc()))) {
                return e;
            }
        }

        return null;
    }

    /**
     * 通过枚举名获取枚举
     * @param enumClass
     * @param value
     * @return
     */
    public E nameOf(Class<E> enumClass, Object value) {
        E[] es = (E[]) enumClass.getEnumConstants();
        for (int i = 0; i < es.length; ++i) {
            if (es[i].name().equals(value)) {
                return es[i];
            }
        }
        return null;
    }

    /**
     * 通过value获取枚举
     * @param enumClass
     * @param value
     * @return
     */
    public E valueOf(Class<E> enumClass, Object value) {
        E[] es = (E[])enumClass.getEnumConstants();
        for(int i = 0; i < es.length; ++i) {
            E e = es[i];
            if (((IEnum)e).getValue() == value) {
                return e;
            }

            if (value instanceof Number) {
                if (((IEnum)e).getValue() instanceof Number && ((Number)value).doubleValue() == ((Number)((IEnum)e).getValue()).doubleValue()) {
                    return e;
                }
            } else if (String.valueOf(value).equals(String.valueOf(((IEnum)e).getValue()))) {
                return e;
            }
        }

        return null;
    }

    /**
     * 通过枚举名或枚举值value或desc获取枚举
     * @param obj
     * @return
     */
    public E getEnum(Object obj) {
        E result = nameOf(clazz, obj);
        if (result == null) {
            result = valueOf(clazz, obj);
            if(result == null){
                result = descOf(clazz, obj);
            }
        }
        return result;
    }

    /**
     * 枚举转换，通过枚举名或value返回desc值
     * @param value
     * @return
     */
    public Serializable getValue(Object value) {
        E result = nameOf(clazz, value);
        if (result == null) {
            result = descOf(clazz, value);
        }
        return result.getValue();
    }

    /**
     * 枚举转换，通过枚举名或desc返回value值
     * @param value
     * @return
     */
    public Serializable getDesc(Object value) {
        E result = nameOf(clazz, value);
        if (result == null) {
            result = valueOf(clazz, value);
        }
        return result.getDesc();
    }
}
