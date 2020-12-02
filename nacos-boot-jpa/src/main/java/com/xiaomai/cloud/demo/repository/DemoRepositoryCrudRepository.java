package com.xiaomai.cloud.demo.repository;

import com.xiaomai.cloud.demo.po.Demo;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository接口,主要是完成一些增删改查的操作。注意：CrudRepository接口继承了Repository接口
 * @author wangfeng
 * @date 2020/12/2
 */
public interface DemoRepositoryCrudRepository extends CrudRepository<Demo,Integer> {

}
