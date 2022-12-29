package com.uprainsun.demo.generator.controller;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uprainsun.demo.generator.entity.*;
import com.uprainsun.demo.generator.mapper.BannerproductMapper;
import com.uprainsun.demo.generator.mapper.HotproductMapper;
import com.uprainsun.demo.generator.mapper.RecommendproductMapper;
import com.uprainsun.demo.generator.mapper.SeckillproductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/generator/allproduct")
public class AllproductController {
    @Autowired
    public BannerproductMapper bannerproductMapper;
    @Autowired
    public SeckillproductMapper seckillproductMapper;
    @Autowired
    public RecommendproductMapper recommendproductMapper;
    @Autowired
    public HotproductMapper hotproductMapper;
    public List<Bannerproduct> bannerproductList;
    public List<Seckillproduct> seckillproductList;
    public List<Recommendproduct> recommendproductList;
    public HotMessage hotMessage = new HotMessage();
    public List<Hotproduct> hotproductList;
    @GetMapping("/getHomeData")
    public RESULT getHomeData() {
        RESULT result = new RESULT();
        bannerproductList = bannerproductMapper.selectList(null);
        seckillproductList = seckillproductMapper.selectList(null);
        recommendproductList = recommendproductMapper.selectList(null);
        hotproductList = hotproductMapper.selectList(null);
        hotMessage.setStart_time(0);
        hotMessage.setEnd_time(3600);
        hotMessage.setHotproductList(hotproductList);
        HomeData homeData = new HomeData(bannerproductList, seckillproductList, recommendproductList, hotMessage);
        if(bannerproductList.isEmpty() || seckillproductList.isEmpty() || recommendproductList.isEmpty() || hotproductList.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("请求数据失败");
            result.setDetails(null);
        }
        else {
            result.setSuccess(true);
            result.setMsg("请求数据成功");
            result.setDetails(homeData);
        }
        return result;
    }
}

