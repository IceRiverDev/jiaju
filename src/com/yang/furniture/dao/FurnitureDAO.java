package com.yang.furniture.dao;

import com.yang.furniture.entity.Furniture;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/5/25  9:34 PM
 */
public interface FurnitureDAO {
    List<Furniture> queryFurniture();

    int addFurniture(Furniture furniture);

    int deleteFurnitureById(int id);

    int updateFurniture(Furniture furniture);

    Furniture queryFurnitureById(int id);

    int getTotalRow();

    List<Furniture> getPageItems(int begin, int pagesize);

    int getTotalRowByName(String furnitureName);

    List<Furniture> getPageItemsByName(int begin, int pagesize, String furnitureName);
}
