package com.xiaomai.cloud.nacosprovider;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

// @SpringBootApplication

/**
 * Spring Boot 应用启动类
 *
 * @author : liangxifeng
 * @date : 2018-1-19
 */
// Spring Boot 应用的标识
@SpringBootApplication
//如果mybatis中service实现类中加入事务注解，需要此处添加该注解
//@EnableTransactionManagement
// mapper 接口类扫描包配置
//@MapperScan("domain.dao")
@Slf4j
public class NacosProviderApplication extends SpringBootServletInitializer {

    private Logger logger = LoggerFactory.getLogger(NacosProviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);

        /**
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosProviderApplication.class, args);

//        while(true){
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            String userCode = applicationContext.getEnvironment().getProperty("user.code");
            System.err.println("user name :"+userName+"; age: "+userAge+"; code: "+userCode);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }
        */
    }

    //读取配置文件
    //@Autowired
    //private YmlConfig ymlConfig;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        return springApplicationBuilder.sources(NacosProviderApplication.class);
    }

    /**
     * 注册netty-socketio服务端
     * @author liangxifeng 2018-07-07
     * @return
     */
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            //在本地window环境测试时用localhost
            System.out.println("this is  windows");
            logger.info("This is Windows!");
            config.setHostname("localhost");
        } else {
            config.setHostname("192.168.0.106");
        }
        config.setPort(9092);
        // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(10000);

        // Ping消息间隔（毫秒），默认25000。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(25000);

        // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(60000);

        /*config.setAuthorizationListener(new AuthorizationListener() {//类似过滤器
            @Override
            public boolean isAuthorized(HandshakeData data) {
                //http://localhost:8081?username=test&password=test
                //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
                // String username = data.getSingleUrlParam("username");
                // String password = data.getSingleUrlParam("password");
                return true;
            }
        });*/

        final SocketIOServer server = new SocketIOServer(config);
        return server;
    }

    /**
     * tomcat启动时候，扫码socket服务器并注册
     * @param socketServer
     * @return
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

}
