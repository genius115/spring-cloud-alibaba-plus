package com.xiaomai.xiaomaispringbootbase.Bean;

import lombok.Data;

import java.util.List;

/**
 * @author Madison
 * @date 2022/6/21
 */
@Data
public class User {
    private Integer userId;
    private String userName;
    private Integer age;
    private Card card;
    private List<MobilePhone> mobilePhone;
}