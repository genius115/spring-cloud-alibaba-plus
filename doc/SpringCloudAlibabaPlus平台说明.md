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
### 3.1 nacos-activiti 10310 工作流服务
 1、启动项目，在浏览器上访问Activiti5.22.0 在线设计器的页面 
 在浏览器上访问这个路径： http://localhost:10310/activiti/create
  
  
  备注：
  一、资料
  1、https://blog.csdn.net/tangthh123/article/details/105120702?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-3.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-3.control
  2、https://blog.csdn.net/xm393392625/article/details/100115045?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-3.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-3.control
  3、spring boot整合activiti6.0，整合在线设计
  https://blog.csdn.net/weixin_44596921/article/details/109735914?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242
  4、
  


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
  
  2、token权限验证 获取账户信息 Authorization  包名：com.xiaomai.cloud.api.config
    
    2.1 UserContext类  AuthManagerFilter 每次请求一个线程  LocalThread
    
    2.2 AuthUtil类  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

  3、
  
  
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
  
  6、Hutool工具包 API及使用方法
  
  7、

### 3.30 nacos-boot-webservice 10014 WebService服务  SpringBoot2.0.1

### 3.35 nacos-provider 10017 提供者2
   1、socket io
   socket.io是js实现的，websocket框架，为了解决浏览器不兼容问题而设计
   socket.io.js下载地址：https://cdnjs.com/libraries/socket.io
   常用的方式是，前端使用socket.io.js，后端使用node.js实现socket.io的接口，可是我们的架构后端使用的是java，所以我使用的是netty-socketio，基于spring-boot实现

   2、测试自定义启动器  xiaomai-spring-boot-starter
   
   3、application 配置文件
   可以借助Spring的注解@Profile实现这样的功能，这样需要定义两个实现EmailService借口的类。
   不同位置的配置文件的加载顺序
   在不指定要被加载文件时，默认的加载顺序：由里向外加载，所以最外层的最后被加载，会覆盖里层的属性（参考官网介绍）

    spring boot允许你通过命名约定按照一定的格式(application-{profile}.properties)来定义多个配置文件，然后通过在application.properyies通过spring.profiles.active来具体激活一个或者多个配置文件，如果没有没有指定任何profile的配置文件的话，spring boot默认会启动application-default.properties。
    profile的配置文件可以按照application.properyies的放置位置一样，放于以下四个位置：
    
    1、当前目录的 “/config”的子目录下
    2、当前目录下
    3、classpath根目录的“/config”包下
    4、classpath的根目录下
  
    Profile
    profile，剖面，大体意思是：我们程序可能从某几个剖面来执行应用，比如正式机环境、测试机环境、开发机环境等，每个剖面的配置可能不一样（比如开发机可能使用本地的数据库测试，正式机使用正式机的数据库测试）等；因此呢，就需要根据不同的环境选择不同的配置；如果用过maven，maven中就有profile的概念。
      
  profile有两种：
    
    默认的：通过“spring.profiles.default”属性获取，如果没有配置默认值是“default”
    明确激活的：通过“spring.profiles.active”获取    
    查找顺序是：先进性明确激活的匹配，如果没有指定明确激活的（即集合为空）就找默认的；配置属性值从Environment读取。
    
    API请参考Environment部分。设置profile属性，常见的有三种方式：    
    一、启动Java应用时，通过-D传入系统参数       
    -Dspring.profiles.active=dev
   
   
   4、
   
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

### 7.2.1 xiaomai  15101
1、Junit 
assertThat 使用了 Hamcrest 的 Matcher 匹配符


### 7.2.2 xiaomai-spring-boot  15201
    1、mybatis  ORM框架
    https://mybatis.org/mybatis-3/zh/index.html
    
    2、PageHelper  分页插件 4.1.0  5.0.0 版本4与版本5有很大差异
    https://pagehelper.github.io/
    
    配置属性注入PageHelper @Bean
    
    2.1、PageHelper支持多种数据库的分页。
    2.2、PageHelper中有6个startPage重载方法以及3个offsetPage重载方法，他们的使用基本比较相似。
    2.3、PageHelper中有两个orderBy重载方法，支持排序分页。
    
    {
        "endRow": 2,
        "hasNextPage": false,
        "hasPreviousPage": false,
        "isFirstPage": true,
        "isLastPage": true,
        "list": [{
            "createdBy": "SYSTEM",
            "createdDate": 1575545371000,
            "password": "123",
            "updatedBy": "SYSTEM",
            "updatedDate": 1575545371000,
            "userId": 1,
            "userName": "wcc"
        }],
        "navigateFirstPage": 1,
        "navigateLastPage": 1,
        "navigatePages": 8,
        "navigatepageNums": [1],
        "nextPage": 0,
        "pageNum": 1,
        "pageSize": 1000,
        "pages": 1,
        "prePage": 0,
        "size": 2,
        "startRow": 1,
        "total": 2
    }
    
    说明：
    
    1、PageHelper的优点是，分页和Mapper.xml完全解耦。实现方式是以插件的形式，对Mybatis执行的流程进行了强化，添加了总数count和limit查询。属于物理分页。
    
    2、Page page = PageHelper.startPage(pageNum, pageSize, true); - true表示需要统计总数，这样会多进行一次请求select count(0); 省略掉true参数只返回分页数据。 
    
    1)统计总数，（将SQL语句变为 select count(0) from xxx,只对简单SQL语句其效果，复杂SQL语句需要自己写）
    
        Page<?> page = PageHelper.startPage(1,-1);
    
        long count = page.getTotal();
    
    2)分页，pageNum - 第N页， pageSize - 每页M条数
    
        A、只分页不统计(每次只执行分页语句)
    
        PageHelper.startPage([pageNum],[pageSize]);
    
        List<?> pagelist = queryForList( xxx.class, "queryAll" , param);
    
        //pagelist就是分页之后的结果
    
        B、分页并统计（每次执行2条语句，一条select count语句，一条分页语句）适用于查询分页时数据发生变动，需要将实时的变动信息反映到分页结果上
    
        Page<?> page = PageHelper.startPage([pageNum],[pageSize],[iscount]);
    
        List<?> pagelist = queryForList( xxx.class , "queryAll" , param);
    
        long count = page.getTotal();
    
        //也可以 List<?> pagelist = page.getList();  获取分页后的结果集
    
    3)使用PageHelper查全部（不分页）
    
        PageHelper.startPage(1,0);
    
        List<?> alllist = queryForList( xxx.class , "queryAll" , param);
    
    4)PageHelper的其他API
    
        String orderBy = PageHelper.getOrderBy();    //获取orderBy语句
    
        Page<?> page = PageHelper.startPage(Object params);
    
        Page<?> page = PageHelper.startPage(int pageNum, int pageSize);
    
        Page<?> page = PageHelper.startPage(int pageNum, int pageSize, boolean isCount);
    
        Page<?> page = PageHelper.startPage(pageNum, pageSize, orderBy);
    
        Page<?> page = PageHelper.startPage(pageNum, pageSize, isCount, isReasonable);    //isReasonable分页合理化,null时用默认配置
    
        Page<?> page = PageHelper.startPage(pageNum, pageSize, isCount, isReasonable, isPageSizeZero);    //isPageSizeZero是否支持PageSize为0，true且pageSize=0时返回全部结果，false时分页,null时用默认配置
    
    5)、默认值
    
        //RowBounds参数offset作为PageNum使用 - 默认不使用
    
        private boolean offsetAsPageNum = false;
    
        //RowBounds是否进行count查询 - 默认不查询
    
        private boolean rowBoundsWithCount = false;
    
        //当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
    
        private boolean pageSizeZero = false;
    
        //分页合理化
    
        private boolean reasonable = false;
    
        //是否支持接口参数来传递分页参数，默认false
    
        private boolean supportMethodsArguments = false;  
    注意事项：
    4. 什么时候会导致不安全的分页？
     
    PageHelper 方法使用了静态的 ThreadLocal 参数，分页参数和线程是绑定的。
     
    只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，这就是安全的。因为 PageHelper 在 finally 代码段中自动清除了 ThreadLocal 存储的对象。
     
    如果代码在进入 Executor 前发生异常，就会导致线程不可用，这属于人为的 Bug（例如接口方法和 XML 中的不匹配，导致找不到 MappedStatement 时）， 这种情况由于线程不可用，也不会导致 ThreadLocal 参数被错误的使用。
     
    但是如果你写出下面这样的代码，就是不安全的用法：
     
    PageHelper.startPage(1, 10);
    List<Country> list;
    if(param1 != null){
        list = countryMapper.selectIf(param1);
    } else {
        list = new ArrayList<Country>();
    }
    这种情况下由于 param1 存在 null 的情况，就会导致 PageHelper 生产了一个分页参数，但是没有被消费，这个参数就会一直保留在这个线程上。当这个线程再次被使用时，就可能导致不该分页的方法去消费这个分页参数，这就产生了莫名其妙的分页。
     
    上面这个代码，应该写成下面这个样子：
     
    List<Country> list;
    if(param1 != null){
        PageHelper.startPage(1, 10);
        list = countryMapper.selectIf(param1);
    } else {
        list = new ArrayList<Country>();
    }
    这种写法就能保证安全。
     
    如果你对此不放心，你可以手动清理 ThreadLocal 存储的分页参数，可以像下面这样使用：
     
    List<Country> list;
    if(param1 != null){
        PageHelper.startPage(1, 10);
        try{
            list = countryMapper.selectAll();
        } finally {
            PageHelper.clearPage();
        }
    } else {
        list = new ArrayList<Country>();
    }
    这么写很不好看，而且没有必要。
    
    3、tk.mybatis  通用mapper (可自行扩展BaseMapper)
    https://mapperhelper.github.io/docs/
    
    4、MyBatis Generator  代码自动生成    
    

### 7.2.3 xiaomai-spring-boot-init  15301

    1、spring retry机制
    
### 7.2.4 xiaomai-spring-boot-pagehelper 15302
    1、pagehelper 分页插件
    

### 7.5 xiaomai-spring-boot-starter 自定义启动器

> X项目
### 8.1


> 系统端口及项目结构

    服务默认端口：   生产环境需要集群、密码和端口修改
    1、mysql 3306 oracle 1521 
    2、redis 6379
    3、RabbitMQ 4369、5672、15672、25672
    4、tomcat 8080
    5、nginx 80 443 
    6、nacos 8848
    7、minio 9000
    8、seata 8091
    
    
    0~1024

    10000~20000
    
    65535


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
    PropertySourceBootstrapProperties类
    1、Nacos Config
    https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config
    2、Application.properties与Bootstrap.properties的差异
    https://www.cnblogs.com/BlogNetSpace/p/8469033.html
    https://blog.csdn.net/dulabing/article/details/80183662?utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control
    https://blog.csdn.net/uniquewonderq/article/details/79963719
    3、
    

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

### 1.8 持久化 mybatis PageHelper Tk.mapper
    1、MyBatis分页插件PageHelper的使用及原理浅析
    https://blog.csdn.net/zuoshengdong/article/details/104729253
    pagehelper使用方法及参数说明
    https://blog.csdn.net/java_2017_csdn/article/details/107637887
    MyBatis使用PageHelper排序分页
    https://blog.csdn.net/nklinsirui/article/details/105040621
    2、MyBatis 
    
    3、SpringBoot整合mybatis通用Mapper+自定义通用Mapper方法
    https://blog.csdn.net/ypp91zr/article/details/89006493
    
### 1.9 23种设计模式
    1、https://www.runoob.com/design-pattern/chain-of-responsibility-pattern.html
    2、http://c.biancheng.net/view/1383.html 
    3、
    
### 1.10 流水号  （Oracle 序列 sequence；Mysql）
    1、Mybatis之nextval函数生成流水号，行级锁实现线程安全的nextval()方法
    https://blog.csdn.net/weixin_41888813/article/details/88545890
    用法：
    select currval('serial_number') seq;
    select nextval('serial_number') seq;
    
    2、oracle中的CURRVAL和NEXTVAL用法
    https://blog.csdn.net/qianyiyiding/article/details/51592689
    用法：
    create sequence INR_REQUIRMENT_SQUENCE    
    INCREMENT BY 1 -- 每次加几个  
    START WITH 1 -- 从1开始计数  
    NOMAXVALUE -- 不设置最大值  
    NOCYCLE -- 一直累加，不循环  
    CACHE 10; 
    select INR_REQUIRMENT_SQUENCE.CURRVAL from dual
    
### 1.11 Spring-Cloud-openfeign 
    1、spring cloud——feign为GET请求时的对象参数传递
    https://www.cnblogs.com/qq503665965/p/9865881.html
      
    2、处理openFeign自动将get请求装换为post请求的问题
    https://snakey.blog.csdn.net/article/details/105389846 
    
    3、Openfeign开发过程中，对请求参数的要求
    https://blog.csdn.net/weixin_44257627/article/details/104212985
    
    4、Feign消费服务时POST/GET请求方式
    https://blog.csdn.net/justry_deng/article/details/80785973
    
    5、【Java用法】Feign @QueryMap支持，@SpringQueryMap注解 feign的get传参方式
    https://blog.csdn.net/weixin_44299027/article/details/106261948
    
    
       
    
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

SNAPSHOT
RC
SR
GA

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

   
