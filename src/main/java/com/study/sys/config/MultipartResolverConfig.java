package com.study.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author wxl
 * @date 2020/4/1 16:26:49
 */
@Configuration
public class MultipartResolverConfig {
    @Bean("multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(51200000);
        resolver.setMaxUploadSizePerFile(10240000);
        //resolveLazily属性启用是为了推迟文件解析，以在UploadAction中捕获文件大小异常
//        resolver.setResolveLazily(true);
//        resolver.setPreserveFilename();
//        resolver.setUploadTempDir();
        return resolver;
    }
}
