package com.xiaomai.cloud.service.order;

import com.xiaomai.cloud.po.order.Payment;

import java.util.List;

/**
 * @author wangfeng
 * @date 2020/11/27
 */
public interface OrderService {
    List<Payment> listPayments();

    List<Payment> listPaymentsBySerial(String serial);
}
