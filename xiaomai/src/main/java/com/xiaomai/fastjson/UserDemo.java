package com.xiaomai.fastjson;

/**
 * @author Madison
 * @date 2021/5/13
 */
public class UserDemo {
    private String id;
    private String name;

    public String getIdname() {
        return id+name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
