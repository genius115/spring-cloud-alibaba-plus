package com.xiaomai.cloud.controller.order;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomai.cloud.mapper.order.SlaveOrderMapper;
import com.xiaomai.cloud.po.order.Payment;
import com.xiaomai.cloud.service.order.OrderService;
import com.xiaomai.cloud.service.order.OrderServiceImpl;
import com.xiaomai.cloud.service.order.SlaveOrderService;
import com.xiaomai.cloud.service.order.SlaveOrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangfeng
 * @date 2020/11/21
 */
@Api(value = "订单接口")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService OrderServiceImpl;

    @Autowired
    private SlaveOrderService slaveOrderServiceImpl;

    @ApiOperation(value = "订单列表", nickname = "订单")
    @GetMapping("/list")
    public List<Payment> listPayments() {
        return OrderServiceImpl.listPayments();
    }

    @ApiOperation(value = "依据serial订单列表", nickname = "依据serial订单")
    @GetMapping("/listbyserial")
    public List<Payment> listPaymentsBySerial(@RequestParam("serial") String serial) {
        return OrderServiceImpl.listPaymentsBySerial(serial);
    }

    //

    @ApiOperation(value = "分页查询订单列表", nickname = "分页订单")
    @GetMapping("/listpage")
    public Page<Payment> listPaymentsPage(@RequestParam("page") int pageNum,@RequestParam("size") int sizeNum) {
        return slaveOrderServiceImpl.listPaymentsPage(pageNum,sizeNum);
    }

    @ApiOperation(value = "数据迁移", nickname = "数据迁移")
    @GetMapping("/insert")
    public String paymentTableCopy() {
        return slaveOrderServiceImpl.paymentTableCopy();
    }

    @ApiOperation(value = "mybatis-plus事务", nickname = "事务")
    @GetMapping("/orderandpay")
    public String orderandpay() {
        return slaveOrderServiceImpl.orderandpay();
    }


    @ApiOperation(value = "新增订单", nickname = "新增")
    @GetMapping("/add")
    public String addPaymeny(@Validated Payment payment, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError);
            }
            return "fail";
        }
        return slaveOrderServiceImpl.addPaymeny(payment);
    }

    @ApiOperation(value = "删除订单", nickname = "删除")
    @GetMapping("/remove")
    public String removePaymeny(int id){
        return slaveOrderServiceImpl.removePaymeny(id);
    }




}
