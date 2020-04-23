package com.study.sys.utils;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author wxl
 * @date 2020/4/23 10:32:55
 */
public class EnumTransformUtil {

    public static <E extends Enum<?> & IEnum> E nameOf(Class<E> enumClass, Object value) {
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
     * @param <E>
     * @return
     */
    public static <E extends Enum<?> & IEnum> E valueOf(Class<E> enumClass, Object value) {
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
}
