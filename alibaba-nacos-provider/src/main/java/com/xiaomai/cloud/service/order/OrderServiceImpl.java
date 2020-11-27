package com.xiaomai.cloud.service.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomai.cloud.mapper.order.OrderMapper;
import com.xiaomai.cloud.po.order.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangfeng
 * @date 2020/11/21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Payment> implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Payment> listPayments() {
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<Payment>();
        return orderMapper.selectList(queryWrapper);
    }

    @Override
    public List<Payment> listPaymentsBySerial(String serial) {
        return orderMapper.paymentBySerial(serial);
    }

}
