package com.xiaomai.xiaomaispringbootbase.config;

/**
 * @author Madison
 * @date 2022/7/9
 */
import com.xiaomai.xiaomaispringbootbase.util.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扩展数据源
 *
 *
 * @author vander
 * @date 2018年11月26日
 */
@Configuration
@ConditionalOnProperty(value="pingruan.base.c3p0.data.use-ext-data",havingValue="true")
public class DbConfig {
    @Autowired
    C3p0Properties c3p0Properties;
    
    @Bean
    public Db dbs() {
        return new Db(c3p0Properties);
    }
}
