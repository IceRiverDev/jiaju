package com.yang.furniture.service;

import com.yang.furniture.entity.Furniture;
import com.yang.furniture.entity.Page;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/5/25  9:51 PM
 */
public interface FurnitureService {
    List<Furniture> list();
    void addFurniture(Furniture furniture);

    Furniture showFurniture(int id);

    void deleteFurniture(int id);

    void updateFurniture(Furniture furniture);

    Furniture getFurnitureById(int id);
    Page<Furniture> page(int pageNumber, int pageSize);

    Page<Furniture> pageByName(int pageNumber, int pageSize, String name);
}
