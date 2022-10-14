package com.yang.furniture.dao.impl;

import com.yang.furniture.dao.BasicDAO;
import com.yang.furniture.dao.OrderItemDAO;
import com.yang.furniture.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 刘洋
 * @date 2022/6/5  12:38 PM
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        return update("insert into order_item (id, name, price, count, total_price, order_id) values (?, ?, ?, ?, ?, ?)", orderItem.getId(), orderItem.getName()
                , orderItem.getPrice(), orderItem.getCount(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> listOrderItemByOrderId(String id) {
        return queryMulti("select name, price, count, total_price AS totalPrice from order_item where order_id = ?", OrderItem.class, id);
    }

    public int getOrderItemTotalCountByOrderId(String id) {
        return ((Number)queryScalar("select sum(count) from order_item where order_id = ?", id)).intValue();
    }

    public BigDecimal getOrderItemTotalPriceByOrderId(String id) {
        return ((BigDecimal)queryScalar("select sum(total_price) from order_item where order_id = ?", id));
    }
}
