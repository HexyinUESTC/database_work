package com.uprainsun.demo.generator.service.impl;

import com.uprainsun.demo.generator.entity.Order;
import com.uprainsun.demo.generator.mapper.OrderMapper;
import com.uprainsun.demo.generator.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
