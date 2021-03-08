package com.xiaomai.boot.myjson.config;

import com.xiaomai.boot.myjson.service.MyJsonService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类
 *
 * @author Madison
 * @date 2021/3/2
 */
//标识此类为配置类
@Configuration
//表示只有指定的class在classpath上时才能被注册
@ConditionalOnClass(MyJsonService.class)
//激活@ConfigurationProperties
@EnableConfigurationProperties(MyJsonProperties.class)
public class MyJsonConfiguration {

    private MyJsonProperties myJsonProperties;

    // 自动注入配置类
    public MyJsonConfiguration(MyJsonProperties myJsonProperties) {
        this.myJsonProperties = myJsonProperties;
    }

    //创建MyJsonService对象，注入到Spring容器中
    @Bean
    @ConditionalOnProperty(name = "nobody.json.enable", matchIfMissing = true)  //控制bean的实例化
    @ConditionalOnMissingBean(MyJsonService.class) //当容器没有此bean时，才注册
    public MyJsonService myJsonService() {
        MyJsonService myJsonService = new MyJsonService();
        myJsonService.setPrefixName(myJsonProperties.getPrefixName());
        myJsonService.setSuffixName(myJsonProperties.getSuffixName());
        return myJsonService;
    }

}
