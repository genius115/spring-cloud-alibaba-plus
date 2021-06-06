package com.xiaomai.boot.xiaomaispringbootpagehelper.service.impl;

/**
 * @author Madison
 * @date 2021/5/25
 */
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaomai.boot.xiaomaispringbootpagehelper.entity.OrderPresentInfo;
import com.xiaomai.boot.xiaomaispringbootpagehelper.mapper.OrderPresentInfoMapper;
import com.xiaomai.boot.xiaomaispringbootpagehelper.service.OrderPresentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPresentInfoServiceImpl implements OrderPresentInfoService {


    @Autowired
    private OrderPresentInfoMapper orderPresentMapper;

    @Override
    public List<OrderPresentInfo> getAllOrderPresent() {
        return orderPresentMapper.getAllOrderPresent();
    }

    @Override
    public PageInfo<OrderPresentInfo> getAllOrderPresentForPage(int pageNo, int pageSize) {

        PageHelper.startPage(pageNo,pageSize);
        List<OrderPresentInfo> allOrderPresentList = orderPresentMapper.getAllOrderPresent();
        PageInfo<OrderPresentInfo> pageInfo = new PageInfo<>(allOrderPresentList);
        return pageInfo;
    }
}