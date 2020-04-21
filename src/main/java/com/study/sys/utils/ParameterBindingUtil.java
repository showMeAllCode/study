package com.study.sys.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/3/31 14:38:19
 */
public class ParameterBindingUtil {

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String JSON_TYPE = "application/json";

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
        if (request.getHeader(CONTENT_TYPE).contains(JSON_TYPE)) {
            String body = getBodyJson(request);
            ObjectMapper mapper = new ObjectMapper();
            dto = (T) mapper.readValue(body, dto.getClass());
        }
        return dto;
    }

    public static Map requestBodyBinding(HttpServletRequest request) throws IOException {
        Map map = null;
        if (request.getHeader(CONTENT_TYPE).contains(JSON_TYPE)) {
            String body = getBodyJson(request);
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(body,Map.class);
        }
        return map;
    }

    public static String getBodyJson(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtil.read(reader);
        return body;
    }
}
