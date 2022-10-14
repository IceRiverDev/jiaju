package com.yang.furniture.service.Impl;

import com.yang.furniture.dao.FurnitureDAO;
import com.yang.furniture.dao.impl.FurnitureDAOImpl;
import com.yang.furniture.service.CartService;

/**
 * @author 刘洋
 * @date 2022/6/7  11:46 PM
 */
public class CartServiceImpl implements CartService {
    private final FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    @Override
    public boolean isStockEnough(int itemId) {
        Integer stock = furnitureDAO.queryFurnitureById(itemId).getStock();
        return stock - 1 >= 0;
    }
}
