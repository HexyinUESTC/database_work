package com.uprainsun.demo.generator.service;

import com.uprainsun.demo.generator.entity.UserAddress;
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
public interface UserAddressService extends IService<UserAddress> {
    List<UserAddress> getAllAddressByUserId(Integer useId);
    Map<String, Object> addAddress(UserAddress userAddress);
    Map<String, Object> updateAddress(UserAddress userAddress);
    Map<String, Object> deleteAddress(UserAddress userAddress);
}
