package com.xiaomai.cloud.service.user;

import com.xiaomai.cloud.po.user.User;

/**
 * @author wangfeng
 * @date 2020/11/27
 */
public interface UserService {
    String addUser(User user);
    String removeUser(String id);
}
