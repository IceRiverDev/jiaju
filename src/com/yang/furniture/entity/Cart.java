package com.yang.furniture.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 刘洋
 * @date 2022/6/4  7:49 AM
 */
public class Cart {
    private final HashMap<Integer, CartItem> items = new HashMap<>();
    private int totalCount = 0;

    private BigDecimal totalPrice = new BigDecimal(0);

    /**
     * 添加商品，如果商品已经存在，则更新现有商品信息。否则直接添加商品，并更新商品总价
     *
     * @param cartItem 购物车商品
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item != null) {
            item.setTotalPrice(cartItem.getPrice().add(item.getTotalPrice()));
            item.setCount(item.getCount() + 1);
        } else {
            items.put(cartItem.getId(), cartItem);
        }
    }

    /**
     * 减少购物车某个商品的数量
     *
     * @param id 购物车商品id
     */
    public void deleteItem(int id) {
        CartItem item = items.get(id);
        if (item != null) {
            items.remove(id);
        }
    }

    /**
     * 清空购物车所有商品
     */
    public void clear() {
        items.clear();
    }

    /**
     * @return 购物车商品总价
     */
    public BigDecimal getTotalPrice() {
        totalPrice = new BigDecimal(0);
        Set<Map.Entry<Integer, CartItem>> entries = items.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    /**
     * @return 购物车商品总数量
     */
    public int getTotalCount() {
        totalCount = 0;
        Set<Map.Entry<Integer, CartItem>> entries = items.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }


    public void updateCount(int id, int count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    public Boolean isEmpty() {
        return items.isEmpty();
    }
    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + items +
                '}';
    }
}
