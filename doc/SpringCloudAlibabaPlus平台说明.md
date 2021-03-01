# 微服务平台说明
## 1、服务简介
> 基于alibaba-nacos-XXX项目

### 1.1 alibaba-nacos-consumer 20010 消费者

### 1.2 alibaba-nacos-consumer-openfeign 20011 消费者（openfeign调用服务）

### 1.3 alibaba-nacos-provider 10010 提供者
   1、Nacos配置中心  com.xiaomai.cloud.controller.test.TestNacosController  
   公共配置、私有配置     
   2、Mybaits-Plus  多数据源、多类型(Mysql/PostgresQL)数据库、分页     
   3、Swagger-ui  knife4j-spring-boot-starter(2.9.6+)     
   4、MinIO     
   5、验证码 captcha     
   6、Spring Boot 配置: spring-boot-configuration-processor  
   7、生成流水号
   单应用部署环境（项目启动时，从数据库获取当前最大值，作为静态变量存储）、
   分布式部署环境（线程同步+redis分布式锁实现））  

> 基于cloud-plus-XXX项目
### 2.1 cloud-plus-comm 公共服务

### 2.2 cloud-plus-generator 6666 代码生成

### 2.3 cloud-plus-session 30010 Session管理

### 2.4 cloud-plus-xxljob  30020 分布式任务调度

> 基于nacos-boot-XXX项目

### 3.1 nacos-boot-jpa 10016 持久话JPA

### 3.1 nacos-boot-knife4j 10018 接口文档knife4j

### 3.2 nacos-boot-rabbitmq 10015 消息中间件RabbitMQ

### 3.3 nacos-boot-swagger 10013 接口文档

### 3.4 nacos-boot-webservice 10014 WebService服务

### 3.5 nacos-provider 10017 提供者2
   1、socket io
   socket.io是js实现的，websocket框架，为了解决浏览器不兼容问题而设计
   socket.io.js下载地址：https://cdnjs.com/libraries/socket.io
   常用的方式是，前端使用socket.io.js，后端使用node.js实现socket.io的接口，可是我们的架构后端使用的是java，所以我使用的是netty-socketio，基于spring-boot实现


### 3.6 nacos-provider-sentinel 10012 限流Sentinel

### 3.7 nacos-webflux 10030 异步非阻塞WebFlux(密集型I/o服务)
   1、webflux  
   2、缓存技术：
    Spring默认cache缓存/Redis缓存
    （一种使用CacheManger/一种使用RedisUtil）  
   3、MongoDB  
 
   
   
   
> 基于spring-cloud-XXX项目
### 8.1 spring-cloud=gateway-dynamic-mysql 18888  网关
   1、动态网关
   2、Nacos持久化Mysql 
   3、Redis 

### 8.2 spring-cloud-gateway-dynamic-nacos 28888 网关
   1、动态网关

### 8.2 spring-cloud-gateway-knife4j   38888 网关
  

### 8.3 spring-cloud-gateway-server 8888 网关
   1、
> 其他
### 9.1 doc 文档
   1、文档  
   2、数据库
   3、

### 9.2 log 日志
   1、日志
   
## 2、文档
### 2.1 Nacos配置中心
    Nacos Config
    https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config

### 2.2 Spring Boot 读取配置文件
    springboot获取properties文件的配置内容(转载)
    https://www.cnblogs.com/mr-wuxiansheng/p/10338789.html
    
### 2.3 SpringBoot系列之缓存使用教程
    https://www.cnblogs.com/mzq123/p/12629142.html
    

# 项目介绍
## 文件结构
### 后端结构
com.ruoyi     
├── ruoyi-ui              // 前端框架 [80]
├── ruoyi-gateway         // 网关模块 [8080]
├── ruoyi-auth            // 认证中心 [9200]
├── ruoyi-api             // 接口模块
│       └── ruoyi-api-system                          // 系统接口
├── ruoyi-common          // 通用模块
│       └── ruoyi-common-core                         // 核心模块
│       └── ruoyi-common-datascope                    // 权限范围
│       └── ruoyi-common-datasource                   // 多数据源
│       └── ruoyi-common-log                          // 日志记录
│       └── ruoyi-common-redis                        // 缓存服务
│       └── ruoyi-common-security                     // 安全模块
│       └── ruoyi-common-swagger                      // 系统接口
├── ruoyi-modules         // 业务模块
│       └── ruoyi-system                              // 系统模块 [9201]
│       └── ruoyi-gen                                 // 代码生成 [9202]
│       └── ruoyi-job                                 // 定时任务 [9203]
│       └── ruoyi-file                                // 文件服务 [9300]
├── ruoyi-visual          // 图形化管理模块
│       └── ruoyi-visual-monitor                      // 监控中心 [9100]
├──pom.xml                // 公共依赖

   
