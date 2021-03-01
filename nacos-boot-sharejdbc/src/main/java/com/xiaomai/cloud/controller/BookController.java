package com.xiaomai.cloud.controller;

import com.xiaomai.cloud.entity.Book;
import com.xiaomai.cloud.service.BookService;
import com.xiaomai.cloud.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Madison
 * @date 2021/3/1
 */
@RestController
public class BookController {

    @Autowired
    BookService BookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<Book> getItems(){
        return BookService.getBookList();
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public Boolean saveItem(Book book){
        return BookService.save(book);
    }
}