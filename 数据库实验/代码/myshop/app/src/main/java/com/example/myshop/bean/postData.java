package com.example.myshop.bean;

import android.content.Intent;

import java.util.List;

public class postData {
    private comment com;
    private int reply_num;
    private List<reply> replyList;

    public postData() {

    }

    public postData(comment com, int reply_num, List<reply> replyList) {
        this.com = com;
        this.reply_num = reply_num;
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "postData{" +
                "com=" + com +
                ", reply_num=" + reply_num +
                ", replyList=" + replyList +
                '}';
    }

    public comment getCom() {
        return com;
    }

    public void setCom(comment com) {
        this.com = com;
    }

    public int getReply_num() {
        return reply_num;
    }

    public void setReply_num(int reply_num) {
        this.reply_num = reply_num;
    }

    public List<reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<reply> replyList) {
        this.replyList = replyList;
    }
}
