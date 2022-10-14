package com.yang.furniture.dao;

import com.yang.furniture.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 刘洋
 * @date 2022/6/5  12:38 PM
 */
public interface OrderItemDAO {
    int saveOrderItem(OrderItem orderItem);

    List<OrderItem> listOrderItemByOrderId(String orderId);

    int getOrderItemTotalCountByOrderId(String id);

    BigDecimal getOrderItemTotalPriceByOrderId(String id);
}
