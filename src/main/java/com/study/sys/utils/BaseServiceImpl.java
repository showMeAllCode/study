package com.study.sys.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author wxl
 * @date 2020/4/10 08:37:52
 */
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveMsg(T entity) {
        Object obj = beforeSave(entity);
        boolean flag = SqlHelper.retBool(baseMapper.insert(entity));
        isThrowNewException(flag, "保存失败，请检查传入信息");
        afterSave(entity, obj);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateJson(T entity) throws IllegalAccessException {
        Object obj = beforeJson(entity);
        Field[] fields = entity.getClass().getDeclaredFields();
        StringBuilder sqlSet = new StringBuilder();
        String id = null;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(TableField.class) != null && field.getAnnotation(TableField.class).exist() == false) {
                continue;
            }
            if ("id".equals(field.getName())) {
                id = field.get(entity).toString();
                continue;
            }
            if (!"serialVersionUID".equals(field.getName()) && field.get(entity) != null) {
                sqlSet = sqlSet.append(BaseControllerOperationUtil.camelToUnderline(field.getName())).append("=")
                        .append("'").append(field.get(entity)).append("'").append(",");
            }
        }
        Assert.notNull(id, "入参id不能为空");
        Assert.notNull(sqlSet, "没有找到需要修改的属性");
        String newSqlSet = sqlSet.substring(0, sqlSet.length() - 1) + "id='" + id + "'";
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.setSql(newSqlSet);
        Integer num = baseMapper.update(entity, wrapper);
        if (num < 1) {
            throw new RuntimeException("修改失败，请检查传入信息");
        }
        afterJson(entity, obj);
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMsg(T entity) {
        Object obj = beforeUpdate(entity);
        boolean flag = SqlHelper.retBool(baseMapper.updateById(entity));
        isThrowNewException(flag, "修改失败，请检查传入信息");
        afterUpdate(entity, obj);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMsg(Serializable id) {
        Object obj = beforeDelete(id);
        boolean flag = SqlHelper.retBool(baseMapper.deleteById(id));
        isThrowNewException(flag, "删除失败，请检查传入信息");
        afterDelete(id, obj);
        return flag;
    }

    public void isThrowNewException(boolean flag, String msg) {
        if (!flag) {
            throw new RuntimeException(msg);
        }
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

    public Object beforeJson(T entity) {
        return null;
    }

    public void afterJson(T entity, Object obj) {
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
