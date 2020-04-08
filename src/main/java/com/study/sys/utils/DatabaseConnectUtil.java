package com.study.sys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wxl
 * @date 2020/4/7 16:04:41
 */
public class DatabaseConnectUtil {

//    @Value("${spring.datasource.url}")
    private String url = "jdbc:mysql://10.35.11.146:3309/oa_zs_56?useUnicode=true&useSSL=false&characterEncoding=utf8&severTimezone=Asia/Shanghai";

//    @Value("${spring.datasource.username}")
    private String username = "root";

//    @Value("${spring.datasource.password}")
    private String password = "123456";

//    @Value("${spring.datasource.driver-class-name}")
    private String driver = "com.mysql.jdbc.Driver";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection connection = (Connection) DriverManager.getConnection(url, username, password);
        return connection;
    }
}
