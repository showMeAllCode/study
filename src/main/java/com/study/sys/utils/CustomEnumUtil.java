package com.study.sys.utils;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020/4/23 15:59:02
 */
public class CustomEnumUtil<E extends Enum<?> & IEnum & EnumUtil> {

    private Class<E> clazz;

    CustomEnumUtil(Class<E> clazz){
        this.clazz = clazz;
    }

    /**
     * 枚举转换，通过value返回desc值
     * @param value
     * @return
     */
    public Serializable enumValueGetDesc(Object value) {
        E result = EnumTransformUtil.valueOf(clazz, value);
        if (result == null) {
            result = EnumTransformUtil.valueOf(clazz, value);
        }
        return result.getDesc();
    }

    /**
     * 枚举转换，通过desc值返回枚举
     * @param clazz
     * @param desc
     * @return
     */
    public E descGetEnum(Class<E> clazz, Object desc) {
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

}
