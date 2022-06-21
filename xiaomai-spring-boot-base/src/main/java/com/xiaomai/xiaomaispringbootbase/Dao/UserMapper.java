package com.xiaomai.xiaomaispringbootbase.Dao;

import com.xiaomai.xiaomaispringbootbase.Bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Madison
 * @date 2022/6/21
 */
@Mapper
public interface UserMapper {
        /**
         * 通过userId查询user信息
         * @param userId
         * @return
         */
        User queryById(int userId);

        User queryPhoneById(int userId);
    
}
