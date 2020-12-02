package com.xiaomai.cloud.mapper.user;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomai.cloud.po.user.User;

/**
 * @author wangfeng
 * @date 2020/11/27
 */
@DS("slave")
public interface UserMapper extends BaseMapper<User> {
}
