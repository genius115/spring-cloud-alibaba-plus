package com.xiaomai.cloud.service;

/**
 * @author wangfeng
 * @date 2020/12/3
 */
import javax.jws.WebService;

@WebService(name = "DemoService", // 暴露服务名称
        targetNamespace = "http://service.cloud.xiaomai.com"// 命名空间,一般是接口的包名倒序
)
public interface DemoService {

    String sayHello(String user);

}
