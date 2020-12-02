package com.xiaomai.cloud.demo.repository;

import com.xiaomai.cloud.demo.po.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangfeng
 * @date 2020/12/2
 */
// 在事务层要加上
// @Transactional来代表这是一个事务级别的操作。
public interface DemoRepositoryQueryAnnotation extends JpaRepository<Demo,Integer> {
    @Query("from Demo where name=?1")
    List<Demo> queryByNameDemoHQL(String name);

    @Query(value = "select * from demo where name=?1",nativeQuery = true)
    List<Demo> queryByNameDemoSQL(String name);

    @Query("update Demo set name=?1 where id=?2")
    @Modifying //需要执行一个更新操作
    @Transactional
    void updateDemoNameById(String name,Integer id);

    @Query("select u from Demo u where u.id = :id")
    Demo findOne(@Param("id")Integer id);

}
