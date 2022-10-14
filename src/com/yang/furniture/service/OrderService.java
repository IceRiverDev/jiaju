package com.yang.furniture.service;

import com.yang.furniture.entity.Cart;
import com.yang.furniture.entity.Order;
import com.yang.furniture.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 刘洋
 * @date 2022/6/5  2:42 PM
 */
public interface OrderService {
    Order saveOrder(Cart cart, int memberId);

    List<Order> listOrderByMemberId(int memberId);

    List<OrderItem> listOrderItemByOrderId(String orderId);

    int getTotalItemCount(String orderId);

    BigDecimal getTotalPrice(String orderId);

}
