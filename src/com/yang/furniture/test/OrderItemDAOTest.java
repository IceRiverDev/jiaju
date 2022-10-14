package com.yang.furniture.test;

import com.yang.furniture.dao.OrderItemDAO;
import com.yang.furniture.dao.impl.OrderItemDAOImpl;
import org.junit.jupiter.api.Test;

/**
 * @author 刘洋
 * @date 2022/6/5  2:03 PM
 */
public class OrderItemDAOTest {

    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Test
    public void listOrderItemByMemberId() {
        System.out.println(orderItemDAO.listOrderItemByOrderId("1"));
    }
}
