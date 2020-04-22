package com.mapscience.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.sys.utils.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/3/31 14:38:19
 */
@Slf4j
public class ParameterBindingUtil {

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String JSON_TYPE = "application/json";

    public static Map<String, List<String>> methodCache = new HashMap<>();

    /**
     * 数据绑定
     *
     * @param request
     * @param dto
     * @param <T>
     * @return
     */
    public static <T> T parameterBinding(HttpServletRequest request, T dto) {
        ServletRequestDataBinder binder = new ServletRequestDataBinder(dto, dto.getClass().getName());
        binder.bind(request);
        return dto;
    }

    /**
     * body数据绑定
     *
     * @param request
     * @param dto
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T requestBodyBinding(HttpServletRequest request, T dto) throws IOException {
        if (request.getHeader(CONTENT_TYPE) != null && request.getHeader(CONTENT_TYPE).contains(JSON_TYPE)) {
            String body = getBodyJson(request);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(body, Map.class);
            map.forEach((k,v) -> {
                try {
                    Field field = dto.getClass().getDeclaredField(String.valueOf(k));
                    field.setAccessible(true);
                    field.set(dto,v);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    log.info(Thread.currentThread().getStackTrace()[2].getMethodName() + "无法绑定的参数：" + k);
                }
            });
        }
        return dto;
    }

    /**
     * body单参数处理
     * @param name
     * @param request
     * @return
     * @throws IOException
     * @throws NoSuchMethodException
     */
    public static Object requestBodyBinding(String name, HttpServletRequest request) throws IOException, NoSuchMethodException {
        if (request.getHeader(CONTENT_TYPE) != null && request.getHeader(CONTENT_TYPE).contains(JSON_TYPE)) {
            String body = getBodyJson(request);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(body, Map.class);
            return map.get(name);
        }
        return null;
    }

    public static String getBodyJson(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtil.read(reader);
        return body;
    }
}
