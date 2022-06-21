package com.xiaomai.xiaomaispringbootbase.service;

import com.xiaomai.xiaomaispringbootbase.Bean.User;
import com.xiaomai.xiaomaispringbootbase.Dao.CardMapper;
import com.xiaomai.xiaomaispringbootbase.Dao.TestMapper;
import com.xiaomai.xiaomaispringbootbase.Dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Madison
 * @date 2022/6/21
 */
@Service
public class TestService {
    @Autowired
    public TestMapper testMapper;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public CardMapper cardMapper;
    
    
    public String querybook(){
        return testMapper.querybook();
    }


    //事务会被Spring管理
    @Transactional
    public User queryById(int userId){        
        return userMapper.queryById(userId);
    }

    //非事务不会被Spring管理
    public User queryPhoneById(int userId){
        return userMapper.queryPhoneById(userId);
    }
}
