package com.uprainsun.demo.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uprainsun.demo.generator.entity.new_talk;
import com.uprainsun.demo.generator.mapper.new_talkMapper;
import com.uprainsun.demo.generator.service.new_talkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class new_talkServiceImpl extends ServiceImpl<new_talkMapper, new_talk> implements new_talkService {
    @Autowired
    private new_talkMapper newTalkMapper;
    @Override
    public List<new_talk> getAllNew() {
        List<new_talk> new_talkList = newTalkMapper.selectList(null);
        return new_talkList;
    }

    @Override
    public Map<String, Object> likeTalk(new_talk newTalk) {
        Map<String, Object> mp = new HashMap<>();
        new_talk nowTalk = newTalkMapper.selectById(newTalk.getId());
        newTalk.setVersion(nowTalk.getVersion());
        int i = 0;
        try {
            i = newTalkMapper.updateById(newTalk);
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
    public Map<String, Object> insertTalk(new_talk newTalk) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = newTalkMapper.insert(newTalk);
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
