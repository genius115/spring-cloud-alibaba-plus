package com.xiaomai.activiti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author luojie 2018/6/9
 */
@Configuration
public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
          registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");super.addResourceHandlers(registry);
     }
}

