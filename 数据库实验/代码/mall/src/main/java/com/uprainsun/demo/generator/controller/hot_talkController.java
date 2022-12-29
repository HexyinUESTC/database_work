package com.uprainsun.demo.generator.controller;

import com.uprainsun.demo.generator.entity.RESULT;
import com.uprainsun.demo.generator.entity.hot_talk;
import com.uprainsun.demo.generator.mapper.hot_talkMapper;
import com.uprainsun.demo.generator.service.hot_talkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generator/hot_talk")
public class hot_talkController {
    @Autowired
    private hot_talkService hotTalkService;
    @GetMapping("/getAllHot")
    public RESULT getAllHot() {
        RESULT result = new RESULT();
        List<hot_talk> hot_talkList = hotTalkService.getAllHot();
        result.setSuccess(true);
        result.setMsg("请求成功！");
        result.setDetails(hot_talkList);
        return result;
    }
    @PostMapping("/like_Talk")
    public RESULT like_Talk(@RequestBody hot_talk hotTalk) {
        RESULT result = new RESULT();
        Map<String, Object> mp = hotTalkService.likeTalk(hotTalk);
        if (Integer.parseInt(mp.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("请求成功！");
            result.setDetails(hotTalk);
        }
        else {
            result.setSuccess(false);
            result.setMsg("请求失败！");
            result.setDetails(hotTalk);
        }
        return result;
    }
}
