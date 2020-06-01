package com.study.sys.utils;

/**
 * @author wxl
 * @date 2020/4/23 15:59:02
 */
public class CustomEnumUtil<E extends Enum<?> & EnumUtil> {

    private Class<E> clazz;

    CustomEnumUtil(Class<E> clazz) {
        this.clazz = clazz;
    }

    /**
     * 枚举转换，通过desc值返回枚举
     *
     * @param desc
     * @return
     */
    public E descOf(Object desc) {
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
    public E nameOf(Object value) {
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
    public E valueOf(Object value) {
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
    public E getEnum(Object obj) {
        E result = nameOf(obj);
        if (result == null) {
            result = valueOf(obj);
            if (result == null) {
                result = descOf(obj);
            }
        }
        if(result == null){
            throw new LogicException("不存在的枚举");
        }
        return result;
    }

    /**
     * 枚举转换，通过枚举名或desc返回value值
     *
     * @param value
     * @return
     */
    public Object getValue(Object value) {
        E result = nameOf(value);
        if (result == null) {
            result = descOf(value);
        }
        if(result == null){
            throw new LogicException("不存在的枚举");
        }
        return result.getValue();
    }

    /**
     * 枚举转换，通过枚举名或value返回desc值
     *
     * @param value
     * @return
     */
    public Object getDesc(Object value) {
        E result = nameOf(value);
        if (result == null) {
            result = valueOf(value);
        }
        if(result == null){
            throw new LogicException("不存在的枚举");
        }
        return result.getDesc();
    }
}
