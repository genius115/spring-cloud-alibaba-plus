package com.xiaomai.cloud.service;

import javax.jws.WebService;
import java.util.Date;

/**
 * @author wangfeng
 * @date 2020/12/3
 */
@WebService(serviceName = "DemoService", // 与接口中指定的name一致
        targetNamespace = "http://service.cloud.xiaomai.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.xiaomai.cloud.service.DemoService"// 接口地址
)
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String user) {
        return user + "，现在时间：" + "(" + new Date() + ")";
    }
}