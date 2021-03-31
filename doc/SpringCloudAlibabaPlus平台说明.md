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
   
   8、opslabJutil项目-Java Utils
   封装了一些常用Java操作方法,便于重复开发利用。可以直接导出成jar包引用到项目中。 

   9、Spring Boot获取配置文件信息

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
  1、Swagger-ui
  原生文档访问地址：Swagger
  http://localhost:10013/swagger-ui.html
  接口文档访问地址：Swagger-ui
  http://localhost:10013/doc.html
  
> swagger-ui默认访问地址
  boot工程格式如下  
  http://${ip}:{port}/swagger-ui.html  
  非boot工程加个自己项目名  
  http://${ip}:{port}/xxx/swagger-ui.html
  
  
  2、SpringBoot启动后自动执行方法
  A:实现ApplicatonRunner接口 B:实现COmmandLinrRUnner接口
  
  3、依据Swagger生成静态文档  Swagger2Markup 导出swagger 接口文档(html，pdf)
  https://blog.csdn.net/fly910905/article/details/105504324
  https://blog.csdn.net/asenseof/article/details/106461884
 
  https://github.com/Swagger2Markup/spring-swagger2markup-demo
  https://github.com/Swagger2Markup/swagger2markup
  
  4/
  
  5/
  

### 3.3.1nacos-boot-test 1088 测试项目
  1、sa-token 
  http://sa-token.dev33.cn/
  
  2、数据库文档生成 screw-core
  
  3、Poi操作Excel
  
  4、Validate入参验证
  
  5、
  

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
   

### 10 部署

#### 10.1 Spring Boot启动配置文件优先级
    
#### 10.2



## 2、文档
### 2.1 Nacos配置中心
    Nacos Config
    https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config

### 2.2 Spring Boot 读取配置文件
    springboot获取properties文件的配置内容(转载)
    https://www.cnblogs.com/mr-wuxiansheng/p/10338789.html
    
### 2.3 SpringBoot系列之缓存使用教程
    https://www.cnblogs.com/mzq123/p/12629142.html
    
### 2.4 SpringBoot+Poi实现Excel的导入导出 
    https://blog.csdn.net/typ1805/article/details/83279532?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&dist_request_id=1328741.24803.16168966130071077&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control

### 2.5 前、后端代码规范（参考：阿里巴巴规约、SmartAdmin项目规范）
    http://smartadmin.1024lab.net/doc/2/157
    简介
    SmartAdmin由河南·洛阳 1024创新实验室团队研发的一套互联网企业级的通用型中后台解决方案！使用最前沿的前后台技术栈SpringBoot和Vue，前后端分离，我们开源一套漂亮的代码和一套整洁的代码规范，让大家在这浮躁的代码世界里感受到一股把代码写好的清流！同时又让开发者节省大量的时间，减少加班，快乐工作，热爱生活。SmartAdmin 让你从认识到忘不了，绝对是你最想要的！
    开源地址 (欢迎 Star ~ ~ ╰(￣▽￣)╭)
    
    github: https://github.com/1024-lab/smart-admin
    gitee: https://gitee.com/lab1024/smart-admin
    在线预览： http://preview.smartadmin.1024lab.net
    部署文档：http://smartadmin.1024lab.net/doc/2/168
    平滑升级：http://smartadmin.1024lab.net/doc/2/173

### 2.6

 
    

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

   
