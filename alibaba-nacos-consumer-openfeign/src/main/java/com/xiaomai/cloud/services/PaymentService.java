package com.xiaomai.cloud.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangfeng
 * @date 2020/11/3
 */
// @FeignClient标注的默认配置类为FeignClientsConfiguration，
@FeignClient(value = "alibaba-nacos-provider",url="111")
@RequestMapping
public interface PaymentService {
    @GetMapping(value = "/get",consumes = MediaType.APPLICATION_JSON_VALUE)
    String get();

    @GetMapping("/one")
    String demo();

    @GetMapping("/demo/one")
    String demoone();

    @GetMapping("/time/out")
    String requestTimeOut();
}
