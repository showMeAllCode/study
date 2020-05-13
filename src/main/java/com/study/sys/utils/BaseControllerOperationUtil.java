package com.study.sys.utils;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IEnum;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapscience.core.common.FinalObject;
import com.mapscience.core.exception.GenericException;
import com.mapscience.enu.AllowOperationsEnum;
import com.study.sys.dto.PageQueryDto;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author wxl
 * @date 2020/3/30 15:09:06
 */
@Slf4j
public class BaseControllerOperationUtil<T, M extends IService> {

    /**
     * BaseController所有方法统一权限控制
     */
    protected Boolean allowAll = true;

    /**
     * BaseController方法允许操作列表
     */
    protected List<String> allowOperations = new ArrayList<>();

    /**
     * Service类
     */
    private M entityService;

    /**
     * 实体类class
     */
    private Class<T> clazzT;

    /**
     * Service类class
     */
    private Class<M> clazzM;

    /**
     * 实体类简单类名
     */
    private String entitySimpleName;

    /**
     * 实体类工程路径类名
     */
    private String entityClassName;

    /**
     * 公司id属性名常量
     */
    private static final String COMPANY_ID = "companyId";

    /**
     * 公司id表字段名常量
     */
    private static final String COMPANY_ID_UNDERLINE = "company_id";

    /**
     * 表字段update_time常量
     */
    private static final String UPDATE_TIME = "update_time";

    /**
     * 实体类对应表，且在表后加"."
     */
    private String tableName;

    public T getEntity() {
        try {
            Class<T> clazz = getEntityClass();
            T entity = clazz.newInstance();
            return entity;
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("初始化实体类失败,失败原因：{}", e);
            throw new RuntimeException("初始化实体类失败");
        }
    }

    public M getEntityService() {
        if (entityService == null) {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<M> clazz = (Class<M>) type.getActualTypeArguments()[1];
            entityService = SpringBeanUtil.getBean(clazz);
        }
        return entityService;
    }

    public Class<T> getEntityClass() {
        if (clazzT == null) {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            clazzT = (Class<T>) type.getActualTypeArguments()[0];
        }
        return clazzT;
    }

    public String getEntitySimpleName() {
        if (entitySimpleName == null) {
            entitySimpleName = getEntityClass().getSimpleName();
        }
        return entitySimpleName;
    }

    public String getEntityClassName() {
        if (entityClassName == null) {
            entityClassName = getEntityClass().getName();
        }
        return entityClassName;
    }

    public String getTableName() {
        if (tableName == null) {
            tableName = getEntitySimpleName();
            tableName = "t_" + camelToUnderline(Character.toLowerCase(getEntitySimpleName().charAt(0)) + entitySimpleName.substring(1)) + ".";
        }
        return tableName;
    }

    public Class<M> getEntityServiceClass() {
        if (clazzM != null) {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            clazzM = (Class<M>) type.getActualTypeArguments()[1];
        }
        return clazzM;
    }

    public List doQueryByIds(String ids) {
        String[] newIds = ids.split(",");
        List<String> idList = new ArrayList<>();
        Collections.addAll(idList, newIds);
        return this.getEntityService().selectBatchIds(idList);
    }

    protected PageInfo doPageQuery(PageQueryDto dto, HttpServletRequest request) {
        Wrapper wrapper = new EntityWrapper();
        Class<T> clazz = getEntityClass();
        String className = getEntityClassName();
        ParameterBindingUtil.dtoFieldCacheHelper.checkAndAddAllIfNecessary(className, clazz);
        Field field = ParameterBindingUtil.dtoFieldCacheHelper.getFields(className).get(COMPANY_ID);
        checkCompanyId(field, wrapper, request);
        wrapper = customWrapper(wrapper);
        if (dto.getIsPageQuery()) {
            Page<T> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
            customSql(null, dto, null, request, wrapper);
            PageInfo pageInfo = new PageInfo(page);
            return pageInfo;
        } else {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setList(customSql(null, dto, null, request, wrapper));
            return pageInfo;
        }
    }

    protected PageInfo doQueryMsgByParams(T entity, PageQueryDto dto, Boolean eq, HttpServletRequest request){
        String nowTableName = getTableName();
        Wrapper wrapper = new EntityWrapper<>();
        Class<T> entityClazz = getEntityClass();
        String entityClassName = getEntityClassName();
        ParameterBindingUtil.dtoFieldCacheHelper.checkAndAddAllIfNecessary(entityClassName, entityClazz);
        Map<String, Field> fieldsMap = ParameterBindingUtil.dtoFieldCacheHelper.getFields(entityClassName);
        Map<String, Class<?>> fieldTypesMap = ParameterBindingUtil.dtoFieldCacheHelper.getFieldTypes(entityClassName);
        Map parameters = request.getParameterMap();
        Field companyIdField = fieldsMap.get(COMPANY_ID);
        checkCompanyId(companyIdField, wrapper, request);
        Wrapper finalWrapper = wrapper;
        parameters.forEach((k, v) -> {
            if (!FinalObject.SERIAL_VERSION_UID.equals(k) && !COMPANY_ID.equals(k)) {
                Field field = fieldsMap.get(k);
                Boolean flag = (field != null && !(field.getAnnotation(TableField.class) != null && !field.getAnnotation(TableField.class).exist()));
                if (flag) {
                    Object object;
                    try {
                        object = field.get(entity);
                    } catch (IllegalAccessException e) {
                        throw new GenericException("属性权限未开启");
                    }
                    String fieldName = k.toString();
                    if (object != null) {
                        Class<?> clazz = fieldTypesMap.get(k);
                        if (clazz == LocalDateTime.class || clazz == LocalDate.class || clazz == Date.class) {
                            if (request.getParameter(fieldName + FinalObject.PAGE_QUERY_TIME_SUFFIX) == null) {
                                addWrapperConditions(finalWrapper, nowTableName, fieldName, object, eq);
                            } else {
                                finalWrapper.ge(nowTableName + camelToUnderline(fieldName), object).and().le(nowTableName + camelToUnderline(fieldName), request.getParameter(fieldName + "End"));
                            }
                        } else {
                            if (clazz.isEnum()) {
                                IEnum objEnum = (IEnum) object;
                                object = objEnum.getValue();
                            }
                            addWrapperConditions(finalWrapper, nowTableName, fieldName, object, eq);
                        }
                    }
                }
            }
        });
        wrapper = customWrapper(finalWrapper);
        if (dto.getIsPageQuery()) {
            Page<T> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
            customSql(entity, dto, eq, request, wrapper);
            PageInfo pageInfo = new PageInfo(page);
            return pageInfo;
        } else {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setList(customSql(entity, dto, eq, request, wrapper));
            return pageInfo;
        }
    }

    private void checkCompanyId(Field field, Wrapper wrapper, HttpServletRequest request){
        if (field != null) {
            String companyId = request.getParameter(COMPANY_ID);
            if (companyId == null) {
                try {
                    companyId = SecurityUtil.getCompany().getCompanyId();
                } catch (NullPointerException e) {
                    throw new GenericException("请登录");
                }
            }
            String nowTableName = getTableName();
            wrapper.eq(nowTableName + COMPANY_ID_UNDERLINE, companyId);
        }
    }

    private void addWrapperConditions(Wrapper<T> wrapper, String nowTableName, String name, Object value, Boolean eq) {
        wrapper = eq ? wrapper.eq(nowTableName + camelToUnderline(name), value) : wrapper.like(nowTableName + camelToUnderline(name), value.toString());
    }

    /**
     * 驼峰转下划线
     *
     * @param param
     * @return
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
     * 分页查询wrapper重定义
     *
     * @param wrapper
     * @return
     */
    public Wrapper customWrapper(Wrapper wrapper) {
        if(ParameterBindingUtil.dtoFieldCacheHelper.getFields(entityClassName).get(UPDATE_TIME) != null) {
            wrapper.orderBy(UPDATE_TIME, false);
        }
        return wrapper;
    }

    /**
     * 重定义sql
     *
     * @param wrapper
     * @return
     */
    public List customSql(T entity, PageQueryDto dto, Boolean eq, HttpServletRequest request, Wrapper wrapper) {
        return this.getEntityService().selectList(wrapper);
    }

    protected void isAllow(AllowOperationsEnum allowOperationsEnum) {
        if (allowAll) {
            return;
        }
        for (String allowOperation : allowOperations) {
            if (allowOperationsEnum.getValue().equals(allowOperation)) {
                return;
            }
        }
        throw new GenericException("无权访问");
    }

    protected Object oneParamBinding(String name, Object value, HttpServletRequest request) throws IOException {
        Object obj = ParameterBindingUtil.requestBodyBinding(name, request);
        if (obj != null) {
            value = obj;
        } else if (value == null) {
            throw new GenericException("获取参数为空");
        }
        return value;
    }
}
