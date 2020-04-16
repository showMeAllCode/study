package com.study.sys.utils;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020/4/10 08:30:04
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 根据实体类保存信息
     * @param entity
     * @return
     */
    boolean saveMsg(T entity);

    /**
     * 根据id修改信息
     * @param entity
     * @return
     */
    boolean updateMsg(T entity);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    boolean deleteMsg(Serializable id);
}
