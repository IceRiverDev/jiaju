package com.yang.furniture.test;

import com.yang.furniture.dao.FurnitureDAO;
import com.yang.furniture.dao.impl.FurnitureDAOImpl;
import com.yang.furniture.entity.Furniture;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author 刘洋
 * @date 2022/5/25  9:40 PM
 */
public class FurnitureDAOTest {
    private final FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    @Test
    public void queryFurniture() {
        List<Furniture> furnitures = furnitureDAO.queryFurniture();
        for (Furniture furniture: furnitures) {
            System.out.println(furniture);
        }
    }

    @Test
    public void addFurniture() {
        int rows = furnitureDAO.addFurniture(new Furniture(null, "桌子", BigDecimal.valueOf(113.2), 23, 45, null, "美国"));
        if (rows == 1) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Test
    public void getPageItems() {
        System.out.println(furnitureDAO.getPageItems((2 - 1) * 3, 3));
    }

    @Test
    public void getPageItemsByName() {
        System.out.println(furnitureDAO.getPageItemsByName(0, 5, "中国沙发"));
    }

    @Test
    public void getTotalRowByName() {
        System.out.println(furnitureDAO.getTotalRowByName("中国沙发"));
    }

}
