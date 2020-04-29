package com.study.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.study.sys.utils.EnumUtil;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020/4/26 09:30:29
 */
public enum  CompanyStatusEnum implements IEnum,EnumUtil {
    YES("yes","续存"),
    NO("no","破产");

    private String value;

    private String desc;


    CompanyStatusEnum(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @Override
    public Serializable getDesc() {
        return this.desc;
    }
}
