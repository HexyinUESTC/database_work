package com.uprainsun.demo.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uprainsun.demo.generator.entity.new_talk;

import java.util.List;
import java.util.Map;

public interface new_talkService extends IService<new_talk> {
    List<new_talk> getAllNew();
    Map<String, Object> likeTalk(new_talk newTalk);
    Map<String, Object> insertTalk(new_talk newTalk);
}
