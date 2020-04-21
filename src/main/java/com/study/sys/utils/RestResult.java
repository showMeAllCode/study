package com.study.sys.utils;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.sys.dto.PageInfosDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName RestResult
 * @Description：前后端交互数据实体类
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2019/5/6 16:03
 **/
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
public class RestResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private Integer code = 200;
    /**
     * 成功标志
     */
    private Boolean success;

    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 分页数据信息
     */
    private IPage<T> pageInfos;

    /**
     * 响应时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public RestResult(){

    }

    public RestResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> RestResult<T> success() {
        return createResult(HttpStatus.HTTP_OK, true, "请求成功", null, null);
    }

    public static <T> RestResult<T> success(T data) {
        return createResult(HttpStatus.HTTP_OK, true, "请求成功", data, null);
    }

    public static <T> RestResult<T> success(IPage<T> pageInfos) {
        return createResult(HttpStatus.HTTP_OK, true, "请求成功", null, pageInfos);
    }

    public static <T> RestResult<T> success(T data, String message) {
        return createResult(HttpStatus.HTTP_OK, true, message, data, null);
    }

    public static <T> RestResult<T> failure() {
        return createResult(HttpStatus.HTTP_INTERNAL_ERROR, false, "请求失败", null, null);
    }

    public static <T> RestResult<T> failure(Integer code, String message) {
        return createResult(code, false, message, null, null);
    }

    public static <T> RestResult<T> failure(Integer code, String message, T data) {
        return createResult(code, false, message, data, null);
    }

    public static <T> RestResult<T> createResult(Integer code, Boolean success, String message, T data, IPage<T> pageInfos) {
        RestResult<T> result = new RestResult<>();
        result.setCode(code);
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        result.setPageInfos(pageInfos);
        return result;
    }

}
