//package com.study.sys.config;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.activation.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author wxl
// * @date 2020/4/2 16:09:38
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Bean("dataSource")
//    public DataSource dataSource() throws Exception {
//        Map map = new HashMap();
//        map.put("driverClassName", "com.mysql.cj.jdbc.Driver");
//        map.put("url", "jdbc:mysql://cdb-obab2ln3.gz.tencentcdb.com:10005/newOA?useUnicode=true&useSSL=false&characterEncoding=utf8&severTimezone=Asia/Shanghai");
//        map.put("username", "root");
//        map.put("password", "xy123456");
//        return (DataSource) DruidDataSourceFactory.createDataSource(map);
//    }
//}
