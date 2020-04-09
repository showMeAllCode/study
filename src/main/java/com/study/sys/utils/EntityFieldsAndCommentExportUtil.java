package com.study.sys.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author wxl
 * @date 2020/4/8 09:29:41
 */
@Slf4j
public class EntityFieldsAndCommentExportUtil {
    public static void get(List<Object> entityFieldsList, List<Object> entityCommentList, String tableName) throws SQLException, ClassNotFoundException {
        Connection connect = DatabaseConnectUtil.getConnection();
        try {
            Statement stmt = (Statement) connect.createStatement();
            ResultSet rs = stmt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                entityFieldsList.add(rs.getString("Field"));
                entityCommentList.add(rs.getString("Comment"));
            }
        } catch (Exception e) {
            log.error("获取实体类字段及备注失败，失败原因：{}", e);
            throw new RuntimeException("获取实体类字段及备注失败");
        }finally {
            connect.close();
        }
    }
}
