package com.study.sys.utils.handler;

import com.study.sys.utils.CustomEnumUtil;
import com.study.sys.utils.EnumHelperUtil;
import com.study.sys.utils.EnumUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wxl
 * @date 2020/4/24 10:55:09
 */
public class EnumBaseHandler<E extends Enum<?> & EnumUtil> extends BaseTypeHandler<EnumUtil> {

    private Class<E> clazz;

    private CustomEnumUtil customEnumUtil;

//    EnumBaseHandler(){
//        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
//        this.clazz = (Class<E>) type.getActualTypeArguments()[0];
//        customEnumUtil = EnumHelperUtil.customEnumUtil(clazz);
//    }

    EnumBaseHandler(Class<E> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
        customEnumUtil = EnumHelperUtil.customEnumUtil(clazz);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EnumUtil parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setObject(i, parameter.getValue());
        } else {
            ps.setObject(i, parameter.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null == rs.getString(columnName) && rs.wasNull() ? null : (E) customEnumUtil.valueOf(rs.getObject(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null == rs.getString(columnIndex) && rs.wasNull() ? null : (E) customEnumUtil.valueOf(rs.getObject(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null == cs.getString(columnIndex) && cs.wasNull() ? null : (E) customEnumUtil.valueOf(cs.getObject(columnIndex));
    }

}
