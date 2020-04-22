package com.study.sys.utils;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.core.toolkit.EnumUtils;
import org.apache.ibatis.type.EnumTypeHandler;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/4/22 17:56:02
 */
public class FieldValueFormattingUtil<E extends Enum<?> & IEnum> extends EnumTypeHandler {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public FieldValueFormattingUtil(Class type) {
        super(type);
    }

    public <T> Object dataFormattingValue(Field field, T entity, Object value, Map map) throws IllegalAccessException {
        if(field.getType() == LocalDateTime.class){
            value = dateTimeFormatter.format((LocalDateTime)field.get(entity));
        }else if (field.getType() == LocalDate.class){
            value = dateTimeFormatter.format((LocalDate)field.get(entity));
        }else if (field.getType() == Date.class){
            value = dateFormat.format((Date)field.get(entity));
        }else if (field.getType().isEnum()){
            Class<E> clazz = (Class<E>)field.getType();
            value = EnumUtils.valueOf(clazz, map.get(field.get(entity)));
        } else {
            value = String.valueOf(field.get(entity));
        }
        return value;
    }

}
