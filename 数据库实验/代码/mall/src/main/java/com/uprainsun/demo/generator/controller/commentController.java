package com.uprainsun.demo.generator.controller;

import com.uprainsun.demo.generator.entity.RESULT;
import com.uprainsun.demo.generator.entity.comment;
import com.uprainsun.demo.generator.entity.commentDetail;
import com.uprainsun.demo.generator.entity.reply;
import com.uprainsun.demo.generator.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generator/comment")
public class commentController {
    @Autowired
    private commentService comService;

    @PostMapping("/getAllComment")
    public RESULT getAllComment(@RequestParam String talk_id ) {
        RESULT result = new RESULT();
        List<commentDetail> commentDetailList = comService.getAllComment(Integer.valueOf(talk_id));
        result.setMsg("请求成功！");
        result.setSuccess(true);
        result.setDetails(commentDetailList);
        return result;
    }

    @PostMapping("/addComment")
    public RESULT addComment(@RequestBody comment com) {
        RESULT result = new RESULT();
        result.setSuccess(false);
        result.setMsg("请求失败！");
        Map<String, Object> mp = comService.addComment(com);
        if(Integer.parseInt(mp.get("status").toString()) == 200) {
            result.setMsg("请求成功！");
            result.setSuccess(true);
            result.setDetails(com);
        }
        else {
            result.setMsg("请求失败！");
            result.setSuccess(false);
            result.setDetails(com);
        }
        return result;
    }
    @PostMapping("/deleteComment")
    public RESULT deleteComment(@RequestParam Integer commentId) {
        RESULT result = new RESULT();
        result.setMsg("请求失败！");
        result.setSuccess(false);
        Map<String, Object> mp = comService.deleteComment(commentId);
        if(Integer.parseInt(mp.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("请求成功！");
            result.setDetails(commentId);
        }
        else {
            result.setSuccess(false);
            result.setMsg("请求失败！");
            result.setDetails(commentId);
        }
        return result;
    }

}
