package com.yang.furniture.dao.impl;

import com.yang.furniture.dao.BasicDAO;
import com.yang.furniture.dao.OrderDAO;
import com.yang.furniture.entity.Order;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/6/5  12:38 PM
 */

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        return update("insert into `order` (id, create_time, price, status, member_id) values (?, ?, ?, ?, ?)", order.getId()
                , order.getCreateTime(), order.getPrice(), order.getStatus(), order.getMemberId());
    }

    @Override
    public List<Order> listOrderByMemberId(int memberId) {
        return queryMulti("select id, create_time AS createTime, price, status, member_id AS memberId from `order` where member_id = ?", Order.class, memberId);
    }
}
