package com.uprainsun.demo.generator.entity;

import java.util.List;

public class HotMessage {
    private int start_time;
    private int end_time;
    private List<Hotproduct> hotproductList;

    public HotMessage() {
    }

    public HotMessage(int start_time, int end_time, List<Hotproduct> hotproductList) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.hotproductList = hotproductList;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public List<Hotproduct> getHotproductList() {
        return hotproductList;
    }

    public void setHotproductList(List<Hotproduct> hotproductList) {
        this.hotproductList = hotproductList;
    }
}
