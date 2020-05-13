package com.study.sys.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wxl
 * @date 2020/4/7 16:04:41
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseConnectUtil {

    private static String url;

    private static String username;

    private static String password;

    public void setUrl(String url) {
        DatabaseConnectUtil.url = url;
    }

    public void setUsername(String username) {
        DatabaseConnectUtil.username = username;
    }

    public void setPassword(String password) {
        DatabaseConnectUtil.password = password;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
