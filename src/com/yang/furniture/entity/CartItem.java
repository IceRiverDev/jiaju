package com.yang.furniture.entity;

import java.math.BigDecimal;

/**
 * @author 刘洋
 * @date 2022/6/4  7:40 AM
 */
public class CartItem {
    private Integer id;
    private BigDecimal price;
    private Integer count;
    private String name;
    private BigDecimal totalPrice;

    private String imagePath;


    public CartItem(Integer id, BigDecimal price, Integer count, String name, BigDecimal totalPrice, String imagePath) {
        this.id = id;
        this.price = price;
        this.count = count;
        this.name = name;
        this.totalPrice = totalPrice;
        this.imagePath = imagePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", price=" + price +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
