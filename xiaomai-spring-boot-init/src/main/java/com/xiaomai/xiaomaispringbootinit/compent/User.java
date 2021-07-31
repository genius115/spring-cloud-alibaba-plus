package com.xiaomai.xiaomaispringbootinit.compent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Madison
 * @date 2021/6/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String id;
    private String name;
    private Integer age;
    private Dog dog;
}
