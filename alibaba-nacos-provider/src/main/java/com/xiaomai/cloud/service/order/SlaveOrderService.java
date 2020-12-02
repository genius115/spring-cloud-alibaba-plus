package com.xiaomai.cloud.service.order;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomai.cloud.po.order.Payment;

import java.util.List;

/**
 * @author wangfeng
 * @date 2020/11/27
 */
public interface SlaveOrderService {

    List<Payment> listPayments();

    Page<Payment> listPaymentsPage(int page,int size);

    List<Payment> listPaymentsBySerial(String serial);

    String paymentTableCopy();

    String orderandpay();

    String addPaymeny(Payment payment);

    String removePaymeny(int id);


}
