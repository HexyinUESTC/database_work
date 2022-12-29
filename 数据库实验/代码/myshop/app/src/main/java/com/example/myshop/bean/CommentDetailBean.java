package com.example.myshop.bean;

import java.util.List;

/**
 * Created by moos on 2018/4/20.
 */

public class CommentDetailBean {
    private comment com;
    private List<reply> replyList;

    public CommentDetailBean() {
    }

    public CommentDetailBean(comment com, List<reply> replyList) {
        this.com = com;
        this.replyList = replyList;
    }

    public comment getCom() {
        return com;
    }

    public void setCom(comment com) {
        this.com = com;
    }

    public void setReplyList(List<reply> replyList) {
        this.replyList = replyList;
    }
    public List<reply> getReplyList() {
        return replyList;
    }
}
