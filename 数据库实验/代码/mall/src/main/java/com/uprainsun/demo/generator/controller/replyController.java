package com.uprainsun.demo.generator.controller;

import com.uprainsun.demo.generator.entity.RESULT;
import com.uprainsun.demo.generator.entity.reply;
import com.uprainsun.demo.generator.service.commentService;
import com.uprainsun.demo.generator.service.replyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/generator/reply")
public class replyController {

    @Autowired
    private replyService repService;

    @PostMapping("/addReply")
    public RESULT addReply(@RequestBody reply rep) {
        RESULT result = new RESULT();
        result.setSuccess(false);
        result.setMsg("请求失败！");
        Map<String, Object> mp = repService.addReply(rep);
        if(Integer.parseInt(mp.get("status").toString()) == 200) {
            result.setMsg("请求成功！");
            result.setSuccess(true);
            result.setDetails(rep);
        }
        else {
            result.setMsg("请求失败！");
            result.setSuccess(false);
            result.setDetails(rep);
        }
        return result;
    }

    @PostMapping("/deleteReply")
    public RESULT deleteReply(@RequestParam Integer replyId) {
        RESULT result = new RESULT();
        result.setMsg("请求失败！");
        result.setSuccess(false);
        Map<String, Object> mp = repService.deleteReply(replyId);
        if(Integer.parseInt(mp.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("请求成功！");
            result.setDetails(replyId);
        }
        else {
            result.setSuccess(false);
            result.setMsg("请求失败！");
            result.setDetails(replyId);
        }
        return result;
    }
}
