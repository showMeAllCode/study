package com.study.sys.utils;

import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;

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
}
