package com.yang.furniture.dao;

import com.yang.furniture.entity.Order;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/6/5  12:37 PM
 */
public interface OrderDAO {
    int saveOrder(Order order);
    List<Order> listOrderByMemberId(int memberId);
}
