package com.xiaomai.cloud.service.postgres;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomai.cloud.mapper.postgres.BookMapper;
import com.xiaomai.cloud.po.postgres.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author developer
 * @date 2020/12/31
 */
@Slf4j
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> {

    @Autowired
    private BookMapper bookMapper;

    @Transactional
    public List<Book> selectAllBook(Integer pageNum,Integer sizeNum){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        // queryWrapper.lt(true,"id",5);
        queryWrapper.orderByAsc("id");

        Page<Book> page = new Page<>();
        page.setCurrent(pageNum==null?1:pageNum);
        page.setSize(sizeNum==null?10:sizeNum);

        IPage<Book> pages = this.bookMapper.selectPage(page, queryWrapper);
        List<Book> list = pages.getRecords();
        return list;
    }

    public List<Book> selectBookPageByName(Integer pageNum,Integer sizeNum,String name){
        Page<Book> page = new Page<>();
        page.setCurrent(pageNum==null?1:pageNum);
        page.setSize(sizeNum==null?10:sizeNum);
        IPage<Book> pages = this.bookMapper.selectPageVo(page,name,"%学%","数");
        List<Book> list = pages.getRecords();
        return list;
    }

    public List<Book> selectBookPageByCodeORName(Integer pageNum,Integer sizeNum,String code,String name){
        Page<Book> page = new Page<>();
        page.setCurrent(pageNum==null?1:pageNum);
        page.setSize(sizeNum==null?10:sizeNum);
        Book book = new Book();
        book.setCode(code);
        book.setName(name);
        IPage<Book> pages = this.bookMapper.selectPageVo2(page,book);
        List<Book> list = pages.getRecords();
        return list;
    }
}
