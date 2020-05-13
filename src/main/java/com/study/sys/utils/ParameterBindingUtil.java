package com.study.sys.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/3/31 14:38:19
 */
@Slf4j
public class ParameterBindingUtil {

    public static DtoFieldCacheHelper dtoFieldCacheHelper = new DtoFieldCacheUtil();

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String JSON_TYPE = "application/json";

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
     */
    public static <T> T requestBodyBinding(HttpServletRequest request, T dto) {
        try {
            if (request.getHeader(CONTENT_TYPE) != null && request.getHeader(CONTENT_TYPE).contains(JSON_TYPE)) {
                Class clazz = dto.getClass();
                String className = clazz.getName();
                dtoFieldCacheHelper.checkAndAddAllIfNecessary(className, clazz);
                Map<String, Field> finalFieldMap = dtoFieldCacheHelper.getFields(className);
                String body = parseIfNecessary(request);
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(body, Map.class);
                map.forEach((k, v) -> {
                    Field field = finalFieldMap.get(k);
                    if (field != null) {
                        Object value = FieldValueFormattingUtil.dataFormattingValue(field, map);
                        try {
                            field.set(dto, value);
                        } catch (IllegalAccessException e) {
                            throw new LogicException("属性权限未开启");
                        }
                    } else {
                        log.warn(className + "." + Thread.currentThread().getStackTrace()[4].getMethodName() + "无法绑定的参数：" + k);
                    }
                });
            }
        } catch (LogicException e) {
            throw new LogicException("请确认参数类型");
        } catch (Exception e) {
            log.error("requestBodyBinding参数绑定失败，原因：{}", e);
            throw new LogicException("参数绑定失败");
        }
        return dto;
    }

    /**
     * body单参数处理
     *
     * @param name
     * @param request
     * @return
     * @throws IOException
     */
    public static Object requestBodyBinding(String name, HttpServletRequest request) throws IOException {
        if (request.getHeader(CONTENT_TYPE) != null && request.getHeader(CONTENT_TYPE).contains(JSON_TYPE)) {
            String body = parseIfNecessary(request);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(body, Map.class);
            return map.get(name);
        }
        return null;
    }

    public static String getBodyJson(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        String body = IOUtil.read(reader);
        return body;
    }

    private static String parseIfNecessary(HttpServletRequest request) throws IOException {
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request) {
            @Override
            public InputStream getBody() throws IOException {
                return request.getInputStream();
            }
        };
        MediaType contentType = inputMessage.getHeaders().getContentType();
        Charset charset = (contentType != null && contentType.getCharset() != null ?
                contentType.getCharset() : StandardCharsets.UTF_8);
        return StreamUtils.copyToString(inputMessage.getBody(), charset);
    }
}
