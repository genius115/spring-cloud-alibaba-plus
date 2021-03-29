package com.xiaomai.cloud.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

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
@EnableTransactionManagement
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
//

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // <!-- 对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型 -->
        // interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));

        //乐观锁  实体类和数据库新增字段version 更新操作时出现
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // 多数据源则每次进行抓取数据库类型

        PaginationInnerInterceptor paginationInnerInterceptor= new PaginationInnerInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        paginationInnerInterceptor.setOverflow(false);
        paginationInnerInterceptor.setMaxLimit(500L);
//        paginationInnerInterceptor.setDialect(new IDialect() {
//            @Override
//            public DialectModel buildPaginationSql(String originalSql, long offset, long limit) {
////                MySqlDialect mysql数据库的分页SQL构造器
////                StringBuilder sql = (new StringBuilder(originalSql)).append(" LIMIT ").append("?");
////                if (offset != 0L) {
////                    sql.append(",").append("?");
////                    return (new DialectModel(sql.toString(), offset, limit)).setConsumerChain();
////                } else {
////                    return (new DialectModel(sql.toString(), limit)).setConsumer(true);
////                }
//                return new DialectModel(originalSql,offset,limit);
//            }
//        });
        //Properties properties = new Properties();
        //paginationInnerInterceptor.setProperties(properties);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

}
