package com.uprainsun.demo.generator.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uprainsun.demo.generator.entity.RESULT;
import com.uprainsun.demo.generator.entity.UserAddress;
import com.uprainsun.demo.generator.mapper.UserAddressMapper;
import com.uprainsun.demo.generator.service.UserAddressService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/generator/userAddress")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("/getAllAddress")
    public RESULT getAllAddress(@RequestParam String userId) {
        List<UserAddress> userAddressList = userAddressService.getAllAddressByUserId(Integer.valueOf(userId));
        RESULT result = new RESULT();
        result.setSuccess(true);
        result.setMsg("请求成功");
        result.setDetails(userAddressList);
        return result;
    }

    @PostMapping("/addAddress")
    public RESULT addAddress(@RequestBody UserAddress userAddress) {
        RESULT result = new RESULT();
        result.setMsg("添加失败!");
        result.setSuccess(false);
        Map<String, Object> map = userAddressService.addAddress(userAddress);
        if(Integer.parseInt(map.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("添加成功!");
            result.setDetails(userAddress);
        }
        else {
            result.setSuccess(false);
            result.setMsg("添加失败!");
            result.setDetails(userAddress);
        }
        
        return result;
    }

    @PostMapping("/updateAddress")
    public RESULT updateAddress(@RequestBody UserAddress userAddress) {
        RESULT result = new RESULT();
        result.setMsg("更新失败!");
        result.setSuccess(false);
        Map<String, Object> map = userAddressService.updateAddress(userAddress);
        if(Integer.parseInt(map.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("更新成功!");
            result.setDetails(userAddress);
        }
        else {
            result.setSuccess(false);
            result.setMsg("更新失败!");
            result.setDetails(userAddress);
        }
        return result;
    }

    @PostMapping("/deleteAddress")
    public RESULT deleteAddress(@RequestBody UserAddress userAddress) {
        RESULT result = new RESULT();
        result.setMsg("删除失败!");
        result.setSuccess(false);
        Map<String, Object> map = userAddressService.deleteAddress(userAddress);
        if(Integer.parseInt(map.get("status").toString()) == 200) {
            result.setMsg("删除成功!");
            result.setSuccess(true);
            result.setDetails(userAddress);
        }
        else {
            result.setMsg("删除失败!");
            result.setSuccess(false);
            result.setDetails(userAddress);
        }
        return result;
    }

}

