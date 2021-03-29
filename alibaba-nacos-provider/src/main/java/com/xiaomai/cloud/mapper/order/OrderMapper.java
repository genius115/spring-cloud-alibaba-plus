package com.xiaomai.cloud.mapper.order;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomai.cloud.po.order.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangfeng
 * @date 2020/11/21
 */
@DS("master")
// @Mapper
@Repository
public interface OrderMapper extends BaseMapper<Payment> {
    List<Payment> paymentBySerial(@Param("serial")String serial);
}