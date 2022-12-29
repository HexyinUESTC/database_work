package com.uprainsun.demo.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uprainsun.demo.generator.entity.hot_talk;
import com.uprainsun.demo.generator.mapper.hot_talkMapper;
import com.uprainsun.demo.generator.service.hot_talkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class hot_talkServiceImpl extends ServiceImpl<hot_talkMapper, hot_talk> implements hot_talkService {
    @Autowired
    private hot_talkMapper hotTalkMapper;
    @Override
    public List<hot_talk> getAllHot() {
        List<hot_talk> hot_talkList = hotTalkMapper.selectList(null);
        return hot_talkList;
    }

    @Override
    public Map<String, Object> likeTalk(hot_talk hotTalk) {
        Map<String, Object> mp = new HashMap<>();
        hot_talk nowTalk = hotTalkMapper.selectById(hotTalk.getId());
        hotTalk.setVersion(nowTalk.getVersion());
        int i = 0;
        try {
            i = hotTalkMapper.updateById(hotTalk);
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
