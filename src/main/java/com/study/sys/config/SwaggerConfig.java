package com.study.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wxl
 * @date 2020/3/30 12:01:39
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("")
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.study.sys.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }


        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("**中再生OA平台接口**")
                    .description("1.提供OA后台使用的接口 2.提供对其他服务调用的服务")
                    .contact(new Contact("wxl", "127.0.0.1:8082", "328717542@qq.com"))
                    .version("1.0")
                    .termsOfServiceUrl("127.0.0.1:8082")
                    .build();
        }


}

