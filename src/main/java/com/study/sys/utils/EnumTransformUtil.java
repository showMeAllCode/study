package com.study.sys.utils;

/**
 * @author wxl
 * @date 2020/4/23 10:32:55
 */
public class EnumTransformUtil {

    public static <E extends Enum<?>> E valueOf(Class<E> enumClass, Object value) {
        E[] es = enumClass.getEnumConstants();
        for (int i = 0; i < es.length; ++i) {
            if (es[i].name().equals(value)) {
                return es[i];
            }
        }
        return null;
    }
}
