package com.study.sys.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.sys.dto.PageQueryDto;
import com.study.sys.enums.AllowOperationsEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author wxl
 * @date 2020/3/30 15:09:06
 */
@Slf4j
public class BaseControllerOperationUtil<T, M extends IService> {

    protected Boolean allowAll = true;

    protected List<String> allowOperations = new ArrayList<>();

    private T entity;

    public T getEntity() {
        try {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
            entity = clazz.newInstance();
        } catch (IllegalAccessException e) {
            log.error("初始化实体类失败,失败原因：{}",e);
            throw new RuntimeException("初始化实体类失败");
        } catch (InstantiationException e) {
            log.error("初始化实体类失败,失败原因：{}",e);
            throw new RuntimeException("初始化实体类失败");
        }
        return entity;
    }

    public M getEntityService() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<M> clazz = (Class<M>) type.getActualTypeArguments()[1];
        return SpringUtil.getBean(clazz);
    }

    public IPage<T> doQueryByIds(String ids) {
        IPage<T> iPage = new Page<>();
        String[] newIds = ids.split(",");
        List<String> idList = new ArrayList<>();
        for (String id : newIds) {
            idList.add(id);
        }
        List<T> list = this.getEntityService().listByIds(idList);
        iPage.setRecords(list);
        iPage.setTotal(list.size());
        return iPage;
    }

    public IPage<T> newIPage(PageQueryDto dto) {
        IPage<T> iPage = new Page<>();
        iPage.setCurrent(dto.getPageNo());
        iPage.setSize(dto.getPageSize());
        return iPage;
    }

    public IPage doPageQuery(PageQueryDto dto) {
        IPage<T> iPage = newIPage(dto);
        iPage = this.getEntityService().page(iPage);
        return iPage;
    }

    public IPage doQueryMsgByParams(T entity, PageQueryDto dto, Boolean eq, HttpServletRequest request) throws IllegalAccessException {
        IPage<T> iPage = newIPage(dto);
        Field[] fields = entity.getClass().getDeclaredFields();
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getAnnotation(TableField.class) != null && field.getAnnotation(TableField.class).exist() == false){
                continue;
            }
            if (!"serialVersionUID".equals(field.getName()) && field.get(entity) != null) {
                if(field.getType() == LocalDateTime.class || field.getType() == LocalDate.class
                        || field.getType() == Date.class){
                    if (request.getParameter(field.getName() + "End") == null) {
                        addWrapperConditions(wrapper, field.getName(), field.get(entity), eq);
                    } else {
                        wrapper.and((Consumer<QueryWrapper<T>>) wrapper.gt(camelToUnderline(field.getName()), field.get(entity)).lt(camelToUnderline(field.getName()), request.getParameter(field.getName() + "End")));
                    }
                    continue;
                }
                addWrapperConditions(wrapper, field.getName(), field.get(entity), eq);
            }
        }
        wrapper.orderBy(true,false, "create_time");
        iPage = this.getEntityService().page(iPage, wrapper);
        return iPage;
    }

    public QueryWrapper<T> addWrapperConditions(QueryWrapper<T> wrapper, String name, Object value, Boolean eq) {
        wrapper = eq ? wrapper.eq(camelToUnderline(name), value) : wrapper.like(camelToUnderline(name), value);
        return wrapper;
    }

    /**
     * 驼峰转下划线
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 权限验证
     * @param allowOperationsEnum
     * @return
     */
    public Boolean isAllow(AllowOperationsEnum allowOperationsEnum){
        if (allowAll){
            return true;
        }
        for (String allowOperation : allowOperations) {
            if (allowOperationsEnum.getValue().equals(allowOperation)){
                return true;
            }
        }
        throw new LogicException("无权访问");
    }

}
