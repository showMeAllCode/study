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

    private static String driver = "com.mysql.jdbc.Driver";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        DatabaseConnectUtil.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        DatabaseConnectUtil.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        DatabaseConnectUtil.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        DatabaseConnectUtil.driver = driver;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection connection = (Connection) DriverManager.getConnection(url, username, password);
        return connection;
    }
}
