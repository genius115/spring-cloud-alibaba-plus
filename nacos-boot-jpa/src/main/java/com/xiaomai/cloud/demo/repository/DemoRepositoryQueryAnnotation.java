package com.xiaomai.cloud.demo.repository;

import com.xiaomai.cloud.demo.po.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *  使用Native SQL Query（nativeQuery=true则使用原生SQL默认HQL）
 *  所谓本地查询，就是使用原生的sql语句（根据数据库的不同，在sql的语法或结构方面可能有所区别）进行查询数据库的操作
 *
 * 注意事项：
 * 1、update或delete时必须使用@Modifying和@Transactional对方法进行注解，才能使得ORM知道现在要执行的是写操作
 *
 * @author wangfeng
 * @date 2020/12/2
 */
// 在事务层要加上
// @Transactional来代表这是一个事务级别的操作。
public interface DemoRepositoryQueryAnnotation extends JpaRepository<Demo,Integer> {
    @Query("from Demo where name=?1")
    List<Demo> queryByNameDemoHQL(String name);

    //表名为数据库对象名称
    @Query(value = "select * from demo where name=?1",nativeQuery = true)
    List<Demo> queryByNameDemoSQL(String name);

    @Query("update Demo set name=?1 where id=?2")
    @Modifying  /*update或delete 需要执行一个更新操作*/
    @Transactional
    void updateDemoNameById(String name,Integer id);


    @Query("select u from Demo u where u.id = :id")
    Demo findOne(@Param("id")Integer id);


    @Query(value = "select * from demo where id in :ids",nativeQuery = true)
    List<Demo> queryByIds(@Param(value = "ids") List<String> ids);

}
