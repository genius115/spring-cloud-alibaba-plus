package com.xiaomai.xiaomaispringbootbase.Dao;

import com.xiaomai.xiaomaispringbootbase.Bean.Card;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Madison
 * @date 2022/6/21
 */
@Mapper
public interface CardMapper {
    Card queryCardById(int cardId);
}
