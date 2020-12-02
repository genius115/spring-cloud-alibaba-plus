package com.xiaomai.cloud.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomai.cloud.mapper.user.UserMapper;
import com.xiaomai.cloud.po.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangfeng
 * @date 2020/11/27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String addUser(User user){
        boolean result = this.save(user);
        return result?"success ":"error "+"add "+user.toString();
    }

    @Override
    public String removeUser(String id){
        boolean  result = this.removeById(id);
        return result?"success ":"error  "+"remove -- " + id;
    }
}
