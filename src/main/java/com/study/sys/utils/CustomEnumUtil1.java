package com.study.sys.utils;

/**
 * @author wxl
 * @date 2020/4/24 11:33:22
 */
public class CustomEnumUtil1 {
    /**
     * 枚举转换，通过desc值返回枚举
     *
     * @param desc
     * @return
     */
    public static <E extends Enum<?> & EnumUtil> E descOf(Class<E> clazz, Object desc) {
        E[] es = clazz.getEnumConstants();
        for (int i = 0; i < es.length; ++i) {
            E e = es[i];
            if (e.getDesc() == desc) {
                return e;
            }

            if (desc instanceof Number) {
                if (e.getDesc() instanceof Number && ((Number) desc).doubleValue() == ((Number) e.getDesc()).doubleValue()) {
                    return e;
                }
            } else if (String.valueOf(desc).equals(String.valueOf(e.getDesc()))) {
                return e;
            }
        }

        return null;
    }

    /**
     * 通过枚举名获取枚举
     *
     * @param value
     * @return
     */
    public static <E extends Enum<?> & EnumUtil> E nameOf(Class<E> clazz, Object value) {
        E[] es = clazz.getEnumConstants();
        for (int i = 0; i < es.length; ++i) {
            if (es[i].name().equals(value)) {
                return es[i];
            }
        }
        return null;
    }

    /**
     * 通过value获取枚举
     *
     * @param value
     * @return
     */
    public static <E extends Enum<?> & EnumUtil> E valueOf(Class<E> clazz, Object value) {
        E[] es = clazz.getEnumConstants();
        for (int i = 0; i < es.length; ++i) {
            E e = es[i];
            if (e.getValue() == value) {
                return e;
            }

            if (value instanceof Number) {
                if (e.getValue() instanceof Number && ((Number) value).doubleValue() == ((Number) e.getValue()).doubleValue()) {
                    return e;
                }
            } else if (String.valueOf(value).equals(String.valueOf(e.getValue()))) {
                return e;
            }
        }

        return null;
    }

    /**
     * 通过枚举名或枚举值value或desc获取枚举
     *
     * @param obj
     * @return
     */
    public static <E extends Enum<?> & EnumUtil> E getEnum(Class<E> clazz, Object obj) {
        E result = nameOf(clazz, obj);
        if (result == null) {
            result = valueOf(clazz, obj);
            if (result == null) {
                result = descOf(clazz, obj);
            }
        }
        return result;
    }

    /**
     * 枚举转换，通过枚举名或value返回desc值
     *
     * @param value
     * @return
     */
    public static <E extends Enum<?> & EnumUtil> Object getValue(Class<E> clazz, Object value) {
        E result = nameOf(clazz, value);
        if (result == null) {
            result = descOf(clazz, value);
        }
        return result.getValue();
    }

    /**
     * 枚举转换，通过枚举名或desc返回value值
     *
     * @param value
     * @return
     */
    public static <E extends Enum<?> & EnumUtil> Object getDesc(Class<E> clazz, Object value) {
        E result = nameOf(clazz, value);
        if (result == null) {
            result = valueOf(clazz, value);
        }
        return result.getDesc();
    }
}
