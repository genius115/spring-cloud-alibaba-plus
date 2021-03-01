package com.xiaomai.cloud.rocket.order;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Madison
 * @date 2021/3/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStep {
    private Long orderId;
    private String desc;

    public static List<OrderStep> buildOrders(){
        List<OrderStep> list = new ArrayList<>();
        OrderStep orderStep = new OrderStep(1039L,"创建");
        list.add(orderStep);
        orderStep = new OrderStep(1039L,"付款");
        list.add(orderStep);
        orderStep = new OrderStep(1070L,"创建");
        list.add(orderStep);
        orderStep = new OrderStep(1039L,"推送");
        list.add(orderStep);
        orderStep = new OrderStep(1039L,"完成");
        list.add(orderStep);
        orderStep = new OrderStep(1070L,"付款");
        list.add(orderStep);
        orderStep = new OrderStep(1065L,"创建");
        list.add(orderStep);
        orderStep = new OrderStep(1065L,"付款");
        list.add(orderStep);
        orderStep = new OrderStep(1070L,"完成");
        list.add(orderStep);
        orderStep = new OrderStep(1065L,"推送");
        list.add(orderStep);
        orderStep = new OrderStep(1065L,"完成");
        list.add(orderStep);

        return list;
    }
}
