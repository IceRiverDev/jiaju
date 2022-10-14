package com.yang.furniture.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author 刘洋
 * @date 2022/6/5  12:46 PM
 */
public class Order {
    private String id;
    private LocalDateTime createTime;
    private BigDecimal price;
    private String status;
    private Integer memberId;

    public Order() {
    }

    public Order(String id, LocalDateTime createTime, BigDecimal price, String status, Integer memberId) {
        this.id = id;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.memberId = memberId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}
