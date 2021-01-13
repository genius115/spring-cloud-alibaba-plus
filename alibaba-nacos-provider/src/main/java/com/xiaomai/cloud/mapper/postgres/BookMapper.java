package com.xiaomai.cloud.mapper.postgres;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomai.cloud.po.postgres.Book;

/**
 * @author developer
 * @date 2020/12/31
 */
@DS("postgresdb")
// @InterceptorIgnore()
public interface BookMapper extends BaseMapper<Book> {
    // 参数按照顺序获取
    IPage<Book> selectPageVo(Page<?> page,String name,String name2,String name3);

    IPage<Book> selectPageVo2(Page<?> page,Book book);
}