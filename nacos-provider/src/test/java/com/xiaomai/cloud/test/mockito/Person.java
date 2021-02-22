package com.xiaomai.cloud.test.mockito;

/**
 * @author Madison
 * @date 2021/1/27
 */
public class Person {
    private final int id;
    private final String name;
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
