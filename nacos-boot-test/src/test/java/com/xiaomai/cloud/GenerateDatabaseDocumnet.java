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
        hikariConfig.setJdbcUrl("jdbc:mysql://192.168.0.11:3306/mpms_quality?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("00000000");

        /*hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@192.168.0.2:1521:ORCL");
        hikariConfig.setUsername("NHMPMS");
        hikariConfig.setPassword("MPMS");*/

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
        List<String> ignorePrefix = Arrays.asList("test_", "test"
                ,"A","B","C","D","E","F","G","H","I","J","K","L","M"
                ,"NH_VISUAL","NYXT_KZ"
                ,"O","Q","R","S","T","U","V","W","X","Y","Z");
        // 忽略表后缀
        List<String> ignoreSuffix = Arrays.asList("_test", "test");

        // 2、配置想要忽略的表
        ProcessConfig processConfig = ProcessConfig.builder().ignoreTableName(ignoreTableName)
                .ignoreTablePrefix(ignorePrefix).ignoreTableSuffix(ignoreSuffix).build();

        // 3、生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                //.title("标题【数据库设计文档】")
                //.title("【综合展现-沙盘数据库设计文档】")
                .title("【基建数字化平台-质量管理 数据库设计文档】")
                .version("1.0.0")
                .description("描述【数据库文档】")
                .organization("组织【北京易用视点】")
                .organizationUrl("网址【http://www.vrfirst.cn】")
                .dataSource(dataSource)
                .engineConfig(engineConfig).produceConfig(processConfig).build();

        // 4、执行生成
        new DocumentationExecute(config).execute();

        System.out.println("数据库文档生成完成。。。");

    }

}