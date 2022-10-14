package com.yang.furniture.service.Impl;

import com.yang.furniture.dao.FurnitureDAO;
import com.yang.furniture.dao.OrderDAO;
import com.yang.furniture.dao.OrderItemDAO;
import com.yang.furniture.dao.impl.FurnitureDAOImpl;
import com.yang.furniture.dao.impl.OrderDAOImpl;
import com.yang.furniture.dao.impl.OrderItemDAOImpl;
import com.yang.furniture.entity.*;
import com.yang.furniture.service.OrderService;
import com.yang.furniture.utils.DataUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author 刘洋
 * @date 2022/6/5  2:42 PM
 */
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    private final FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    @Override
    public Order saveOrder(Cart cart, int memberId) {
        String orderId = DataUtils.orderIdGenerator(memberId);
        Order order = new Order(orderId, LocalDateTime.now(), cart.getTotalPrice(), "未发货", memberId);
        orderDAO.saveOrder(order);

        HashMap<Integer, CartItem> items = cart.getItems();
        Set<Integer> keySet = items.keySet();
        for (Integer key : keySet) {
            CartItem cartItem = items.get(key);
            Furniture furniture = furnitureDAO.queryFurnitureById(cartItem.getId());
            Integer stock = furniture.getStock();

            // 保存订单
            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(), Math.min(stock, cartItem.getCount()), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDAO.saveOrderItem(orderItem);

            // 设置销量
            furniture.setSales(furniture.getSales() + Math.min(stock, cartItem.getCount()));

            // 设置库存
            int restStock = stock - orderItem.getCount();
            furniture.setStock(Math.max(restStock, 0));
            furnitureDAO.updateFurniture(furniture);
        }

        cart.clear();
        return order;
    }

    @Override
    public List<Order> listOrderByMemberId(int memberId) {
        return orderDAO.listOrderByMemberId(memberId);
    }

    @Override
    public List<OrderItem> listOrderItemByOrderId(String orderId) {
        return orderItemDAO.listOrderItemByOrderId(orderId);
    }

    @Override
    public int getTotalItemCount(String orderId) {
        return orderItemDAO.getOrderItemTotalCountByOrderId(orderId);
    }

    @Override
    public BigDecimal getTotalPrice(String orderId) {
        return orderItemDAO.getOrderItemTotalPriceByOrderId(orderId);
    }
}
