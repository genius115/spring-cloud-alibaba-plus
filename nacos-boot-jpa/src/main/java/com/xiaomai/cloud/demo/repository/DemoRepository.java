package com.xiaomai.cloud.demo.repository;

import com.xiaomai.cloud.demo.po.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 *参数一 T :当前需要映射的实体
 *参数二 ID :当前映射的实体中的OID的类型
 *
 * @author wangfeng
 * @date 2020/12/1
 */

public interface DemoRepository extends JpaRepository<Demo,Integer> {

}



