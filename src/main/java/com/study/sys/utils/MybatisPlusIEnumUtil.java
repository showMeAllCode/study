package com.study.sys.utils;

import com.baomidou.mybatisplus.core.enums.IEnum;
import org.apache.ibatis.type.EnumTypeHandler;

/**
 * @author wxl
 * @date 2020/4/23 08:45:45
 */
public class MybatisPlusIEnumUtil<E extends Enum<?> & IEnum> extends EnumTypeHandler {

    private Class<E> clazz;

    public MybatisPlusIEnumUtil(Class<E> type) {
        super(type);
        this.clazz = type;
    }

    public E getEnum(Object obj) {
        E result = EnumTransformUtil.valueOf(clazz, obj);
        if (result == null) {
            result = valueOf(obj);
        }
        return result;
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
}
