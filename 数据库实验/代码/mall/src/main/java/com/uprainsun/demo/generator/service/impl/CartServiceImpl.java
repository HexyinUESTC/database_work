package com.uprainsun.demo.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.uprainsun.demo.generator.entity.Cart;
import com.uprainsun.demo.generator.mapper.CartMapper;
import com.uprainsun.demo.generator.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<Cart> getAllCart(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        return cartList;
    }

    @Override
    public Map<String, Object> addCart(Cart cart) {
        Map<String, Object> map = new HashMap<>();
        int i = 0;
        try {
            i = cartMapper.insert(cart);
        }catch (Exception e) {
            i = -1;
        }
        if(i > 0) {
            map.put("status", "200");
        }
        else {
            map.put("status", "404");
        }
        return map;
    }

    @Override
    public Map<String, Object> removeCart(Cart cart) {
        Map<String, Object> map = new HashMap<>();
        int i = 0;
        try {
            i = cartMapper.deleteById(cart.getId());
        }catch (Exception e) {
            i = -1;
        }
        if(i > 0) {
            map.put("status", "200");
        }
        else {
            map.put("status", "404");
        }
        return map;
    }

    @Override
    public Map<String, Object> updateCart(Cart cart) {
        Map<String, Object> map = new HashMap<>();
        int i = 0;
        try {
            i = cartMapper.updateById(cart);
        }catch (Exception e) {
            i = -1;
        }
        if(i > 0) {
            map.put("status", "200");
        }
        else {
            map.put("status", "404");
        }
        return map;
    }
}
