package com.yang.furniture.entity;

import java.math.BigDecimal;

/**
 * @author 刘洋
 * @date 2022/5/25  9:31 PM
 */
public class Furniture {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Integer sales;
    private String imagePath = "assets/images/product-image/default.jpg";
    private String maker;

    public Furniture() {
    }

    public Furniture(Integer id, String name, BigDecimal price, Integer stock, Integer sales, String imagePath, String maker) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sales = sales;
        if (imagePath != null && ! imagePath.equals("")) {
            this.imagePath = imagePath;
        }

        this.maker = maker;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", sales=" + sales +
                ", imagePath='" + imagePath + '\'' +
                ", maker='" + maker + '\'' +
                '}';
    }
}
