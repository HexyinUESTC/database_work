package com.example.myshop.bean;

import java.io.Serializable;
import java.util.Date;

public class reply implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer talkId;
    private Integer commentId;
    private String userName;
    private String avator;
    private Integer productId;
    private String content;
    private String createTime;
    private  String updateTime;

    public reply() {
    }

    public reply(Integer id, Integer userId, Integer talkId, Integer commentId, String userName, String avator, Integer productId, String content, String createTime, String updateTime) {
        this.id = id;
        this.userId = userId;
        this.talkId = talkId;
        this.commentId = commentId;
        this.userName = userName;
        this.avator = avator;
        this.productId = productId;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    @Override
    public String toString() {
        return "reply{" +
                "id=" + id +
                ", userId=" + userId +
                ", talkId=" + talkId +
                ", commentId=" + commentId +
                ", userName='" + userName + '\'' +
                ", avator='" + avator + '\'' +
                ", productId=" + productId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
