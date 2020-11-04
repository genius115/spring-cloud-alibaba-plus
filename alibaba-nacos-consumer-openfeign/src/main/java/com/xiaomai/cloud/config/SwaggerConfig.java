package com.xiaomai.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangfeng
 * @date 2020/11/4
 */
@Configuration
@EnableOpenApi
// @EnableSwagger2
// @EnableWebMvc
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean swagger_is_enable;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .enable(swagger_is_enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaomai.cloud.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基于Swagger3.0.0的接口文档-(consumer-openfeign)")
                .description("OpenFeign服务调用")
                .version("1.0")
                .contact(new Contact("Xiaomai", "http://www.baidu.com", "123456@qq.com"))
                .build();
    }
}
