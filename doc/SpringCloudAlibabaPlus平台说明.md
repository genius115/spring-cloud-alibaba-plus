# 微服务平台说明
## 一、服务简介
> 基于alibaba-nacos-XXX项目

### 1.1 alibaba-nacos-consumer 20010 消费者

### 1.5 alibaba-nacos-consumer-openfeign 20011 消费者（openfeign调用服务）


### 1.10 alibaba-nacos-provider 10010 提供者
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

### 2.5 cloud-plus-generator 6666 代码生成

### 2.10 cloud-plus-session 30010 Session管理

### 2.15 cloud-plus-xxljob  30020 分布式任务调度



> 基于nacos-boot-XXX项目
### 3.1 nacos-boot-api 10888 接口服务
  1、配置 监控 spring-boot-starter-actuator
 其他端点的路径如下表格，注意，前面需要添加/actuator根路径，如下
 请求方式 	路径 	说明
 POST 	/shutdown 	关闭应用程序，要求endpoints.shutdown.enabled设置为true
 GET 	/autoconfig 	提供了一份自动配置报告，记录哪些自动配置条件通过了，哪些没通过
 GET 	/configprops 	描述配置属性(包含默认值)如何注入Bean
 GET 	/beans 	描述应用程序上下文里全部的Bean，以及它们的关系
 GET 	/dump 	获取线程活动的快照
 GET 	/env 	获取全部环境属性
 GET 	/env/{name} 	根据名称获取特定的环境属性值
 GET 	/health 	报告应用程序的健康指标，这些值由HealthIndicator的实现类提供
 GET 	/info 	获取应用程序的定制信息，这些信息由info打头的属性提供
 GET 	/mappings 	描述全部的URI路径，以及它们和控制器(包含Actuator端点)的映射关系
 GET 	/metrics 	报告各种应用程序度量信息，比如内存用量和HTTP请求计数
 GET 	/metrics/{name} 	报告指定名称的应用程序度量值
 GET 	/trace 	提供基本的HTTP请求跟踪信息(时间戳、HTTP头等)
 作者：天不生我落雨
 链接：https://www.jianshu.com/p/14e4975a2b7d
  
  2、

### 3.5 nacos-boot-jpa 10016 持久化JPA

1、springdata-jpa与mysql
一 、springdata-jpa 的大小写
springdata-jpa默认是区分大小写的，但是生成的sql语句和字段名默认都是小写。且会将驼峰命名法转为对应的下划线。

    如：
    表名：USER--->user
    属性：userName--->user_name


二、mysql的大小写
mysql在windows环境默认不区分大小写，但是在linux环境下默认区分大小写.
MySQL在Linux下数据库名、表名、列名、别名大小写规则是这样的：

    数据库名与表名是严格区分大小写的。

    表的别名是严格区分大小写的。

    列名与列的别名在所有的情况下均是忽略大小写的。

    变量名也是严格区分大小写的。

2、spring boot 单元测试




### 3.10 nacos-boot-knife4j 10018 接口文档knife4j

### 3.15 nacos-boot-rabbitmq 10015 消息中间件RabbitMQ

### 3.20 nacos-boot-swagger 10013 接口文档
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
  
  4、kk-anti-reptile 反爬虫组件
  
  5/
  

### 3.25 nacos-boot-test 10088 测试项目
  1、sa-token 
  http://sa-token.dev33.cn/
  
  2、数据库文档生成 screw-core
  
  3、Poi操作Excel
  
  4、Validate入参验证
  
  5、Guava的Ratelimit实现接口限流 （注解+AOP）
  
  6、

### 3.30 nacos-boot-webservice 10014 WebService服务  SpringBoot2.0.1

### 3.35 nacos-provider 10017 提供者2
   1、socket io
   socket.io是js实现的，websocket框架，为了解决浏览器不兼容问题而设计
   socket.io.js下载地址：https://cdnjs.com/libraries/socket.io
   常用的方式是，前端使用socket.io.js，后端使用node.js实现socket.io的接口，可是我们的架构后端使用的是java，所以我使用的是netty-socketio，基于spring-boot实现

   2、测试自定义启动器  xiaomai-spring-boot-starter

### 3.40 nacos-provider-sentinel 10012 限流Sentinel

### 3.45 nacos-webflux 10030 异步非阻塞WebFlux(密集型I/o服务)
   1、webflux  
   2、缓存技术：
    Spring默认cache缓存/Redis缓存
    （一种使用CacheManger/一种使用RedisUtil）  
   3、MongoDB  
 
   
> 基于spring-cloud-XXX项目
### 6.1 spring-cloud=gateway-dynamic-mysql 18888  网关
   1、动态网关
   2、Nacos持久化Mysql 
   3、Redis 

### 6.5 spring-cloud-gateway-dynamic-nacos 28888 网关
   1、动态网关

### 6.10 spring-cloud-gateway-knife4j   38888 网关
  

### 6.15 spring-cloud-gateway-server 8888 网关

> 基于技术实践项目
### 7.1

### 7.2.1 xiaomai  55501
1、Junit 
assertThat 使用了 Hamcrest 的 Matcher 匹配符


### 7.2.2 xiaomai-spring-boot  55510
### 7.2.3 xiaomai-spring-boot-init  55520

### 7.5 xiaomai-spring-boot-starter 自定义启动器

> X项目
### 8.1



   1、
> 其他
### 9.10 doc 文档
   1、文档  
   2、数据库
   3、

### 9.20 log 日志
   1、日志
   

### 10 部署

#### 10.10 Spring Boot启动配置文件优先级
    
#### 10.20



## 二、文档
> 技术文档
### 1.1 Nacos配置中心
    Nacos Config
    https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config

### 1.2 Spring Boot 读取配置文件
    springboot获取properties文件的配置内容(转载)
    https://www.cnblogs.com/mr-wuxiansheng/p/10338789.html
    
### 1.3 SpringBoot系列之缓存使用教程
    https://www.cnblogs.com/mzq123/p/12629142.html
    
### 1.4 SpringBoot+Poi实现Excel的导入导出 
    https://blog.csdn.net/typ1805/article/details/83279532?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&dist_request_id=1328741.24803.16168966130071077&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control

### 1.5 前、后端代码规范（参考：阿里巴巴规约、SmartAdmin项目规范）
    http://smartadmin.1024lab.net/doc/2/157
    简介
    SmartAdmin由河南·洛阳 1024创新实验室团队研发的一套互联网企业级的通用型中后台解决方案！使用最前沿的前后台技术栈SpringBoot和Vue，前后端分离，我们开源一套漂亮的代码和一套整洁的代码规范，让大家在这浮躁的代码世界里感受到一股把代码写好的清流！同时又让开发者节省大量的时间，减少加班，快乐工作，热爱生活。SmartAdmin 让你从认识到忘不了，绝对是你最想要的！
    开源地址 (欢迎 Star ~ ~ ╰(￣▽￣)╭)
    
    github: https://github.com/1024-lab/smart-admin
    gitee: https://gitee.com/lab1024/smart-admin
    在线预览： http://preview.smartadmin.1024lab.net
    部署文档：http://smartadmin.1024lab.net/doc/2/168
    平滑升级：http://smartadmin.1024lab.net/doc/2/173
### 1.6 Java利用iText操作PDF功能大全
    https://blog.csdn.net/cliper9768/article/details/87691022
    
### 1.7 springboot(16)Spring Boot使用单元测试
    https://blog.csdn.net/sz85850597/article/details/80427408   

### XXX
    XXXX 
    
> 文档
### 2.6

## 三、规范
### 1.1 版本说明
版本命名规则
基本格式
[name].x.y.z-[state]
    name [可选] 一般为v, 表示version
    x.y.z 为各版本的序号，符合语义化版本命名规范
    stats [可选] 表示版本的状态

符合语义化版本命名规范(x.y.z)
规则如下
序号	格式要求	要求
x	非负整数	主版本号(major), 进行不向下兼容时，递增主版本号
y	非负整数	次版本号(minor), 进行向下兼容，新增特性时，递增此版本号
z	非负整数	修订(patch),保持向下兼容,修复问题，但不影响特性时，递增修订版本号

    0.y.z 表示开发阶段，一切可能随时改变，非稳定版本。
    1.0.0 界定此版本为初始稳定版，后面的一切更新都是基于此版本进行修改。

版本状态(stats)
描述方式	说明	含义
α或者a	alpha版本	内测版本，内部测试版本, bug较多
β或者b	beta版本	公测版本，给外部进行测试的版本，有缺陷
γ或者g	Gamma版本	相当成熟的版本，于发行版相差无几
rc	Release Candidate	是前面三种测试版本的进一步版本，实现了全部功能，清除了大部分bug


软件版本周期

α、β、λ 常用来表示软件测试过程中的三个阶段。

-- α 是第一阶段，一般只供内部测试使用；

-- β是第二个阶段，已经消除了软件中大部分的不完善之处，但仍有可能还存在缺陷和漏洞，一般只提供给特定的用户群来测试使用；

-- λ是第三个阶段，此时产品已经相当成熟，只需在个别地方再做进一步的优化处理即可上市发行。


开发期

-- Alpha(α)：预览版，或者叫内部测试版；一般不向外部发布，会有很多Bug；一般只有测试人员使用。

-- Beta(β)：测试版，或者叫公开测试版；这个阶段的版本会一直加入新的功能；在 Alpha版之后推出。

-- RC(Release Candidate)：最终测试版本；可能成为最终产品的候选版本，如果未出现问题则可发布成为正式版本

多数开源软件会推出两个RC版本，最后的 RC2 则成为正式版本。


完成期

-- Stable：稳定版；来自预览版本释出使用与改善而修正完成。

-- GA(General Availability)：正式发布的版本；在国外都是用GA来说明release版本的。

-- RTM(Release to Manufacturing)：给生产商的release版本；RTM版本并不一定意味着创作者解决了软件所有问题；仍有可能向公众发布前更新版本。

另外一种RTM的称呼是RTW（Release To Web），表示正式版本的软件发布到Web网站上供客户免费下载。

-- RTL(Retail)：零售版；是真正的正式版，正式上架零售版。

以Windows 7为例，RTM版与零售版的版本号是一样的。


其他表述

-- OEM(Original Equipment Manufacturer)：原始设备制造商；是给计算机厂商随着计算机贩卖的，也就是随机版；

只能随机器出货，不能零售。只能全新安装，不能从旧有操作系统升级。包装不像零售版精美，通常只有一面CD和说明书(授权书)。

-- RVL：号称是正式版，其实RVL根本不是版本的名称。它是中文版/英文版文档破解出来的。

-- EVAL：而流通在网络上的EVAL版，与“评估版”类似，功能上和零售版没有区别。



### 1.2 代码管理消息
 Add Fix Upgrade Improve Refactor

    
> 
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

   
