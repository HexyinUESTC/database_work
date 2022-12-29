package com.uprainsun.demo.generator.entity;

import java.io.Serializable;
import java.util.List;

public class commentDetail implements Serializable {
    private static final long serialVersionUID=1L;
    private comment com;
    private Integer reply_num;
    private List<reply> replyList;

    public commentDetail() {

    }

    public comment getCom() {
        return com;
    }

    public void setCom(comment com) {
        this.com = com;
    }

    public Integer getReply_num() {
        return reply_num;
    }

    public void setReply_num(Integer reply_num) {
        this.reply_num = reply_num;
    }

    public List<reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "commentDetail{" +
                "com=" + com +
                ", reply_num=" + reply_num +
                ", replyList=" + replyList +
                '}';
    }
}
