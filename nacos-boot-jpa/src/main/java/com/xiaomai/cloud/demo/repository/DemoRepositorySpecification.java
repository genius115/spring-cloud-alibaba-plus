package com.xiaomai.cloud.demo.repository;

import com.xiaomai.cloud.demo.po.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wangfeng
 * @date 2020/12/2
 */
public interface DemoRepositorySpecification extends JpaRepository<Demo,Integer>,JpaSpecificationExecutor<Demo> {

}
