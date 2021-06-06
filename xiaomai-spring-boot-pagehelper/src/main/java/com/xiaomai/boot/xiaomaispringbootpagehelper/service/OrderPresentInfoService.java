package com.xiaomai.boot.xiaomaispringbootpagehelper.service;

/**
 * @author Madison
 * @date 2021/5/25
 */
import com.github.pagehelper.PageInfo;
import com.xiaomai.boot.xiaomaispringbootpagehelper.entity.OrderPresentInfo;

import java.util.List;

public interface OrderPresentInfoService {
    List<OrderPresentInfo> getAllOrderPresent();

    PageInfo<OrderPresentInfo> getAllOrderPresentForPage(int pageNo, int pageSize);
}