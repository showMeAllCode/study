package com.study.sys.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.DataBinder;

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

    public static DataBinder dataBinder = new DataBinder(null);

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);


    /**
     * 数据转换
     * @param field
     * @param map
     * @return
     */
    public static Object dataFormattingValue(Field field, Map map) {
        Object value;
        try {
            Class<?> clazz = field.getType();
            if (clazz == LocalDateTime.class) {
                String dateTime = String.valueOf(map.get(field.getName()));
                if(dateTime.length() == YYYY_MM_DD.length()) {
                    dateTime = dateTime + " 00:00:00";
                }
                value = LocalDateTime.parse(dateTime, dateTimeFormatter);
            } else if (clazz == LocalDate.class) {
                value = LocalDate.parse(String.valueOf(map.get(field.getName())), dateFormatter);
            } else if (clazz == Date.class) {
                String dateTime = String.valueOf(map.get(field.getName()));
                if(dateTime.length() == YYYY_MM_DD.length()) {
                    dateTime = dateTime + " 00:00:00";
                }
                value = dateFormat.parse(dateTime);
            } else if (clazz.isEnum()) {
                value = EnumHelperUtil.IEnumUtil(clazz).getEnum(map.get(field.getName()));
            } else {
                value = dataBinder.convertIfNecessary(map.get(field.getName()), clazz);
            }
        } catch (Exception e) {
            log.error("数据转换绑定失败，原因：{}", e);
            throw new LogicException("数据转换绑定失败");
        }
        return value;
    }

}
