package com.uprainsun.demo.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uprainsun.demo.generator.entity.reply;
import com.uprainsun.demo.generator.mapper.replyMapper;
import com.uprainsun.demo.generator.service.replyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class replyServiceImpl extends ServiceImpl<replyMapper, reply> implements replyService {

    @Autowired
    private replyMapper repMapper;
    @Override
    public Map<String, Object> addReply(reply rep) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = repMapper.insert(rep);
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
    public Map<String, Object> deleteReply(Integer replyId) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = repMapper.deleteById(replyId);
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
