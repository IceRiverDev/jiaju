package com.yang.furniture.service.Impl;

import com.yang.furniture.dao.FurnitureDAO;
import com.yang.furniture.dao.impl.FurnitureDAOImpl;
import com.yang.furniture.entity.Furniture;
import com.yang.furniture.entity.Page;
import com.yang.furniture.service.FurnitureService;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/5/25  9:51 PM
 */
public class FurnitureServiceImpl implements FurnitureService {
    private final FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    @Override
    public List<Furniture> list() {
        return furnitureDAO.queryFurniture();
    }

    @Override
    public void addFurniture(Furniture furniture) {
        furnitureDAO.addFurniture(furniture);
    }

    @Override
    public Furniture showFurniture(int id) {
        return furnitureDAO.queryFurnitureById(id);
    }

    @Override
    public void deleteFurniture(int id) {
        furnitureDAO.deleteFurnitureById(id);
    }

    @Override
    public void updateFurniture(Furniture furniture) {
        furnitureDAO.updateFurniture(furniture);
    }

    @Override
    public Furniture getFurnitureById(int id) {
        return furnitureDAO.queryFurnitureById(id);
    }

    @Override
    public Page<Furniture> page(int pageNumber, int pageSize) {
        Page<Furniture> page = new Page<>();
        page.setPagerNumber(pageNumber);
        page.setPageSize(pageSize);
        int totalRow = furnitureDAO.getTotalRow();

        page.setPageTotalCount((int) Math.ceil((double) totalRow / (double) pageSize));

        page.setItems(furnitureDAO.getPageItems((pageNumber - 1) * pageSize, pageSize));
        page.setTotalRow(totalRow);
        return page;
    }

    @Override
    public Page<Furniture> pageByName(int pageNumber, int pageSize, String name) {
        Page<Furniture> page = new Page<>();
        page.setPagerNumber(pageNumber);
        page.setPageSize(pageSize);
        int totalRow = furnitureDAO.getTotalRowByName(name);
        page.setPageTotalCount((int) Math.ceil((double) totalRow / (double) pageSize));

        page.setItems(furnitureDAO.getPageItemsByName((pageNumber - 1) * pageSize, pageSize, name));
        page.setTotalRow(totalRow);
        return page;
    }
}
