package com.xiaomai.cloud.controller.order;

import com.xiaomai.cloud.po.order.Payment;
import com.xiaomai.cloud.service.order.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    private OrderServiceImpl OrderServiceImpl;

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
}
