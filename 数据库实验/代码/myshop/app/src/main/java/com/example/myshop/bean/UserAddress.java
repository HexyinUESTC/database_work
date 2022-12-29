package com.example.myshop.bean;

import android.content.Intent;

import java.io.Serializable;
import java.util.Date;

public class UserAddress implements Serializable {
    private Integer id;
    private Integer userId;
    /**
     * 买家地址
     */
    private String address;
    private String remark;
    private Integer isdefault;

    /**
     * 修改时间
     */
    private String createTime;

    /**
     * 创建时间
     */
    private String updateTime;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 地址编码
     */
    private String areaCode;

    public UserAddress(Integer id, Integer userId, String address, String remark, Integer isDefault, String createTime, String updateTime, String buyerPhone, String buyerName, String areaCode) {
        id = id;
        this.userId = userId;
        this.address = address;
        this.remark = remark;
        this.isdefault = isDefault;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.buyerPhone = buyerPhone;
        this.buyerName = buyerName;
        this.areaCode = areaCode;
    }

    public UserAddress() {

    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", isdefault=" + isdefault +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", buyerPhone='" + buyerPhone + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDefault() {
        return isdefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isdefault = isDefault;
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

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
