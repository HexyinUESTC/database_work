package com.uprainsun.demo.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uprainsun.demo.generator.entity.hot_talk;

import java.util.List;
import java.util.Map;

public interface hot_talkService extends IService<hot_talk> {
    List<hot_talk> getAllHot();
    Map<String, Object> likeTalk(hot_talk hotTalk);
}
