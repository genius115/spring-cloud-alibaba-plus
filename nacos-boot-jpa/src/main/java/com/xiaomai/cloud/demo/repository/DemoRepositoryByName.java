package com.xiaomai.cloud.demo.repository;

import com.xiaomai.cloud.demo.po.Demo;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author wangfeng
 * @date 2020/12/2
 */
/**
 *
 * Repository接口的使用
 *
 *  提供了方法名称命名查询方式
 *  提供了基于@Query注解查询与更新
 *
 * 1、dao层接口（方法名称命名查询方式） *
 *    Repository接口方法名称命名查询
 *
 */
public interface DemoRepositoryByName extends Repository<Demo,Integer> {
    // 方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
    List<Demo> findByName(String name);

    List<Demo> findByNameAndAge(String name,Integer age);

    List<Demo> findByNameLike(String name);

}
