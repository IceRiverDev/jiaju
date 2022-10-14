package com.yang.furniture.test;

import com.yang.furniture.dao.OrderDAO;
import com.yang.furniture.dao.impl.OrderDAOImpl;
import com.yang.furniture.entity.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 刘洋
 * @date 2022/6/5  2:03 PM
 */
public class OrderDAOTest {
    OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void listOrderByMemberId() {
        System.out.println(orderDAO.listOrderByMemberId(1));
    }

    @Test
    public void saveOrder() {
        String orderId = System.currentTimeMillis() + "" + 1;
        int saveOrder = orderDAO.saveOrder(new Order(orderId, LocalDateTime.now(), new BigDecimal(3), "未发货", 1));
        if (saveOrder == 1) {
            System.out.println("订单保存成功");
        } else {
            System.out.println("订单保存失败");
        }
    }
}
