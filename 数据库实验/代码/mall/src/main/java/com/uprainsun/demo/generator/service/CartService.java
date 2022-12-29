package com.uprainsun.demo.generator.service;

import com.uprainsun.demo.generator.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
public interface CartService extends IService<Cart> {
    List<Cart> getAllCart(Integer userId);
    Map<String, Object> addCart(Cart cart);
    Map<String, Object> removeCart(Cart cart);
    Map<String, Object> updateCart(Cart cart);
}
