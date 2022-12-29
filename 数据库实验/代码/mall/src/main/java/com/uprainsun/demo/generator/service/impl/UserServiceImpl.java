package com.uprainsun.demo.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.uprainsun.demo.generator.entity.Image;
import com.uprainsun.demo.generator.entity.User;
import com.uprainsun.demo.generator.mapper.ImageMapper;
import com.uprainsun.demo.generator.mapper.UserMapper;
import com.uprainsun.demo.generator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ImageMapper imageMapper;
    private String PATH = "D:\\ideaPro\\mall\\src\\main\\resources\\static";
   @Override
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", user.getUserName());
        User existUser = userMapper.selectOne(queryWrapper);
        if(existUser != null) {
            map.put("msg", "用户名已存在!");
            map.put("status", 404);
        }
        else {
            int i = 0;
            try {
                i = userMapper.insert(user);
            }catch (Exception e) {
                i = -1;
            }
            if(i > 0) {
                map.put("msg", "注册成功!");
                map.put("status", 200);
            }
            else {
                map.put("msg", "插入数据库失败！!");
                map.put("status", 404);
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> login(String name, String password, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", name);
        User existUser = userMapper.selectOne(queryWrapper);
        if(existUser == null) {
            map.put("msg", "请输入用户名!");
            map.put("status", 404);
        }
        else {
            if(existUser.getPassword().equals(password)) {
                httpSession.setAttribute("userName", existUser.getUserName());
                map.put("msg", "登录成功!");
                map.put("status", 200);
            }
            else {
                map.put("msg", "密码错误!");
                map.put("status", 404);
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> upload(MultipartFile file, String name) {
        Map<String, Object> mp = new HashMap<>();
        if(file == null || file.isEmpty() || name == null || name.isEmpty()) {
            System.out.println("他妈的上传文件为空aaaaaaaaaaaaaaa");
            mp.put("status", 404);
            mp.put("msg", "上传文件为空");
        }
        else {
            try{
                InputStream inputStream = file.getInputStream();
                Path upLoadPath = Paths.get(PATH);
                if(!upLoadPath.toFile().exists()) upLoadPath.toFile().mkdir();
                Files.copy(inputStream, Paths.get(PATH).resolve(name), StandardCopyOption.REPLACE_EXISTING);
                Image ima = new Image();
                ima.setId(1);
                ima.setDescription("");
                ima.setName(name);
                ima.setLocation(name);
                Date date = new Date();
                ima.setCreateTime(date);
                ima.setUpdateTime(date);
                imageMapper.insert(ima);
                mp.put("status", 200);
                mp.put("msg", upLoadPath);
                System.out.println("upload file, filename is" + name);
            }catch (IOException e) {
                mp.put("status", 404);
                mp.put("msg", e.getMessage());
                e.printStackTrace();
            }
        }
        return mp;
    }



    @Override
    public Map<String, Object> download(String name) {
        Map<String, Object> mp = new HashMap<>();
        if(name == null || name.isEmpty()) {
            System.out.println("他妈的下载文件为空aaaaaaaaaaaaa");
            mp.put("status", "404");
            mp.put("msg", "下载文件为空");
        }
        else {
            try {
                File file = Paths.get(PATH).resolve(name).toFile();
                if(file.exists() && file.canRead()) {
                    ResponseEntity<FileSystemResource> body = ResponseEntity.ok().contentType(file.getName().contains(".jpg") ? MediaType.IMAGE_JPEG:MediaType.IMAGE_PNG)
                            .body(new FileSystemResource(file));
//                    result.setDetails(body);
                    mp.put("status", "200");
                    mp.put("msg", body);
                }

            }catch (Exception e) {
                mp.put("status", "404");
                mp.put("msg", e.getMessage());
                e.printStackTrace();
            }
        }
        return mp;
    }


    @Override
    public Map<String, Object> edit(User user) {
        Map<String, Object> mp = new HashMap<>();
        int i = 0;
        try {
            i = userMapper.updateById(user);
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
    public User getUserById(int userId) {
        User user = userMapper.selectById(userId);
        return user;
    }
}
