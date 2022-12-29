package com.example.myshop.bean;

public class LogisticsInfoBean {
    private String acceptstation;
    private String accepttime;

    public String getAcceptstation() {
        return acceptstation;
    }

    public LogisticsInfoBean(String acceptstation, String accepttime) {
        this.acceptstation = acceptstation;
        this.accepttime = accepttime;
    }

    public String getAccepttime() {
        return accepttime;
    }

    public LogisticsInfoBean setAcceptstation(String acceptstation) {
        this.acceptstation = acceptstation;
        return this;
    }

    public LogisticsInfoBean setAccepttime(String accepttime) {
        this.accepttime = accepttime;
        return this;
    }

}
