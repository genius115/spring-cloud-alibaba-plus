package com.xiaomai.cloud;


import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

import java.util.Arrays;
import java.util.List;
/**
 * @author Madison
 * @date 2021/3/3
 * 生成数据库文档
 *
 * @author CL
 *
 */
public class GenerateDatabaseDocumnet {

    public static void main(String[] args) {
        // 数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/nacos_config?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("mysql");
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);

        // 1、生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                //.fileOutputDir("C:\\Users\\Administrator\\Desktop")
                .fileOutputDir("C:\\Users\\wangfeng\\IdeaProjects\\spring-cloud-alibaba-plus\\nacos-boot-test\\doc")
                // 打开目录
                .openOutputDir(false)
                // 文件类型   文件类型
                //  EngineFileType枚举类中提供三种类型：HTML、WORD、MD。
                .fileType(EngineFileType.WORD)
                // 生成模板实现  模板类型
                //  EngineTemplateType枚举类中提供两种类型：freemarker、velocity。
                .produceType(EngineTemplateType.freemarker).build();

        // 忽略表名
        List<String> ignoreTableName = Arrays.asList("test");
        // 忽略表前缀
        List<String> ignorePrefix = Arrays.asList("test_", "test");
        // 忽略表后缀
        List<String> ignoreSuffix = Arrays.asList("_test", "test");

        // 2、配置想要忽略的表
        ProcessConfig processConfig = ProcessConfig.builder().ignoreTableName(ignoreTableName)
                .ignoreTablePrefix(ignorePrefix).ignoreTableSuffix(ignoreSuffix).build();

        // 3、生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .title("标题【数据库设计文档】")
                .version("1.0.0")
                .description("描述【数据库文档】")
                .organization("组织【小麦科技】")
                .organizationUrl("网址【www.baidu.com】")
                .dataSource(dataSource)
                .engineConfig(engineConfig).produceConfig(processConfig).build();

        // 4、执行生成
        new DocumentationExecute(config).execute();

        System.out.println("数据库文档生成完成。。。");

    }

}