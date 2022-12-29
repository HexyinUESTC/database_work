package com.uprainsun.demo.generator.controller;


import com.uprainsun.demo.generator.entity.Cart;
import com.uprainsun.demo.generator.entity.Order;
import com.uprainsun.demo.generator.entity.RESULT;
import com.uprainsun.demo.generator.entity.UserAddress;
import com.uprainsun.demo.generator.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
@RequestMapping("/generator/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/getAllCart")
    public RESULT getAllCart(@RequestParam String userId) {
        RESULT result = new RESULT();
        result.setSuccess(true);
        result.setMsg("获取成功");
        List<Cart> cartList = cartService.getAllCart(Integer.valueOf(userId));
        result.setDetails(cartList);
        return result;
    }
    @PostMapping("/addCart")
    public RESULT addCart(@RequestBody Cart cart) {
        RESULT result = new RESULT();
        result.setSuccess(false);
        result.setMsg("添加失败!");
        Map<String, Object> map = cartService.addCart(cart);
        if (Integer.parseInt(map.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("请求成功!");
            result.setDetails(cart);
        }
        else {
            result.setSuccess(false);
            result.setMsg("请求失败!");
            result.setDetails(cart);
        }
        return result;
    }
    @PostMapping("/deleteCart")
    public RESULT deleteCart(@RequestBody Cart cart) {
        RESULT result = new RESULT();
        result.setSuccess(false);
        result.setMsg("删除失败!");
        Map<String, Object> map = cartService.removeCart(cart);
        if (Integer.parseInt(map.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("删除成功!");
            result.setDetails(cart);
        }
        else {
            result.setSuccess(false);
            result.setMsg("删除失败!");
            result.setDetails(cart);
        }
        return result;

    }
    @PostMapping("/updateCart")
    public RESULT updateCart(@RequestBody Cart cart) {
        RESULT result = new RESULT();
        result.setSuccess(false);
        result.setMsg("更新失败!");
        Map<String, Object> map = cartService.updateCart(cart);
        if (Integer.parseInt(map.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("更新成功!");
            result.setDetails(cart);
        }
        else {
            result.setSuccess(false);
            result.setMsg("更新失败!");
            result.setDetails(cart);
        }
        return result;
    }
}

