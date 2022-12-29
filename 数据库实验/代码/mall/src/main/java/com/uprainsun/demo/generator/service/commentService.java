package com.uprainsun.demo.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uprainsun.demo.generator.entity.Order;
import com.uprainsun.demo.generator.entity.comment;
import com.uprainsun.demo.generator.entity.commentDetail;
import com.uprainsun.demo.generator.entity.reply;

import java.util.List;
import java.util.Map;

public interface commentService extends IService<comment> {
    List<commentDetail> getAllComment(Integer talkId);
    Map<String, Object> addComment(comment com);

    Map<String, Object> deleteComment(Integer commentId);
    Map<String, Object> likeComment(comment com);
}
