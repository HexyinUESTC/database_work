package com.uprainsun.demo.generator.controller;


import com.uprainsun.demo.generator.entity.RESULT;
import com.uprainsun.demo.generator.entity.hot_talk;
import com.uprainsun.demo.generator.entity.new_talk;
import com.uprainsun.demo.generator.mapper.hot_talkMapper;
import com.uprainsun.demo.generator.mapper.new_talkMapper;
import com.uprainsun.demo.generator.service.new_talkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generator/new_talk")
public class new_talkController {
    @Autowired
    private new_talkService newTalkService;
    @GetMapping("/getAllNew")
    public RESULT getAllNew() {
        RESULT result = new RESULT();
        List<new_talk> new_talkList;
        new_talkList = newTalkService.getAllNew();
        System.out.println(new_talkList);
        result.setSuccess(true);
        result.setMsg("请求成功！");
        result.setDetails(new_talkList);
        return result;
    }

    @PostMapping("/like_Talk")
    public RESULT like_Talk(@RequestBody new_talk newTalk) {
        RESULT result = new RESULT();
        Map<String, Object> mp = newTalkService.likeTalk(newTalk);
        if (Integer.parseInt(mp.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("请求成功！");
            result.setDetails(newTalk);
        }
        else {
            result.setSuccess(false);
            result.setMsg("请求失败！");
            result.setDetails(newTalk);
        }
        return result;
    }
    @PostMapping("/insert_Talk")
    public RESULT insert_Talk(@RequestBody new_talk newTalk) {
        RESULT result = new RESULT();
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqq");
        Map<String, Object> mp = newTalkService.insertTalk(newTalk);
        if (Integer.parseInt(mp.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("请求成功！");
            result.setDetails(newTalk);
            System.out.println("ccccccccccccccccc");
        }
        else {
            result.setSuccess(false);
            result.setMsg("请求失败！");
            result.setDetails(newTalk);
            System.out.println("ddddddddddddddddddddd");
        }
        return result;
    }
}
