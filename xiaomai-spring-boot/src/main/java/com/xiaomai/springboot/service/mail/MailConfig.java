package com.xiaomai.springboot.service.mail;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Madison
 * @date 2022/6/3
 */
@Component
public class MailConfig {

    /**
     * 默认情况下，使用 @Bean声明一个bean，bean的名称由方法名决定。此外，可以通过@Bean注解里面的name属性主动设置bean的名称。
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "mail.type",havingValue = "ali")
    public MailService aliMailService(){
        return new AliMailService();
    }

    /**
     * 默认情况下，使用 @Bean声明一个bean，bean的名称由方法名决定。此外，可以通过@Bean注解里面的name属性主动设置bean的名称。
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "mail.type",havingValue = "sina")
    public MailService sinaMailService(){
        return new SinaMailService();
    }
    
}
