package com.uprainsun.demo.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uprainsun.demo.generator.entity.Cart;
import com.uprainsun.demo.generator.entity.comment;
import com.uprainsun.demo.generator.entity.commentDetail;
import com.uprainsun.demo.generator.entity.reply;
import com.uprainsun.demo.generator.mapper.CartMapper;
import com.uprainsun.demo.generator.mapper.commentMapper;
import com.uprainsun.demo.generator.mapper.replyMapper;
import com.uprainsun.demo.generator.service.CartService;
import com.uprainsun.demo.generator.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
@Service
public class commentServiceImpl extends ServiceImpl<commentMapper, comment> implements commentService {
    @Autowired
    private commentMapper comMapper;
    @Autowired
    private replyMapper repMapper;
    @Override
    public List<commentDetail> getAllComment(Integer talkId) {
        List<commentDetail> commentDetailList = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("talk_id", talkId);
        List<comment> commentList = comMapper.selectList(queryWrapper);
        for(comment com : commentList) {
            commentDetail comDetail = commentCombineReply(com);
            commentDetailList.add(comDetail);
        }
        return commentDetailList;
    }

    private commentDetail  commentCombineReply(comment com) {
        commentDetail comDetail = new commentDetail();
        Integer id = com.getId();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("comment_id", id);
        List<reply> replyList = repMapper.selectList(queryWrapper);
        int replySize = replyList.size();
        comDetail.setCom(com);
        comDetail.setReply_num(replySize);
        comDetail.setReplyList(replyList);
        return comDetail;
    }

    @Override
    public Map<String, Object> addComment(comment com) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = comMapper.insert(com);
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
    public Map<String, Object> deleteComment(Integer commentId) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = comMapper.deleteById(commentId);
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
    public Map<String, Object> likeComment(comment com) {
        Map<String, Object> mp = new HashMap<>();
        comment nowcomment = comMapper.selectById(com.getId());
        com.setVersion(nowcomment.getVersion());
        int i = 0;
        try {
            i = comMapper.updateById(com);
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
