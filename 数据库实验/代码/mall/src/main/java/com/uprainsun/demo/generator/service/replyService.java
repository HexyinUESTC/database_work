package com.uprainsun.demo.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uprainsun.demo.generator.entity.reply;

import java.util.Map;

public interface replyService extends IService<reply> {
    Map<String, Object> addReply(reply rep);
    Map<String, Object> deleteReply(Integer replyId);
}
