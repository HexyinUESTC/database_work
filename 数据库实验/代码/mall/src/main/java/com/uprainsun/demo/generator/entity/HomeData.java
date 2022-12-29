package com.uprainsun.demo.generator.entity;

import java.util.List;

public class HomeData {
    private List<Bannerproduct> bannerproductList;
    private List<Seckillproduct> seckillproductList;
    private List<Recommendproduct> recommendproductList;
    private HotMessage hotMessage;

    public HomeData() {
    }

    public HomeData(List<Bannerproduct> bannerproductList, List<Seckillproduct> seckillproductList, List<Recommendproduct> recommendproductList, HotMessage hotMessage) {
        this.bannerproductList = bannerproductList;
        this.seckillproductList = seckillproductList;
        this.recommendproductList = recommendproductList;
        this.hotMessage = hotMessage;
    }

    public List<Bannerproduct> getBannerproductList() {
        return bannerproductList;
    }

    public void setBannerproductList(List<Bannerproduct> bannerproductList) {
        this.bannerproductList = bannerproductList;
    }

    public List<Seckillproduct> getSeckillproductList() {
        return seckillproductList;
    }

    public void setSeckillproductList(List<Seckillproduct> seckillproductList) {
        this.seckillproductList = seckillproductList;
    }

    public List<Recommendproduct> getRecommendproductList() {
        return recommendproductList;
    }

    public void setRecommendproductList(List<Recommendproduct> recommendproductList) {
        this.recommendproductList = recommendproductList;
    }

    public HotMessage getHotproduct() {
        return hotMessage;
    }

    public void setHotproduct(HotMessage hotMessage) {
        this.hotMessage = hotMessage;
    }
}
