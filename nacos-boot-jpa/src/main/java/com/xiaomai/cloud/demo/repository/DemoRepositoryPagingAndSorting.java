package com.xiaomai.cloud.demo.repository;

import com.xiaomai.cloud.demo.po.Demo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 该接口提供了分页与排序的操作，注意：该接口继承了CrudRepository接口
 * @author wangfeng
 * @date 2020/12/2
 */
public interface DemoRepositoryPagingAndSorting extends PagingAndSortingRepository<Demo,Integer> {

}
