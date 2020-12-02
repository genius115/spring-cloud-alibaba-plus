package com.xiaomai.cloud.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 配置分页插件 3.4+
 *
 * Mybatis Plus 3.4.0
 * 新增了如下地内置插件：
 * 主体插件: MybatisPlusInterceptor
 * 该插件内部插件集:
 *
 * 分页插件: PaginationInnerInterceptor
 * 多租户插件: TenantLineInnerInterceptor
 * 动态表名插件: DynamicTableNameInnerInterceptor
 * 乐观锁插件: OptimisticLockerInnerInterceptor
 * sql性能规范插件: IllegalSQLInnerInterceptor
 * 防止全表更新与删除插件: BlockAttackInnerInterceptor
 *
 * @author wangfeng
 * @date 2020/11/27
 */
@Configuration
@MapperScan("com.xiaomai.cloud.mapper")
public class MyBatisPlusConfig {

    /**
     *
     *  新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration #useDeprecatedExecutor = false
     *  避免缓存出现问题(该属性会在旧插件移除后一同移除)
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
//        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
//        //乐观锁
//        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
//        //分页配置
//        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
//        return mybatisPlusInterceptor;

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

}
