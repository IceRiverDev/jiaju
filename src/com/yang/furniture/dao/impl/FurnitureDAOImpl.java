package com.yang.furniture.dao.impl;

import com.yang.furniture.dao.BasicDAO;
import com.yang.furniture.dao.FurnitureDAO;
import com.yang.furniture.entity.Furniture;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/5/25  9:35 PM
 */
public class FurnitureDAOImpl extends BasicDAO<Furniture> implements FurnitureDAO {

    @Override
    public List<Furniture> queryFurniture() {
        return queryMulti("select id, name, price, stock, sales, maker, image_path as imagePath from furniture order by stock desc", Furniture.class);
    }

    @Override
    public int addFurniture(Furniture furniture) {
        return update("insert into furniture (name, price, stock, sales, image_path, maker) values(?, ?, ?, ?, ?, ?)",
                furniture.getName(), furniture.getPrice(), furniture.getStock(), furniture.getSales(), furniture.getImagePath(), furniture.getMaker());
    }

    @Override
    public int deleteFurnitureById(int id) {
        return update("delete from furniture where id = ?", id);
    }

    /*
    家居名 	商家 	价格 	销量 	库存
     */
    @Override
    public int updateFurniture(Furniture furniture) {
        return update("update furniture set name = ?, maker = ?, price = ?, sales = ?, stock = ?, image_path = ? where id = ?",
                furniture.getName(), furniture.getMaker(), furniture.getPrice(), furniture.getSales(), furniture.getStock(), furniture.getImagePath(), furniture.getId());
    }

    @Override
    public Furniture queryFurnitureById(int id) {
        return querySingle("select id, name, price, stock, sales, maker, image_path as imagePath from furniture where id = ?", Furniture.class, id);
    }

    @Override
    public int getTotalRow() {
        Object totalRow = queryScalar("select count(*) from furniture");
        return ((Number) totalRow).intValue();
    }

    @Override
    public List<Furniture> getPageItems(int begin, int pagesize) {
        return queryMulti("select id, name, price, stock, sales, maker, image_path as imagePath from furniture limit ?, ?", Furniture.class, begin, pagesize);
    }

    @Override
    public int getTotalRowByName(String furnitureName) {
        Object totalRow = queryScalar("select count(*) from furniture where name LIKE ?", "%" + furnitureName + "%");
        return ((Number) totalRow).intValue();
    }

    @Override
    public List<Furniture> getPageItemsByName(int begin, int pagesize, String furnitureName) {
        return queryMulti("select  id, name, price, stock, sales, maker, image_path as imagePath from furniture where name LIKE ? order by stock desc limit ?, ? ", Furniture.class, "%" + furnitureName + "%", begin, pagesize);
    }
}
