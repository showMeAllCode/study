package com.study.sys.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/3/31 08:18:29
 */
@Configuration
public class MybatisPlusConfig {

    @Value("#{dataSource}")
    private DataSource dataSource;

    @Bean("sqlSessionFactory")
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resolver.getResources("classpath:mybatis/*.xml");
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(mapperLocations);
        //设置 MyBatis-Plus 分页插件
        Interceptor[] plugins = new Interceptor[1];
        plugins[0] = paginationInterceptor();
        mybatisSqlSessionFactoryBean.setPlugins(plugins);
        return mybatisSqlSessionFactoryBean;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(300);
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
