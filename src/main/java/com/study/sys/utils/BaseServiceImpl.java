package com.study.sys.utils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020/4/10 08:37:52
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveMsg(T entity) {
        Object obj = beforeSave(entity);
        boolean flag = SqlHelper.retBool(this.getBaseMapper().insert(entity));
        afterSave(entity, obj);
        return flag;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMsg(T entity) {
        Object obj = beforeUpdate(entity);
        boolean flag = SqlHelper.retBool(this.getBaseMapper().updateById(entity));
        afterUpdate(entity, obj);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMsg(Serializable id) {
        Object obj = beforeDelete(id);
        boolean flag = SqlHelper.retBool(this.getBaseMapper().deleteById(id));
        afterDelete(id, obj);
        return flag;
    }

    /**
     * 对before和after方法复写可以实现在对表进行操作前后进行其他操作
     *
     * @param entity
     * @return
     */
    public Object beforeSave(T entity) {
        return null;
    }

    public void afterSave(T entity, Object obj) {
    }

    public Object beforeUpdate(T entity) {
        return null;
    }

    public void afterUpdate(T entity, Object obj) {
    }

    public Object beforeDelete(Serializable id) {
        return null;
    }

    public void afterDelete(Serializable id, Object obj) {
    }
}
