package com.study.sys.utils;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.core.toolkit.EnumUtils;
import org.apache.ibatis.type.EnumTypeHandler;

/**
 * @author wxl
 * @date 2020/4/23 08:45:45
 */
public class EnumBindingUtil<E extends Enum<?> & IEnum> extends EnumTypeHandler {

    private Class<E> clazz;

    public EnumBindingUtil(Class<E> type) {
        super(type);
        this.clazz = type;
    }

    public E enumBinding(Object obj) {
        E result = EnumTransformUtil.nameOf(clazz, obj);
        if (result == null) {
            result = EnumTransformUtil.valueOf(clazz, obj);
        }
        return result;
    }
}
