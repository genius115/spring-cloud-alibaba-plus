package com.xiaomai.xiaomaispringbootinit.compent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Madison
 * @date 2021/6/8
 */
@Component
@ConfigurationProperties(prefix = "demo")
//默认为8859-1
@PropertySource(value = "config.properties", encoding="UTF-8")
public class MyConfigDemoBean {

    private String phone;

    private List<String> interests;

    private List<Book> books;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
