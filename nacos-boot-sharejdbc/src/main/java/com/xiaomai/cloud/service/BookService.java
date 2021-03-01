package com.xiaomai.cloud.service;

import com.xiaomai.cloud.entity.Book;

import java.util.List;

/**
 * @author Madison
 * @date 2021/3/1
 */
public interface BookService {
    List<Book> getBookList();
    boolean save(Book book);
}
