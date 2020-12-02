package com.xiaomai.cloud.service.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomai.cloud.mapper.order.OrderMapper;
import com.xiaomai.cloud.mapper.order.SlaveOrderMapper;
import com.xiaomai.cloud.po.order.Payment;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wangfeng
 * @date 2020/11/21
 */
@Service
public class SlaveOrderServiceImpl extends ServiceImpl<SlaveOrderMapper, Payment> implements SlaveOrderService{

    @Autowired
    private SlaveOrderMapper slaveOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Payment> listPayments() {
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<Payment>();
        return slaveOrderMapper.selectList(queryWrapper);
    }

    @Override
    public  Page<Payment> listPaymentsPage(int pageNum,int sizeNum) {
        Page<Payment> page = new Page<>(pageNum,sizeNum);
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<Payment>();

        Page<Payment> list  = slaveOrderMapper.selectPage(page, null);
        return list;
    }

    @Override
    public List<Payment> listPaymentsBySerial(String serial) {
        return slaveOrderMapper.paymentBySerial(serial);
    }


    @Override
    public String paymentTableCopy() {
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<Payment>();
        List<Payment> list = orderMapper.selectList(queryWrapper);

        slaveOrderMapper.delete(queryWrapper);
        for (int i = 0; i < list.size(); i++) {
            slaveOrderMapper.insert(list.get(i));
        }

        return System.currentTimeMillis() + "---success!!";
    }

    /**
     *多数据源时，事务对默认数据源产生事务
     */
    @Transactional
    @Override
    public String orderandpay() {
        int result1 = slaveOrderMapper.deleteById("31");
        System.out.println("result1:" + result1);

        Payment payment = new Payment();
        payment.setId((int) Math.random() * 100);
        payment.setSerial(System.currentTimeMillis() + "");

        if (result1 == 1) {
            throw new RuntimeException();
        }

        int result2 = slaveOrderMapper.insert(payment);
        System.out.println("result2:" + result2);

        return result1 + "---" + result2;
    }

    @Override
    public String addPaymeny(Payment payment){
        // boolean result = this.slaveOrderMapper.insert(payment)==1?true:false;
        boolean result = this.save(payment);
        return result?"success ":"error "+"add "+payment.toString();
    }

    @Override
    public String removePaymeny(int id){
        boolean  result = this.removeById(id);
        return result?"success ":"error  "+"remove -- " + id;
    }
}
