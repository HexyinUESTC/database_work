package com.uprainsun.demo.generator.mapper;

import com.uprainsun.demo.generator.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void print() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        User user = new User();
        user.setCreateTime(date);
        user.setUpdateTime(date);
//        user.setCreateTime(date);
        user.setId(2);
        user.setEmail("123");
        user.setFileName("/VO.PNG");
        user.setGender(1);
        user.setMobile("123");
        user.setUserName("xyy");
        user.setPassword("123");
//        User user = new User(1, "wo", "123", 1, "123", "123", "/VO.PNG", timestamp, timestamp);
        int res = userMapper.insert(user);
        System.out.println("插入数据"+res);
    }
}