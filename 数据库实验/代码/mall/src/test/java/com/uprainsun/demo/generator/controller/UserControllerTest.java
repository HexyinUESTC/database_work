package com.uprainsun.demo.generator.controller;

import com.uprainsun.demo.generator.entity.RESULT;
import com.uprainsun.demo.generator.entity.User;
import com.uprainsun.demo.generator.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Date;
import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void test() {
        Date date = new Date();
        User user = new User(Integer.valueOf(1), "hcl", "123", Integer.valueOf(0), " ", " ", " ", date, date);
        RESULT result = userController.register(user);
        System.out.println("aaaaaaaaaaaaaaaaaaa"+String.valueOf(result));
        User user1 = (User) result.getDetails();
        System.out.println("bbbbbbbbbbbbbbbbbbb"+String.valueOf(user1));
    }

}