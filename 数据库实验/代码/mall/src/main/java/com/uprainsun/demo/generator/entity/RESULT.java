package com.uprainsun.demo.generator.entity;

public class RESULT<T> {
    private String msg;
    private Boolean success;
    private T details;

    public RESULT() {
    }

    public RESULT(String msg, Boolean success, T details) {
        this.msg = msg;
        this.success = success;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", details=" + details +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getDetails() {
        return details;
    }

    public void setDetails(T details) {
        this.details = details;
    }
}

