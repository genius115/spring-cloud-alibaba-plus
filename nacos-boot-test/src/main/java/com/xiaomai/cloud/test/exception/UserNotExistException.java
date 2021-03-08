package com.xiaomai.cloud.test.exception;

/**
 * @author Madison
 * @date 2021/3/5
 */
public class UserNotExistException extends Exception {
    private static final long serialVersionUID = -2373528888948315963L;

    private Integer id;

    public UserNotExistException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "用户不存在";
    }
}
