package com.example.myshop.bean;

import java.io.Serializable;
import java.util.Date;

public class comment implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer talkId;
    private String userName;
    private String avator;
    private Integer productId;
    private Integer likeNum;
    private Integer isLike;
    private String content;
    private String createTime;
    private  String updateTime;

    public comment() {

    }

    public comment(Integer id, Integer userId, Integer talkId, String userName, String avator, Integer productId, Integer likeNum, Integer isLike, String content, String createTime, String updateTime) {
        this.id = id;
        this.userId = userId;
        this.talkId = talkId;
        this.userName = userName;
        this.avator = avator;
        this.productId = productId;
        this.likeNum = likeNum;
        this.isLike = isLike;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", talkId=" + talkId +
                ", userName='" + userName + '\'' +
                ", avator='" + avator + '\'' +
                ", productId=" + productId +
                ", likeNum=" + likeNum +
                ", isLike=" + isLike +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTalkId() {
        return talkId;
    }

    public void setTalkId(Integer talkId) {
        this.talkId = talkId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getIsLike() {
        return isLike;
    }

    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
