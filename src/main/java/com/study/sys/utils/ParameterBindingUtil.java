package com.study.sys.utils;

import com.alibaba.dubbo.common.utils.IOUtils;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author wxl
 * @date 2020/3/31 14:38:19
 */
public class ParameterBindingUtil  {

    /**
     * 数据绑定
     * @param request
     * @param dto
     * @param <T>
     * @return
     */
    public static  <T> T parameterBinding(HttpServletRequest request, T dto) {
        ServletRequestDataBinder binder = new ServletRequestDataBinder(dto, dto.getClass().getName());
        binder.bind(request);
        return dto;
    }

    public static  <T> T requestBodyBinding(HttpServletRequest request, T dto) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.read(reader);
        return dto;
    }
}
