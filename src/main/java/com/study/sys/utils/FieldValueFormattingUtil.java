package com.mapscience.utils;

import com.study.sys.utils.EnumHelperUtil;
import com.study.sys.utils.LogicException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
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
            value = map.get(field.getName());
            if (clazz == LocalDateTime.class) {
                String dateTime = String.valueOf(map.get(field.getName()));
                if(dateTime.length() == 10) {
                    dateTime = dateTime + " 00:00:00";
                }
                value = LocalDateTime.parse(dateTime, dateTimeFormatter);
            } else if (clazz == LocalDate.class) {
                value = LocalDate.parse(String.valueOf(map.get(field.getName())), dateFormatter);
            } else if (clazz == Date.class) {
                value = dateFormat.parse(String.valueOf(map.get(field.getName())));
            } else if (clazz.isEnum()) {
                value = EnumHelperUtil.customEnumUtil(clazz).getEnum(map.get(field.getName()));
            } else if(clazz == BigDecimal.class){
                value = BigDecimal.valueOf(Double.parseDouble(map.get(field.getName()).toString()));
            }
        } catch (Exception e) {
            log.error("数据转换绑定失败，原因：{}", e);
            throw new LogicException("数据转换绑定失败");
        }
        return value;
    }

}
