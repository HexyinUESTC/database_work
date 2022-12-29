package com.uprainsun.demo.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.uprainsun.demo.generator.entity.UserAddress;
import com.uprainsun.demo.generator.mapper.UserAddressMapper;
import com.uprainsun.demo.generator.service.UserAddressService;
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
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    public List<UserAddress> getAllAddressByUserId(Integer useId) {
        List<UserAddress> userAddressList;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", useId);
        userAddressList = userAddressMapper.selectList(queryWrapper);
        return userAddressList;
    }

    @Override
    public Map<String, Object> addAddress(UserAddress userAddress) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = userAddressMapper.insert(userAddress);
        }catch (Exception e) {
            i = -1;
        }
        if(i > 0) {
            mp.put("status", "200");
        }
        else {
            mp.put("status", "404");
        }
        return mp;
    }

    @Override
    public Map<String, Object> updateAddress(UserAddress userAddress) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = userAddressMapper.updateById(userAddress);
        }catch (Exception e) {
            i = -1;
        }
        if(i > 0) {
            mp.put("status", "200");
        }
        else {
            mp.put("status", "404");
        }
        return mp;
    }

    @Override
    public Map<String, Object> deleteAddress(UserAddress userAddress) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = userAddressMapper.deleteById(userAddress.getId());
        }catch (Exception e) {
            i = -1;
        }
        if(i > 0) {
            mp.put("status", "200");
        }
        else {
            mp.put("status", "404");
        }
        return mp;
    }
}
