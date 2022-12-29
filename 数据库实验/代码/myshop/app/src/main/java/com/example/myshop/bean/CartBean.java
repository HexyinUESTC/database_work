package com.example.myshop.bean;

import java.time.LocalDateTime;
import java.util.Date;

public class CartBean {
    private Integer id;

    private Integer productId;

    private Integer quantity;

    private Integer cost;

    private Integer userId;

    private String createTime;

    private String updateTime;

    public CartBean() {
    }

    public CartBean(Integer id, Integer productId, Integer quantity, Integer cost, Integer userId, String createTime, String updateTime) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
