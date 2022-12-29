package com.uprainsun.demo.generator.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.uprainsun.demo.generator.entity.RESULT;
import com.uprainsun.demo.generator.entity.User;
import com.uprainsun.demo.generator.mapper.UserMapper;
import com.uprainsun.demo.generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/generator/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public RESULT register(@RequestBody User user) {
        System.out.println("注册用户"+user.toString());
        Map<String, Object> map = userService.register(user);
        if(Integer.parseInt(map.get("status").toString()) == 200) {
            return new RESULT((String) map.get("msg"), true, user);
        }
        else {
            return new RESULT((String) map.get("msg"), false, user);
        }
    }

    @PostMapping("/login")
    public RESULT login(@RequestBody User user, HttpSession httpSession) {
        System.out.println("登录用户"+user.toString());
        Map<String, Object> map = userService.login(user.getUserName(), user.getPassword(), httpSession);
        if(Integer.parseInt(map.get("status").toString()) == 200) {
            return new RESULT((String) map.get("msg"), true, user);
        }
        else {
            return new RESULT((String) map.get("msg"), false, user);
        }
    }

    @PostMapping("/edit")
    public RESULT edit(@RequestBody User user) {
        RESULT result = new RESULT();
        result.setSuccess(false);
        result.setMsg("修改失败!");
        System.out.println("编辑用户信息"+user.toString());
        Map<String, Object> map = userService.edit(user);
        if(Integer.parseInt(map.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg("修改成功!");
            result.setDetails(user);
        }
        else {
            result.setSuccess(false);
            result.setMsg("修改失败!");
            result.setDetails(user);
        }
        return result;
    }

    @PostMapping("/getUserById")
    public RESULT getUserById(@RequestParam String userId) {
        RESULT result = new RESULT();
        result.setSuccess(true);
        result.setMsg("请求成功!");
        User user = userService.getUserById(Integer.valueOf(userId));
        result.setDetails(user);
        return result;
    }

    @PostMapping("/upload")
    public RESULT upload(@RequestParam MultipartFile file, @RequestParam String name) {
        RESULT result = new RESULT();
        Map<String, Object> map = userService.upload(file, name);
        if(Integer.parseInt(map.get("status").toString()) == 200) {
            result.setSuccess(true);
            result.setMsg(map.get("msg").toString());
            System.out.println("aaaaaaaaaaaaaaaaaaa");
        }
        else {
            result.setSuccess(false);
            result.setMsg(map.get("msg").toString());
            System.out.println("bbbbbbbbbbbbbbbbbbbbb");
        }
        return result;
    }


}

