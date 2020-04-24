package com.study.sys.utils;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class FieldValueFormattingUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static <T> Object dataFormattingValue(Field field, T entity, Object value, Map map) {
        try {
            Class<?> clazz = field.getType();
            if (clazz == LocalDateTime.class) {
                value = LocalDateTime.parse(String.valueOf(map.get(field.getName())), dateTimeFormatter);
            } else if (clazz == LocalDate.class) {
                value = LocalDate.parse(String.valueOf(map.get(field.getName())), dateFormatter);
            } else if (clazz == Date.class) {
                value = dateFormat.parse(String.valueOf(map.get(field.getName())));
            } else if (clazz.isEnum()) {
                value = EnumHelperUtil.customEnumUtil(clazz).getEnum(map.get(field.getName()));
            } else {
                value = String.valueOf(map.get(field.getName()));
            }
        } catch (Exception e) {
            log.error("数据转换绑定失败，原因：{}", e);
            throw new LogicException("数据转换绑定失败");
        }
        return value;
    }

}
