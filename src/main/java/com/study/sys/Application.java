package com.study.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wxl
 * @date 2020/3/30 13:41:04
 */
@ComponentScan
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@MapperScan("com.study.sys.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
