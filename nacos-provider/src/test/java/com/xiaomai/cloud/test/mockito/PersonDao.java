package com.xiaomai.cloud.test.mockito;

/**
 * @author Madison
 * @date 2021/1/27
 */
public interface PersonDao {
    Person getPerson(int id);
    boolean update(Person person);
}

